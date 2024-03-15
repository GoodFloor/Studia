//lab4 - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>
#include "match.h"

int main()
{
    char wzorzec[256];
    char lancuch[256];
    for(int i = 0; i < 256; i++)
    {
        wzorzec[i] = 0;
        lancuch[i] = 0;
    }
    printf("Podaj wzorzec: ");
    for(int i = 0; i < 256; i++)
    {
        scanf("%c", &wzorzec[i]);
        if(wzorzec[i] == 10)
            break;
    }
    printf("Podaj łańcuch który chcesz porównać z wzorcem: ");
    for(int i = 0; i < 256; i++)
    {
        scanf("%c", &lancuch[i]);
        if(lancuch[i] == 10)
            break;
    }
    if(match(wzorzec, lancuch))
        printf("Łańcuch jest zgodny z wzorcem.\n");
    else
        printf("Łańcuch nie jest zgodny ze wzorcem.\n");

    /*for(int i = 0; i < 256; i++)
    {
        printf("%c", wzorzec[i]);
        if(wzorzec[i] == 10)
            break;
    }*/
    return 0;
}
