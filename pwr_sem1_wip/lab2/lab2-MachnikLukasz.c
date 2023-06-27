//lab2 - Łukasz Machnik
#include <stdio.h>
//#include <math.h>

void zad1()
{
    int zlotych = 0, groszy = 0;
    int nominalzl[8] = {200, 100, 50, 20, 10, 5, 2, 1};
    int nominalgr[6] = {50, 20, 10, 5, 2, 1};
    printf("\nZADANIE 1:\n\nPodaj liczbe zlotych: ");
    scanf("%d", &zlotych);
    printf("Podaj liczbe groszy: ");
    scanf("%d", &groszy);
    printf("\tbanknoty:\n");
    //      ZLOTE
    for(int i = 0; i < 8; i++)
    {
        int licznik = 0;
        while(zlotych >= nominalzl[i])
        {
            licznik++;
            zlotych -= nominalzl[i];
        }
        if(nominalzl[i]==5)
            printf("\tmonety:\n");
        if(licznik>0)
            printf("\t\t%d x %d zl\n", licznik, nominalzl[i]);
        licznik=0;
    }
    //     GROSZE
    for(int i = 0; i < 6; i++)
    {
        int licznik = 0;
        while(groszy >= nominalgr[i])
        {
            licznik++;
            groszy -= nominalgr[i];
        }
        if(licznik>0)
            printf("\t\t%d x %d zl\n", licznik, nominalgr[i]);
        licznik=0;
    }
}
void zad2()
{
    int n=0;
    double avg=0;
    printf("Podaj liczbe wpisywanych zmiennych: ");
    scanf("%d", n);
    for(int i = 0; i < n; i++)
    {

    }
}


int main()
{
    //zad1();
    return 0;
}
