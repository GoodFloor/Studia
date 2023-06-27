//lab1 - Łukasz Machnik
#include <stdio.h>
#include <math.h>

void zad1()     ///     ZADANIE 1
{
    printf("Zadanie 1:\n\n");
    char slowo[11]=("ABRAKADABRA");
    for(int i = 0; i < 11; i++)
    {
        for(int j = 0; j < i; j++)
        {
            printf(" ");
        }
        for(int j = 0; j < (11 - i); j++)
        {
            printf("%c ", slowo[j]);
        }
        printf("\n");
    }
}
void zad2()     ///     ZADANIE 2
{
    printf("\n\nZADANIE 2:\n\nWprowadz kolejno wspolczynniki a, b, c twojego rownania w postaci ax^2 + bx + c = 0:\n");
    double a = 0, b = 0, c = 0;

    scanf("%lf", &a);
    scanf("%lf", &b);
    scanf("%lf", &c);
    if(a == 0)
    {
        c *= (-1);
        printf("To jest rownanie liniowe a nie kwadratowe! => Ma jedno rozwiazanie:\n\tx0 = %lf\n", c);
    }
    else
    {
        double delta = (b * b) - (4 * a * c);
        if(delta < 0)
        {
            printf("To rownanie w zbiorze liczb rzeczywistych nie ma rozwiazan.\n");
        }
        else if(delta == 0)
        {
            double x0 = ((-1) * b) / (2 * a);
            printf("To rownanie w zbiorze liczb rzeczywistych ma jedno rozwiazanie:\n\tx0 = %lf\n", x0);
        }
        else
        {
            delta = sqrt(delta);
            double x1 = (((-1) * b) - delta) / (2 * a);
            double x2 = (((-1) * b) + delta) / (2 * a);
            printf("To rownanie w zbiorze liczb rzeczywistych ma dwa rozwiazania:\n\tx1 = %lf\n\tx2 = %lf\n", x1, x2);
        }
    }
    

}
void zad3()     ///     ZADANIE 3
{
    int n = 0;
    printf("\n\nZadanie 3:\n\nPodaj liczbe calkowita n aby wygenerowac prostokat o wymiarach n wierszy na 2 * n kolumn:\n");
    scanf("%i", &n);
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < (2*n); j++)
        {
            printf("*");
        }
        printf("\n");
    }
}
void zad4()     ///     ZADANIE 4
{
    int n = 0;
    printf("\n\nZadanie 4:\n\nPodaj liczbe calkowita n aby wygenerowac trojkat rownoramienny zlozony z n wierszy:\n");
    scanf("%i", &n);
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < (n-1-i); j++)
        {
            printf(" ");
        }
        for(int j = 0; j < ((i * 2) + 1); j++)
        {
            printf("*");
        }
        printf("\n");
    }
}

int main(void)
{
    int cel = -1;
    while(cel != 0)
    {
        printf("\nWpisz numer zadania aby do niego przejsc, wpisz 0 aby zakonczyc: ");
        scanf("%d", &cel);
        switch (cel)
        {
        case 1:
            zad1();
            break;
        case 2:
            zad2();
            break;
        case 3:
            zad3();
            break;
        case 4:
            zad4();
            break;   
        case 0:
            printf("Do widzenia!\n");
            break; 
        default:
            printf("Wpisano niepoprawna liczbe! Sprobuj ponownie!\n");
            break;
        }
    }
    return 0;
}
