//lab3 - Łukasz Machnik
#include <stdio.h>
#include "funs.h"

int phi(long int n)
{
    int licznik = 1;
    for(long int i = 2; i < n; i++)
    {
        if(czyWzgledniePierwsze(i, n))
            licznik++;
    }
    return licznik;
}
