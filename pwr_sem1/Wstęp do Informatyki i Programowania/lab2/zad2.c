//lab2 - Łukasz Machnik
#include <stdio.h>

int main()
{
    int n = 0;
    double avg = 0;
    printf("Podaj z ilu liczb chcesz policzyc srednia:\n");
    scanf("%d", &n);
    while(n < 1)
    {
    	printf("Podana wartosc musi byc dodatnia!\n");
        printf("Podaj z ilu liczb chcesz policzyc srednia:\n");
        scanf("%d", &n);
    }
    printf("Wpisuj kolejne liczby oddzielajac je enterami:\n");
    for(int i = 0; i < n; i++)
    {
        printf("\t");
        double input = 0;
        scanf("%lf", &input);
        avg+= input;
    }
    avg/= n;
    printf("Srednia tych liczb wynosi %lf\n", avg);
    return 0;
}
