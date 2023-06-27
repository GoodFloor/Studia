#include<iostream>

#include "Temperatura.hpp"

using namespace std;

int main(int argc, char *argv[]) {
    Temperatura * a;
    Temperatura * b = new Temperatura();
    Temperatura * c = new Temperatura(100.0);

	a = b;
    cout << "C:" << a << "; K:" << a->Kelvin() << "; F:" << a->Fahrenheit() << ";" << endl;
    cout << "C:" << c << "; K:" << c->Kelvin() << "; F:" << c->Fahrenheit() << ";" << endl;
    c = b;
    cout << "C:" << c << "; K:" << c->Kelvin() << "; F:" << c->Fahrenheit() << ";" << endl;
    c->setF( 0.0 );
    cout << "C:" << c << "; K:" << c->Kelvin() << "; F:" << c->Fahrenheit() << ";" << endl;
    cout << "C:" << a << "; K:" << a->Kelvin() << "; F:" << a->Fahrenheit() << ";" << endl;

}
