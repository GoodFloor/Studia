//lab2_1 Łukasz Machnik
#include <stdio.h>

int main()
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
            printf("\t\t%d x %d gr\n", licznik, nominalgr[i]);
        licznik=0;
    }
    return 0;
}
