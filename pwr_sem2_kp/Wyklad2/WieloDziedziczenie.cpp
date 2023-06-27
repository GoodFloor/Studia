#include<iostream>
using namespace std;

class Samochod
{
	public:
	int pojemnosc;
};

class Pojazd
{
	public:
	int pojemnosc;
	int iloscmiejsc;
};

class Amfibia: public Pojazd, public Samochod
{
	//public:
	// int pojemnosc;
};

int main(int argc, char* argv[]) {

	Amfibia *s = new Amfibia(); 
	
	// Niepoprawnie. Nie wiadomo czy pojemnosc ma byc z klasy Pojazd czy Samochod
	// Byloby poprawnie, gdyby odkomentowac to co jest w klasie Amfibia.
	//	s->pojemnosc=1500;

	// Poprawnie. Jawne wskazywanie z ktorej klasy nalezy wzi�c atrybut pojemnosc
	s->Samochod::pojemnosc=1500;

	// Poprawnie. Nie ma konfliktu. 
	s->iloscmiejsc=4;

    cout << "Pojemnosc" << endl; 
	cout << s->Pojazd::pojemnosc << endl; 

    cout << "Ilosc miejsc" << endl; 
	cout << s->iloscmiejsc << endl; 


    return 0;

}