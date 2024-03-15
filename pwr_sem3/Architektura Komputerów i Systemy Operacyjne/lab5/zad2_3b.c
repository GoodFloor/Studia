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
    for (int i = 0; i < 1000; i++)
    {
        kill(pid, SIGUSR1);
    }
    printf("Wysłano 1000 sygnałów SIGUSR1\n");    
    return 0;
}
