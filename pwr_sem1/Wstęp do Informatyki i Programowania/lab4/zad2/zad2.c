//lab3 - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "funkcje.h"

struct mozliwosc
{
    int pos[5];
    struct mozliwosc* nast;
};

struct mozliwosc* glowa = NULL;

void dodajNaListe(struct mozliwosc* nowy)
{
    if(glowa == NULL)
    {
        glowa = nowy;
    }
    else
    {
        struct mozliwosc* tmp = glowa;
        while(tmp->nast != NULL)
        {
            tmp = tmp->nast;
        }
        tmp->nast = nowy;
    }
}

bool usunZListy(struct mozliwosc* element)
{
    if(glowa == NULL || element == NULL)
    {
        return false;
    }
    if(glowa == element)
    {
        glowa = glowa->nast;
    }
    else 
    {
        struct mozliwosc* tmp = glowa;
        while(tmp->nast != element)
        {
            tmp = tmp->nast;
        }
        tmp->nast = tmp->nast->nast;
    }
    return true;

}

int liczListe()
{
    struct mozliwosc* tmp = glowa;
    int suma = 0;
    while(tmp != NULL)
    {
        tmp = tmp->nast;
        suma++;
    }
    return suma;
}

void drukujElement(struct mozliwosc* element)
{
    printf("\n");
    for(int i = 1; i <= 4; i++)
    {
        printf("[%i]", element->pos[i]);
    }
    printf("\n");
}

int ileCzerwonych(struct mozliwosc* wynik, struct mozliwosc* odpowiedz)
{
    int suma = 0;
    for(int i = 1; i <= 4; i++)
        if(odpowiedz->pos[i] == wynik->pos[i])
            suma++;
    return suma;
}

int ileBialych(struct mozliwosc* wynik, struct mozliwosc* odpowiedz)
{
    struct mozliwosc temp = *odpowiedz;
    int suma = 0;
    for(int i = 1; i <= 4; i++)
    {
        for(int j = 1; j <= 4; j++)
        {
            if(wynik->pos[i] == temp.pos[j])
            {
                suma++;
                temp.pos[j] = 0;
                break;
            }
        }
    }
    return suma;
}

int main()
{
    for(int a = 1; a < 7; a++)
    {
        for(int b = 1; b < 7; b++)
        {
            for(int c = 1; c < 7; c++)
            {
                for(int d = 1; d < 7; d++)
                {   
                    struct mozliwosc* nowy = malloc(sizeof(struct mozliwosc));
                    nowy->pos[1] = a;
                    nowy->pos[2] = b;
                    nowy->pos[3] = c;
                    nowy->pos[4] = d;
                    nowy->nast = NULL;
                    dodajNaListe(nowy);
                }
            }
        }
    }
    struct mozliwosc* obecny = NULL;
    while(liczListe() > 1)
    {
        struct mozliwosc* tmp;
        obecny = glowa;
        drukujElement(obecny);
        int bialych, czerwonych;
        printf("\nIle czerwonych punktów dajesz? ");
        scanf("%i", &czerwonych);
        printf("Ile białych punktów dajesz? ");
        scanf("%i", &bialych);
        tmp = obecny->nast;
        while(tmp != NULL)
        {
            if(ileBialych(tmp, obecny) != bialych + czerwonych || ileCzerwonych(tmp, obecny) != czerwonych)
            {
                usunZListy(tmp);
            }
            tmp = tmp->nast;
        }
        if(czerwonych != 4)
        {
            usunZListy(obecny);
        }
        printf("%i\n", liczListe());
    }
    if(liczListe() == 0)
    {
        printf("Oszukiwałeś!\n");
    }
    else
    {
        printf("Poprawna odpowiedź to: ");
        drukujElement(glowa);
    }
    return 0;
}
