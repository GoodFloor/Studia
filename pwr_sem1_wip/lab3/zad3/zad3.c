//lab3 - Łukasz Machnik
#include <stdio.h>
#include "funs.h"

int main()
{
    long int n;
    printf("Podaj liczbę dla której chesz obliczyć wartość funkcji Eulera: ");
    scanf("%ld", &n);
    if(n < 1)
    {
	printf("Podana liczba musi być większa od 0\n");
	return 5;
    }
    printf("Wartość funkcji Eulera dla %ld = %i\n", n, phi(n));
    /*
    for(int i = 1; i <= 1000; i++)
    {
        printf("%i;%i\n", i, phi(i));
    }
    */
    return 0;
}
