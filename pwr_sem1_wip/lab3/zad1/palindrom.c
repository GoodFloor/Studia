//lab3 palindrom.c - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>

bool palindrom(char napis[])
{
    int l = 0;
    while(napis[l] != '\0')
    {
        l++;
    }
    l--;
    int i = 0;
    while(i < l)
    {
        if(napis[l]!=napis[i])
            return false;
        i++;
        l--;
    }
    return true;
}
