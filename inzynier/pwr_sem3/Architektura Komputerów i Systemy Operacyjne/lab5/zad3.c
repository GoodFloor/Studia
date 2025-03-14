#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>
#include <limits.h>
#include <stdbool.h>
#include <fcntl.h>

int fgPID = 0;


void ctrl_c_handler(int nr) {
    signal(SIGINT, ctrl_c_handler);
    if(fgPID != 0) {
        kill(fgPID, 9);
        fgPID = 0;
    }
}
void zombie_handler(int nr) {
    signal(SIGCHLD, zombie_handler);
    wait(NULL);
}
char* extendString(char* string, char extension) {
    int length = strlen(string);
    char* temp = (char*)malloc(sizeof(char) * (length + 1));
    for (int i = 0; i < length; i++)
    {
        temp[i] = string[i];
    }
    temp[length] = extension;
    return temp;
}
char* deleteLastChar(char* string) {
    int length = strlen(string);
    char* temp = (char*)malloc(sizeof(char) * (length - 1));
    for (int i = 0; i < length - 1; i++)
    {
        temp[i] = string[i];
    }
    return temp;
}
char** addArgv(char** stringArray, int arraySize, char* string) {
    char** temp = malloc((arraySize + 1) * sizeof(char*));
    for (int i = 0; i < arraySize - 1; i++)
    {
        temp[i] = stringArray[i];
    }
    temp[arraySize - 1] = string;
    temp[arraySize] = stringArray[arraySize - 1];
    return temp;
}
char* cutString(char* input) {
    char* output = "";
    for (int i = 0; i < strlen(input) && input[i] != '\0'; i++)
    {
        if (input[i] != 10)
        {
            output = extendString(output, input[i]);
        }

        
    }
    return output;
}

int main(int argc, char const *argv[])
{
    signal(SIGINT, ctrl_c_handler);
    signal(SIGCHLD, zombie_handler);
    char* line = "";
    char inheritedArgument[1024];
    bool hasPipedArgument = false;
    int fd0 = dup(0);
    int fd1 = dup(1);
    int fd2 = dup(2);


    while (strcmp(line, "exit") != 0 )
    {
        char* newfd0 = "";
        char* newfd1 = "";
        char* newfd2 = "";
        bool hasNewfd0 = false;
        bool hasNewfd1 = false;
        bool hasNewfd2 = false;
        int numberOfPipes = 0;
        int pipefd[512][2];
        dup2(fd0, 0);
        dup2(fd1, 1);
        dup2(fd2, 2);

        line = " ";
        char** newargv = malloc(sizeof(char*));
        newargv[0] = NULL;
        int newargc = 1;
        char cwd[256];
        if (getcwd(cwd, sizeof(cwd)) != NULL)
        {
            printf("\033[38;5;3;160;98m%s$ \033[37m", cwd);
        }
        

        //Czytanie linii
        size_t len = 0;
        ssize_t lineSize = 0;
        lineSize = getline(&line, &len, stdin);
        
        if(!line[0]) {
            printf("Ctrl-D\n");
            break;
        }
        int waitForChild = 1;
        if(strlen(line) < 2) {
            continue;
        }
        if(strcmp(deleteLastChar(line), "exit") == 0) {
            break;
        }
        for (int i = 0; i < (strlen(line) - 1); i++)
        {
            char* phrase = "";
            while (line[i] != ' ' && i < (strlen(line) - 1))
            {
                phrase = extendString(phrase, line[i]);
                i++;
            }
            if(phrase != "" && strcmp(phrase, "&") && strcmp(phrase, "|") && strcmp(phrase, "<") && strcmp(phrase, ">") && strcmp(phrase, "2>")) {
                newargv = addArgv(newargv, newargc, phrase);
                newargc++;
            }
            else if(strcmp(phrase, "&") == 0) {
                waitForChild = 0;
                break;
            }
            else if(strcmp(phrase, "|") == 0) {
                if(pipe(pipefd[numberOfPipes]) < 0) {
                    perror("Pipe error");
                    exit(44);
                }
                int t;
                int forkPid = fork();
                if(forkPid == 0) {
                    close(pipefd[numberOfPipes][0]);
                    dup2(pipefd[numberOfPipes][1], 1);
                    t = execvp(newargv[0], newargv);
                }
                else {
                    close(pipefd[numberOfPipes][1]);
                    dup2(pipefd[numberOfPipes][0], 0);
                    
                    free(newargv);
                    newargv = malloc(sizeof(char*));
                    newargv[0] = NULL;
                    newargc = 1;
                    numberOfPipes++;
                }
            }
            else if(strcmp(phrase, "<") == 0) {
                if(line[i] == ' ') {
                    i++;
                }
                hasNewfd0 = true;
                while (line[i] != ' ' && i < (strlen(line) - 1))
                {
                    newfd0 = extendString(newfd0, line[i]);
                    i++;
                }
            }
            else if(strcmp(phrase, ">") == 0) {
                if(line[i] == ' ') {
                    i++;
                }
                hasNewfd1 = true;
                while (line[i] != ' ' && i < (strlen(line) - 1))
                {
                    newfd1 = extendString(newfd1, line[i]);
                    i++;
                }
            }
            else if(strcmp(phrase, "2>") == 0) {
                if(line[i] == ' ') {
                    i++;
                }
                hasNewfd2 = true;
                while (line[i] != ' ' && i < (strlen(line) - 1))
                {
                    newfd2 = extendString(newfd2, line[i]);
                    i++;
                }
            }
        }

        //Wykonywanie polecenia 
        if(strcmp(newargv[0], "cd") == 0) {
            chdir(newargv[1]);
            continue;
        }
        int t;
        int forkPid = fork();
        if(forkPid == 0) {
            if(hasNewfd0) {
                int newfd = open(newfd0, O_RDONLY);
                dup2(newfd, 0);
            }
            if(hasNewfd1) {
                int newfd = open(newfd1, O_WRONLY|O_CREAT);
                dup2(newfd, 1);
            }
            if(hasNewfd2) {
                int newfd = open(newfd2, O_WRONLY|O_CREAT);
                dup2(newfd, 2);
            }
            t = execvp(newargv[0], newargv);
        }
        else {
            fgPID = forkPid;
            if(waitForChild) {
                wait(&t);
            }
            fgPID = 0;
        }

        // printf("'%s', %d\n", line, strcmp(line, "exit"));
        // for (int i = 0; i < strlen(line); i++)
        // {
        //     printf("%d ", line[i]);
        // // }

        
    }
    
    
    
    // char c;
    // int size;
    // char* newargv[1000];
    // int argc = 0;
    // size = read(0, &c, 1);
    // while(size > 0) {
    //     char* argument = "";
    //     while (c != ' ')
    //     {
    //         argument = extendString(argument, c);
    //     }
    //     newargv[argc] = argument;
        
    //     write(1, &c, 1);
    //     size = read(0, &c, 1);
    // }

    // signal(SIGINT, ctrl_c_handler);
    // char* newargv[3];
    // newargv[0] = "sleep";
    // newargv[1] = "10";
    // newargv[2] = NULL;
    
    return 0;
}
