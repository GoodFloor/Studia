#include <stdio.h>
#include <signal.h>
#include <unistd.h>

int recieved = 0;

void signalHandler(int code) {
    recieved++;
    printf("Otrzymano w sumie %d sygnałów\n", recieved);
    signal(SIGUSR1, signalHandler);
}

int main(int argc, char const *argv[])
{
    signal(SIGUSR1, signalHandler);
    printf("PID: %d\n", getpid());
    while (1);
    return 0;
}
