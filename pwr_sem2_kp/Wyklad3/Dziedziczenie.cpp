#include<iostream>
#include<string>
using namespace std;

class Pojazd
{
	public:
	int pojemnosc;
	string nazwa;	
};

// Moze byc dziedziczenie z akcesorem Protected
class Samochod: protected Pojazd
{
	public:
	int iloscmiejsc;

	void PobierzNazwa(){
		nazwa = "volvo";
	};

};

// Moze byc dziedziczenie z akcesorem Private
class SamochodDostawczy:private Samochod 
{
	public:
	void PobierzNazwa1(){
		nazwa = "volvo";
	};
};


int main(int argc, char* argv[]) {
	
	Pojazd *p = new Pojazd();
	p->pojemnosc=1400;
	p->nazwa = "volvo";
	cout << p->nazwa << endl; 
	
	Samochod *s = new Samochod();

	// Niepoprawnie. Atrybut nazwa w klasie Samochod jest juz protected z uwagi na dziedziczenie.

//	s->nazwa = "volvo";
//	cout << "Nazwa" << endl; 
//	cout << s->nazwa << endl; 

	SamochodDostawczy *sd = new SamochodDostawczy();
// Nie ma dostepu bo jest to atrybut prywatny	
//	sd->iloscmiejsc=4;
	sd->PobierzNazwa1();

    return 0;

}