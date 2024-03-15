//lab3 - Łukasz Machnik
#include <stdio.h>
#include "rozwiazanie.h"
#include "f.h"

int main()
{
/*   
    double a, b, eps;
    printf("Podaj początek zakresu: ");
    scanf("%lf", &a);
    printf("Podaj koniec zakresu: ");
    scanf("%lf", &b);
    if(b <= a)
    {
        printf("Koniec zakresu musi być większy niż jego początek!\n");
        if(b == a)
            return 4;
    }
    if(f(a) * f(b) > 0)
    {
        printf("Wartości z końców zakresu muszą być przeciwnego znaku!\n");
        return 5;
    }
    printf("Podaj dokładność określenia rozwiązania (epsilon): ");
    scanf("%lf", &eps);
    printf("Miejsce zerowe w tym zakresie = %lf\n", rozwiazanie(a, b, eps));
*/   
    int k = -1;
    for(double i = 0.1; i >= 0.00000001; i*= 0.1)
    {
        printf("Miejsce zerowe w zakresie [%lf, %lf] z dokładnością 10^%i: %lf\n", 2.0, 4.0, k, rozwiazanie(2.0, 4.0, i));
	k--;
    }

    return 0;
}
