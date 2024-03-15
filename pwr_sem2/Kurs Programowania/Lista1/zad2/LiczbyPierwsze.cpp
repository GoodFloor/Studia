#include "LiczbyPierwsze.hpp"

//Konstruktor
LiczbyPierwsze::LiczbyPierwsze(int n)
{
    //Sito
    ileLiczbPierwszych = 0;
	bool czyPierwsze[(n+1)];
    czyPierwsze[0] = false;
    czyPierwsze[1] = false;
    for(int i = 2; i <= n; i++)
    {
        czyPierwsze[i] = true;
    }
    for(int i = 2; i <= n; i++)
    {
        if(czyPierwsze[i])
        {
            ileLiczbPierwszych++;
            for(int j = 2 * i; j <= n; j+= i)
            {
                czyPierwsze[j] = false;
            }
        }
    }

    //Wpisuję wszystkie liczby pierwsze z zakresu do nowej tablicy
    tabLiczbPierwszych = new int(ileLiczbPierwszych);
    int t = 0;
    for(int i = 2; i <= n; i++)
    {
        if(czyPierwsze[i])
        {
            tabLiczbPierwszych[t] = i;
            t++;
        }
    }
}

//Destruktor
LiczbyPierwsze::~LiczbyPierwsze()
{
    delete tabLiczbPierwszych;
}

//Metoda
int LiczbyPierwsze::liczba(int n)
{
    if(n < ileLiczbPierwszych)
        return tabLiczbPierwszych[n];
    return 0;
}