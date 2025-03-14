class WierszTrojkataPascala 
{
private:
    //Atrybuty
    int* wspolczynniki;
    int numerWiersza;

public:
    //Konstruktor
    WierszTrojkataPascala(int n);
    ~WierszTrojkataPascala();

    //Metoda
    int wspolczynnik(int m)throw (char const*);
};
