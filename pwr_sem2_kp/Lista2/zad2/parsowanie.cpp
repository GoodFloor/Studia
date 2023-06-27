#include <string>
#include "parsowanie.hpp"

using namespace std;

int charToInt(char* znak)throw(char const*)
{
    string tekst = znak;
    int wynik = 0;
    int i = 0;
    if(tekst[i] == 45)
        i++;
    while(tekst[i] >= 48 && tekst[i] < 58 && i < tekst.length())
    {
        wynik*= 10;
        wynik+= tekst[i] - 48;
        i++;
    }
    if(i != tekst.length())
    	throw " - bledna dana";
    else if(tekst[0] == 45)
        wynik*= (-1);
    return wynik;
}

