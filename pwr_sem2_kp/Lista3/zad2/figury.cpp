#include "figury.hpp"
#include <math.h>
#include <iostream>

    Figura::~Figura() { }

//Koło
    Kolo::Kolo(double promien)
    {
        r = promien;
    }
    double Kolo::liczPole()
    {
        return  M_PI * r * r;
    }
    double Kolo::liczObwod()
    {
        return M_PI * 2 * r;
    }

//Czworokąt
    Czworokat::Czworokat(double bok1, double bok2)
    {
        a = bok1;
        b = bok2;
    }
    double Czworokat::liczObwod()
    {
        return (2.0 * a + 2.0 * b);
    }

//Prostokąt
    Prostokat::Prostokat(double bok1, double bok2) : Czworokat(bok1, bok2) { }
    double Prostokat::liczPole()
    {
        return a * b;
    }

//Kwadrat
    Kwadrat::Kwadrat(double bok1) : Czworokat(bok1, bok1) { }
    double Kwadrat::liczPole()
    {
        return a * a;
    }

//Romb
    Romb::Romb(double bok1, double kat) : Czworokat(bok1, bok1)
    {
        k = kat;
    }
    double Romb::liczPole()
    {
        return a * a * sin(k / 180.0 * M_PI);
    }
    double Romb::liczObwod()
    {
        return 4 * a;
    }

//Pięciokąt
    Pieciokat::Pieciokat(double bok)
    {
        a = bok;
    }
    double Pieciokat::liczPole()
    {
        return ((a * a) / 4) * sqrt(25 + (10 * sqrt(5)));
    }
    double Pieciokat::liczObwod()
    {
        return 5 * a;
    }

//Sześciokąt
    Szesciokat::Szesciokat(double bok)
    {
        a = bok;
    }
    double Szesciokat::liczPole()
    {
        return ((a * a * 3) / 2) * sqrt(3);
    }
    double Szesciokat::liczObwod()
    {
        return 6 * a;
    }
