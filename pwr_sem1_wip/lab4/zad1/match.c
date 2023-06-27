//lab4 match.c - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>

bool match(char* wzorzec, char* lancuch)
{
    int wzorDlugosc = -1, lancuchDL = -1;
    bool rowne = 1;
    for(int i = 0; i < 256; i++)
    {
        if(wzorzec[i] == 10 && wzorDlugosc == -1)
            wzorDlugosc = i;
        if(lancuch[i] == 10 && lancuchDL == -1)
            lancuchDL = i;
        if(wzorzec[i] != lancuch[i])
        {
            //debug
            //printf("%i %i %i \n", i, wzorzec[i], lancuch[i]);
            rowne = 0;
        }
    }
    if(rowne || (wzorDlugosc == 0 && lancuchDL == 0))
        return true;
    if(wzorzec[wzorDlugosc-1] == '*')
    {
        if(wzorDlugosc == 1)
            return true;
        /*if(wzorzec[wzorDlugosc-2] == '*')
        {
            wzorzec[wzorDlugosc-1] = 10;
            return match(wzorzec, lancuch);
        }*/

        wzorzec[wzorDlugosc-1] = 10;
        wzorDlugosc--;
        while(lancuchDL > 0)
        {
            char wzorzecTemp[256], lancuchTemp[256];
            for(int i = 0; i < 256; i++)
            {
                wzorzecTemp[i] = wzorzec[i];
            }
            for(int i = 0; i < 256; i++)
            {
                lancuchTemp[i] = lancuch[i];
            }
            if(!match(wzorzecTemp, lancuchTemp))
            {
                lancuch[lancuchDL-1] = 10;
                lancuchDL--;
            }
            else 
                return true;
        }
    }
    else if(wzorzec[wzorDlugosc-1] == '?' || wzorzec[wzorDlugosc-1] == lancuch[lancuchDL-1])
    {
        wzorzec[wzorDlugosc-1] = 10;
        lancuch[lancuchDL-1] = 10;
        return match(wzorzec, lancuch);
    }
    return false;
    
}
