//lab2 - Łukasz Machnik
#include <stdio.h>
#include <math.h>

int main()
{
    double wynik=1.0;
    for(int i = 1; i <= 1000; i++)
    {
        wynik*= pow(i, 0.001);
    }
    printf("Wynik: %lf\n", wynik);
    return 0;
}
