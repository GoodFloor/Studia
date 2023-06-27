//lab3 - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>
#include "funkcje.h"

int main()
{
    int wynik[4], propozycja[4]; //0 - brak, kolory - od 1 do 6
    for(int i = 0; i < 4; i++)
    {
        wynik[i] = 0;
        propozycja[i] = 0;
    }
    int ileWystapien[6], wypisano[6];    
    bool mozliweMiejsca[6][4];
    for(int i = 0; i < 6; i++)
    {
        ileWystapien[i] = 0;
        wypisano[i] = 0;
        for(int j = 0; j < 4; j++)
        {
            mozliweMiejsca[i][j] = 1;
        }
    }
    int obecnyKolor = 1;
    int czerwonych = 0;
    int bialych = 0;
    while((wynik[0] == 0 || wynik[1] == 0 || wynik[2] == 0 || wynik[3] == 0) && obecnyKolor <= 6)
    {
        for(int i = 0; i < 6; i++)          //zerowanko
        {
            wypisano[i] = 0;
        }

        for(int i = 0; i < 4; i++)          //Wypisujemy wiadome
        {
            propozycja[i] = wynik[i];
            if(propozycja[i] != 0)
            {
                wypisano[propozycja[i]-1]++;
            }
        }

        bool zepsuted = 0;
        for(int i = 0; i < 6; i++)          //Wypisujemy sugestie
        {
            for(int j = 0; j < 4 && ileWystapien[i] != wypisano[i]; j++)
            {
                if(propozycja[j] == 0 && mozliweMiejsca[i][j])
                {
                    propozycja[j] = i;
                    wypisano[i]++;
                }
            }
            if(ileWystapien[i] > wypisano[i])
            {
                drukujWynik(wynik);
                printf("\nZepsułeś grę! %i %i %i", i, ileWystapien[i], wypisano[i]);
                zepsuted = 1;
            }
        }
        if(zepsuted)
            break;

        for(int i = 0; i < 4; i++)          //Wypisujemy następny kolor
        {
            if(propozycja[i] == 0)
            {
                propozycja[i] = obecnyKolor;
            }
        }
        
        drukujWynik(propozycja);

        int bialychStare = bialych;
        int czerwonychStare = czerwonych;
        printf("\nIle białych punktów dajesz? ");
        scanf("%i", &bialych);
        printf("Ile czerwonych punktów dajesz? ");
        scanf("%i", &czerwonych);
        int zmiana = (bialych - bialychStare) + (czerwonych - czerwonychStare);
        ileWystapien[obecnyKolor] = zmiana;

        if(zmiana == 0)         //Obecnego koloru nie ma
        {
            for(int i = 0; i < 6; i++)
                for(int j = 0; j < 4; j++)
                    mozliweMiejsca[i][j] = 0;
            if(czerwonychStare == czerwonych)
            {
                for(int j = 0; j < 4; j++)
                {
                    mozliweMiejsca[propozycja[j]][j] = 0;
                }
            }
        }






        obecnyKolor++;
    }
    
    return 0;
}












/*
wynik = 0 0 0 0 
propozycja = wynik 
jeżeli propozycja[i] = 0 to 


ile naDobrymMiejscu?
ile dobryKolor?
jeżeli (naDobrymMiejscu + dobryKolor) jest większy niż poprzednio
    to obecnego koloru jest tyle jaka różnica
*/