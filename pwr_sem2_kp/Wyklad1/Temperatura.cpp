#include<iostream>

#include "Temperatura.hpp"

using namespace std;

// Definicja metod
double Temperatura::Kelvin() { return t+273.15; }
double Temperatura::Fahrenheit() { return 1.8*t+32; }
double Temperatura::Celsjusz() { return t; }
void Temperatura::set(double t) { this->t = t; }
void Temperatura::setF(double t) { this->t = 5.0*(t-32)/9.0; }
void Temperatura::setK(double t) { this->t = t-273.15; }

// Konstruktory
Temperatura::Temperatura() { t = 0.0; }
Temperatura::Temperatura(double t) { this->t = t; }

ostream & operator<<(ostream & out, Temperatura * t) { return out<<t->t; }

