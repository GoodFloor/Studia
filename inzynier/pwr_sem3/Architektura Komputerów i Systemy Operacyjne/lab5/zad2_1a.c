#include <stdio.h>
#include <signal.h>
#include <unistd.h>


void signalHandler(int code) {
    printf("Otrzymano sygnał %d, ignorowanie\n", code);
    signal(code, signalHandler);
}

int main(int argc, char const *argv[])
{
    for (int i = 1; i < 65; i++)
    {
        if(i != 32 && i != 33) {
            printf("Ustawiam obsługę dla sygnału %d\n", i);
            signal(i, signalHandler);
        }
    }
    printf("PID: %d\n", getpid());

    while (1);
    return 0;
}
