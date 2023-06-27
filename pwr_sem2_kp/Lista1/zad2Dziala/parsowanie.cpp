#include <string>
#include <iostream>
#include "parsowanie.hpp"

using namespace std;

int charToInt(char* znak)throw(char const*)
{
    string tekst = znak;
    int wynik = 0;
    int i = 0;
    if(znak[i] == 45)
    	i++;
    while(znak[i] >= 48 && znak[i] < 58 && i < tekst.length())
    {
        wynik*= 10;
        wynik+= znak[i] - 48;
        i++;
    }
    if(i == tekst.length())
    {
        if(znak[0] == 45)
    	wynik*= -1;
    	return wynik;
    }
    else 
        throw " - bledna dana";
}
