#include "tuples.hpp"
#include <iostream>

using namespace std;

int* next_tuple (int* L, int n, int k)throw(char const*)
{
    //Sprawdzanie czy element jest najwiekszy (postać {..., n-2, n-1, n})
    for(int i = 0; i < k; i++)
    {
        if(L[k - i - 1] != n - i)
            break;
        if(i == k - 1)
            throw "Podano najwiekszy element";
    }

    //Utworzenie kopii danej tablicy L
    int* wynik = new int(k);
    for(int i = 0; i < k; i++)
    {
        wynik[i] = L[i];
    }

    //Pętla sprawdza od końca czy na danym miejscu jest maksymalna możliwa wartość (na ostatnim miejscu n, na przedostatnim n-1 itd.)
    for(int i = 0; i < k; i++)
    {
        if(wynik[k - i - 1] != n - i)
        {
	    //Jeżeli nie, to zwiększa tą wartość o jeden
            wynik[k - i - 1] = wynik[k -i - 1] + 1;
	    //oraz wartość o kolejnych indeksach ustawia jako kolejne liczby (jeśli zinkrementowana poprzednio wartość jest teraz równa p to kolejna liczba jest równa p+1 itd.) 
            for(int j = k - i; j < k; j++)
            {
                wynik[j] = wynik[j - 1] + 1;
            }
	    //Przerywa wykonywanie pętli
            break;
        }
    }
    return wynik;
}

void gen_tuples(int n, int k)
{
    //Tworzenie elementu najmniejszego (postaci {1, 2, 3, ..., k})
    int l[k];
    for(int i = 0; i < k; i++)
    {
        l[i] = i + 1;
    }
    //Utworzenie wskaźnika do tego elementu
    int* ptr = l;
    //Pętla do momentu natrafienia na błąd funkcji next_tuple (osiągnięcia elementu największego)
    while(true)
    {
        try
        {
	    //wypisanie wartości wskazywanej przez wskaźnik ptr
            cout << "[ ";
            for(int i = 0; i < k; i++)
            {
                cout << ptr[i] << " ";
            }
            cout << "]" << endl;
	    //zmiana wartości wskazywanej przez ptr na następną
            ptr = next_tuple(ptr, n, k);
        }
	//Po trafieniu na największy element zakończenie pętli
        catch(char const* s)
        {
            break;
        }
    }
}
