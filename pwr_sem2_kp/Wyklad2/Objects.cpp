#include <iostream>
using namespace std;

class Klasa {
    static char ID;
    int          a;
public :
	char        id;
public:
    Klasa() {
        id = ID++;
        a  = 0;
        cout << "Ctor()    " << id << a << endl;
    }
    Klasa(int aa) {
        id = ID++;
        a  = aa;
        cout << "Ctor(int) " << id << a << endl;
    }
    ~Klasa() {
        cout << "Dtor      " << id << a << endl;
    }
};

// Przypisanie do atrybutu statycznego
char Klasa::ID = 'A';

Klasa k1;                          // <- A

//Klasa ka();  // NIE!

int main(void) {
    cout << "Wchodzimy do funkcji \'main\'" << endl;

	// Sposoby tworzenia obiektow w cpp
    // Klasa kb = Klasa; // NIE!
    {
        Klasa k3 = Klasa();        // <- C
        Klasa k4 = Klasa(4);       // <- D
		cout << k3.id <<  endl;
    }

    Klasa* pk5 = new Klasa;        // <- E
    Klasa* pk6 = new Klasa();      // <- F
    Klasa* pk7 = new Klasa(7);     // <- G

    delete pk6;
    delete pk7;
    cout << "Wychodzimy z funkcji \'main\'" << endl;
}

Klasa k2(2);                       // <- B