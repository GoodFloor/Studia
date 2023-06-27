
#include <iostream>
#include <string>
using namespace std;

class Osoba {
public:
    string imie;
	// Konstruktor kopiujacy
    Osoba ( const Osoba &osoba) {
		imie = osoba.imie;
        cout << "konstruktor kopiujacy\n";
    }
 
    Osoba(string imie = "") {
        this->imie = imie;
    }
	
};
 
 
int main()
{
 
    Osoba *Karol = new Osoba("Karol"); // wywolanie konst zwyklego
	cout << Karol->imie << endl;

    Osoba Arek (*Karol);  // pierwsze wywolanie konst kopiujacego
	cout << Arek.imie << endl;

	Osoba Krzysiek=(*Karol); // drugie wywolanie konst kopiujacego
	cout << Krzysiek.imie << endl;

    return 0;
}