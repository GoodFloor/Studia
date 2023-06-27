#include<iostream>

using namespace std;

class Pojazd {    
public:
	virtual void PodajCene()=0;
};

class Ciezarowy : public Pojazd{
public:
	int PodajLadownosc()
    {
     return 500;
    }
    
	 void PodajCene()
    {
        cout << "30000" << endl;
    }
};

class SamochodDostawczy : public Pojazd{   
public:
	int PodajIloscMiejsc()
    {
        return 8;
    }
    int PodajLadownosc()
    {
        return 500;
    }
    void PodajCene()
    {
        cout << "60000" << endl;
    }

};

int main(int argc, char* argv[]) {

	Ciezarowy *c = new Ciezarowy();
	Pojazd *p = new Ciezarowy();

    Pojazd *p1 = new Pojazd();
	
	p->PodajCene();
}