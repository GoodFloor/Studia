#include <iostream>
#include <string>
#include "tuples.hpp"

using namespace std;

int main()
{
    //Wczytywanie od użytkownika wartości n, k oraz elementów tablicy od której następny element mamy wyznaczyć (L) 
    unsigned int n , k;
    cout << "Podaj n: ";
    cin >> n;
    cout << "Podaj k: ";
    cin >> k;
    int l[k];
    for(int i = 0; i < k; i++)
    {
        cout << "Podaj element o indeksie [" << i << "] ciagu L: ";
        cin >> l[i];
    }
    //Próba wyznaczenia następnego elementu oraz wypisania go
    try
    {
        int* s = next_tuple(l, n, k);
        cout << "Nastepny element - [ ";
        for(int i = 0; i < k; i++)
        {
            cout << s[i] << " ";
        }
        cout << "]" << endl;
    }
    //Przy otrzymaniu błedu oznaczającego że podana wartość to element największy - wypisanie komunikatu
    catch(char const* s)
    {
        cout << s << endl;
    }
    return 0;
}
