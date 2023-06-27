#include <stdio.h>
#include <unistd.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <signal.h>

int parseInt(const char* string) {
    int length = 0;
    while(string[length] != 0) {
        length++;
    }
    int result = 0;
    for (int i = 0; i < length; i++)
    {
        result *= 10;
        result += string[i] - '0';
    }
    return result;
    
}

int main(int argc, char const *argv[])
{
    if(argc != 2) {
        printf("Niepoprawna liczba argumentów - podaj PID procesu do testowania");
        return 44;
    }
    int pid = parseInt(argv[1]);
    for (int i = 1; i < 65; i++)
    {
        if(i != 9 && i != 19 && i != 32 && i != 33) {
            printf("Wysyłam sygnał kill -%d %d\n", i, pid);
            kill(pid, i);
            sleep(0.1);
        }
    }
    printf("Wysyłam sygnał kill -19 %d\n", pid);
    kill(pid, 19);
    sleep(1);
    printf("Wysyłam sygnał kill -9 %d\n", pid);
    kill(pid, 9);
    
    return 0;
}
