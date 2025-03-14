class Figura
{
public:
    virtual double liczPole()=0;
    virtual double liczObwod()=0;
    ~Figura();
};

class Kolo : public Figura
{
private:
    double r;

public:
    Kolo(double promien);

    double liczPole();
    double liczObwod();
};

class Czworokat : public Figura
{
protected:
    double a, b;

public:
    Czworokat(double bok1, double bok2);
    virtual double liczPole()=0;
    double liczObwod();
};

class Prostokat : public Czworokat
{
public:
    Prostokat(double bok1, double bok2);
    double liczPole();
};

class Kwadrat : public Czworokat
{
public:
    Kwadrat(double bok1);
    double liczPole();
};

class Romb : public Czworokat
{
protected:
    double k;

public:
    Romb(double bok1, double kat);
    double liczPole();
    double liczObwod();
};

class Pieciokat : public Figura
{
private: 
    double a;

public:
    Pieciokat(double bok);
    double liczPole();
    double liczObwod();
};

class Szesciokat : public Figura
{
private:
    double a;

public:
    Szesciokat(double bok);
    double liczPole();
    double liczObwod();
};
