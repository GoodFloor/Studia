//lab3 - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>

bool czyWzgledniePierwsze(long int a, long int b)
{
    while(a % b > 0)
    {
        long int t = a;
        a = b;
        b = t % b;
    }
    if(b == 1)
        return true;
    else
        return false;
}
