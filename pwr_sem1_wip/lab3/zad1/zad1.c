//lab3 - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>
#include "palindrom.h"

int main()
{
    /*
    bool flaga = 1;
    while(flaga)
    {
        printf("Podaj słowo aby sprawdzić czy jest palindromem albo wpisz 0 aby zakończyć i zatwierdź klawiszem ENTER:\n");
        char slowo[256];
        scanf("%c", &slowo[0]);
        if(slowo[0] == '0')
        {
            flaga = 0;
        }
        else
        {
            int i = 0;
            while (slowo[i] != '\0' && slowo[i] != 10)
            {
                i++;
                scanf("%c", &slowo[i]);
            } 
            slowo[i] = '\0';
            if(palindrom(slowo))
            {
                printf("\nPodane słowo jest palindromem!\n\n");
            }
            else
            {
                printf("\nPodane słowo NIE jest palindromem!\n\n");
            }
        }
    }
    */
    printf("Podaj słowo aby sprawdzić czy jest palindromem i zatwierdź klawiszem ENTER:\n");
    char slowo[256];
	 int i = 0;
	 scanf("%c", &slowo[i]);
    while (slowo[i] != '\0' && slowo[i] != 10)
    {
        i++;
        scanf("%c", &slowo[i]);
    } 
    slowo[i] = '\0';
    if(palindrom(slowo))
    {
        printf("\nPodane słowo jest palindromem!\n\n");
    }
    else
    {
        printf("\nPodane słowo NIE jest palindromem!\n\n");
    }
    return 0;
}
