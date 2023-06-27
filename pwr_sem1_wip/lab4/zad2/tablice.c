//lab3 - Łukasz Machnik
#include <stdio.h>

void drukujWynik(int* tab)
{
    printf("\n");
    for(int i = 0; i < 4; i++)
    {
        printf("[%i]", tab[i]);
    }
    printf("?\n");
}
