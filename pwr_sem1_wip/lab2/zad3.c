//lab2 - Łukasz Machnik
#include <stdio.h>

int main()
{
    double suma = 0;
    int n = 0;
    while(suma <= 10)
    {
        n++;
        suma+= (1.0 / n);
        //printf("%d\t%lf\n", n, suma);
    }
    printf("Najmniejsze takie n wynosi: %d\n", n);
    return 0;
}
