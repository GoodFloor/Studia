#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        printf("Składnia: <liczba> <liczba cyfr po przecinku>\n");
        return 44;
    }
    float x = atof(argv[1]);
    int precyzja = atoi(argv[2]);
    int czescCalkowita = x;
    x = x - czescCalkowita;
    int y = x * 1000000;
    //Wypisanie części całkowitej
    int rozmiar = 16;
    char calkowitaBinarnie[rozmiar + 1];
    calkowitaBinarnie[rozmiar] = 0;
    for (int i = 0; i < rozmiar; i++)
    {
        int r = czescCalkowita % 2;
        calkowitaBinarnie[rozmiar - i - 1] = r + '0';
        czescCalkowita/= 2;
    }
    int c = 0;
    while (c < rozmiar && calkowitaBinarnie[c] == '0')
    {
        c++;
    }
    for (int i = c; i < rozmiar; i++)
    {
        printf("%c", calkowitaBinarnie[i]);
    }   
    printf(".");
    //Wypisanie części ułamkowej
    for (int i = 0; i < precyzja; i++)
    {
        y*= 2;
        if (y >= 1000000)
        {
            printf("1");
            y-= 1000000;
        }
        else
        {
            printf("0");
        }
    }
    return 0;
}
