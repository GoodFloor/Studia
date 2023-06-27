#include <iostream>
#include "tuples.hpp"

using namespace std;

int main()
{
    //Wczytywanie od użytkownika wartości n dopóki nie poda poprawnej (>0) wartości
    int n = 0, k = -1;
    while(n < 1)
    {
        cout << "Podaj n: ";
        cin >> n;
    }
    //Wczytywanie od użytkownika wartości k dopóki nie poda poprawnej (<=n && >0) wartości
    while(k > n || k < 0)
    {
        cout << "Podaj k: ";
        cin >> k;
    }
    //Generowanie zbiorów
    gen_tuples(n, k);
    return 0;
}
