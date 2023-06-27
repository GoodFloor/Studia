#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>

int main(int argc, char const *argv[])
{
    int socketfd;
    if (socketfd = socket(AF_LOCAL, SOCK_STREAM, 0) < 0)
    {
        printf("Błąd socketu");
        return 44;
    }
    
    return 0;
}
