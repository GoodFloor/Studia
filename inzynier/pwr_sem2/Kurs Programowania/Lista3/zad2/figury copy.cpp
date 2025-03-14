#include "figury.hpp"
#include <math.h>

class Figura
{
public:
    virtual double liczPole();
    virtual double liczObwod();
};

class Kolo : public Figura
{
private:
    double r;

public:
    Kolo(double promien)
    {
        r = promien;
    }

    double liczPole()
    {
        return  M_PI * r * r;
    }
    double liczObwod()
    {
        return M_PI * 2 * r;
    }
};

class Czworokat : public Figura
{
protected:
    double a, b;

public:
    Czworokat(double bok1, double bok2)
    {
        a = bok1;
        b = bok2;
    }
    virtual double liczPole();
    double liczObwod()
    {
        return (2.0 * a + 2.0 * b);
    }
};

class Prostokat : public Czworokat
{
public:
    Prostokat(double bok1, double bok2) : Czworokat(bok1, bok2) { }
    double liczPole()
    {
        return a * b;
    }
};

class Kwadrat : public Czworokat
{
public:
    Kwadrat(double bok1) : Czworokat(bok1, bok1) { }
    double liczPole()
    {
        return a * a;
    }
};

class Romb : public Czworokat
{
protected:
    double k;

public:
    Romb(double bok1, double kat) : Czworokat(bok1, bok1)
    {
        k = kat;
    }
    double liczPole()
    {
        return a * a * sin(k / 180.0 * M_PI);
    }
    double liczObwod()
    {
        return 4 * a;
    }
};

class Pieciokat : public Figura
{
private: 
    double a;

public:
    Pieciokat(double bok)
    {
        a = bok;
    }
    double liczPole()
    {
        return ((a * a) / 4) * sqrt(25 + (10 * sqrt(5)));
    }
    double liczObwod()
    {
        return 5 * a;
    }
};

class Szesciokat : public Figura
{
private:
    double a;

public:
    Szesciokat(double bok)
    {
        a = bok;
    }
    double liczPole()
    {
        return ((a * a * 3) / 2) * sqrt(3);
    }
    double liczObwod()
    {
        return 6 * a;
    }
};
