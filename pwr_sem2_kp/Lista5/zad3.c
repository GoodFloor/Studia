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

int fgPID = 0;


void ctrl_c_handler(int nr) {
    signal(SIGINT, ctrl_c_handler);
    if(fgPID != 0) {
        kill(fgPID, 9);
        fgPID = 0;
    }
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

int main(int argc, char const *argv[])
{
    signal(SIGINT, ctrl_c_handler);
    char* line = "";

    while (strcmp(line, "exit") != 0)
    {
        line = "";
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
            if(phrase != "") {
                newargv = addArgv(newargv, newargc, phrase);
                newargc++;
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
            t = execvp(newargv[0], newargv);
        }
        else {
            fgPID = forkPid;
            wait(&t);
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
