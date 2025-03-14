#include <iostream>
#include <string>
#include "LiczbyPierwsze.hpp"
#include "parsowanie.hpp"

using namespace std;

int main(int argc, char* argv[])
{
	if(argc < 2)
		return 44;
	//Wczytywanie zakresu i tworzeni obiektu
	try
	{
        int n = charToInt(argv[1]);
        if(n < 2)
        {
            cout << argv[1] << " - nieprawidlowy zakres";
            return 45;
        }
        cout << n << endl;
        LiczbyPierwsze* wynik = new LiczbyPierwsze(n);
        //Wczytywanie kolejnych argumentów
        for(int i = 2; i < argc; i++)
        {
            if(argv[i][0] < 48 || argv[i][0] > 57)
            {
                cout << argv[i] << " - nieprawidlowa dana" << endl;
            }
            else
            {
                try
                {
                    int m = charToInt(argv[i]);
                    //int m = stoi(argv[i]);
                    if(m < 0 || m >= wynik->ileLiczbPierwszych)
                    {
                        cout << argv[i] << " - liczba spoza zakresu" << endl;
                    }
                    else
                    {
                        cout << argv[i] << " - " << wynik->liczba(m) << endl;
                    }
                }
                catch(char const* s)
                {
                    cout << argv[i] << s << endl;
                }
            }
        }
        delete wynik;
	}
	catch(char const* s)
	{
        cout << argv[1] << s;
	}
	return 0;
}
