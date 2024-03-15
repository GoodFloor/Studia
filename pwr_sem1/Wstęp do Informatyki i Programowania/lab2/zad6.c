//lab2 - Łukasz Machnik
#include <stdio.h>

int sumaDzielnikow(int n)
{
    if(n < 2)
        return -1;
    int suma = 1;
    for(int i = 2; i * i <= n; i++)
    {
        if(n % i == 0)
        {
            suma+=i;
            if(n/i != i)
            {
                suma+=n/i;
            }
        }
    }
    return suma;
}

int main()
{
    int zakres = 1000;
    int sumy[(zakres + 1)];
    printf("Liczby doskonale:\n");
    for(int i = 0; i <= zakres; i++)
    {
        sumy[i] = sumaDzielnikow(i);
        if(sumy[i] == i)
            printf("\t%d\n", i);
    }
    printf("Liczby zaprzyjaznione:\n");
    for(int i = 2; i <= zakres; i++)
    {
        for(int j = i + 1; j <= zakres; j++)
        {
            if(sumy[i] == j && sumy[j] == i)
            printf("\t(%d, %d)\n", i, j);
        }
    }
    return 0;
}
