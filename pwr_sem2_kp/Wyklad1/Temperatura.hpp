// Plik naglowkowy. Rozszerzenie hpp wskazuje na jezyk cpp. 
// Instrukcja uzyta do zapobiegania wielokrotnemu dodawania plikow naglowkowych.
#pragma once

#include<string>

using namespace std;

class Temperatura {
	// Atrybut
  private:
    double t;
	// Metody
  public:
    double Kelvin();
    double Fahrenheit();
    double Celsjusz();
    void set(double t);
    void setF(double t);
    void setK(double t);
  // Konstruktory
    Temperatura();
    Temperatura(double t);

    friend ostream & operator<<(ostream & out, Temperatura * t);
};
