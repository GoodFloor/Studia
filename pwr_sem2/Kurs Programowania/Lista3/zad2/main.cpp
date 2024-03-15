#include <iostream>
#include <string>
#include "figury.hpp"

using namespace std;

int main(int argc, char* args[])
{
    if(argc == 0)
        return 44;

    string kolejnosc = args[1];
    int licznik = 1;
    Figura** tabFigur = new Figura*[kolejnosc.length()];
    
    for(int i = 0; i < kolejnosc.length(); i++)
    {
        licznik++;
        //Sprawdzanie czy jeszcze są argumenty
        if(licznik >= argc)
        {
            cout << "Podano za mało argumentów!" << endl;
            return 44;
        }

        try{
            if(kolejnosc[i] == 'o')
            {
                double r = stod(args[licznik]);
                if(r <= 0)
                {
                    cout << "Długość promienia musi być dodatnia" << endl;
                    continue;
                }
                tabFigur[i] = new Kolo(r);
            }
            else if(kolejnosc[i] == 'c')
            {
                if(argc - licznik < 5)
                {
                    cout << "Podano za mało argumentów!" << endl;
                    return 44;
                }
                double bok1 = stod(args[licznik]);
                licznik++;
                double bok2 = stod(args[licznik]);
                licznik++;
                double bok3 = stod(args[licznik]);
                licznik++;
                double bok4 = stod(args[licznik]);
                licznik++;
                double kat = stod(args[licznik]);
                if(bok1 <= 0 || bok2 <= 0 || bok3 <= 0 || bok4 <= 0)
                {
                    cout << "Długość boku musi być dodatnia" << endl;
                    continue;
                }
                if(kat <= 0 || kat >= 180)
                {
                    cout << "Kąt powinien być z przedziału (0; 180)" << endl;
                    continue;
                }
                else if(kat != 90)
                {
                    if(bok1 == bok2 && bok1 == bok3 && bok1 == bok4)
                        tabFigur[i] = new Romb(bok1, kat);
                    else 
                    {
                        cout << "Romb powinien mieć wszystkie boki tej samej długości" << endl;
                        continue;
                    }
                }
                else
                {
                    if(bok1 == bok2 && bok1 == bok3 && bok1 == bok4)
                        tabFigur[i] = new Kwadrat(bok1);
                    else if((bok1 == bok2 && bok3 == bok4) || (bok1 == bok3 && bok2 == bok4) || (bok1 == bok4 && bok3 == bok2))
                    {
                        if(bok1 == bok2)
                            tabFigur[i] = new Prostokat(bok1, bok3);
                        else
                            tabFigur[i] = new Prostokat(bok1, bok2);
                    }
                    else
                    {
                        cout << "Prostokąt powinien mieć dwie pary boków tej samej długości" << endl;
                        continue;
                    }
                }
            }
            else if(kolejnosc[i] == 'p')
            {
                double a = stod(args[licznik]);
                if(a <= 0)
                {
                    cout << "Długość boku musi być dodatnia" << endl;
                    continue;
                }
                tabFigur[i] = new Pieciokat(a);
            }
            else if(kolejnosc[i] == 's')
            {
                double b = stod(args[licznik]);
                if(b <= 0)
                {
                    cout << "Długość boku musi być dodatnia" << endl;
                    continue;
                }
                tabFigur[i] = new Szesciokat(b);
            }
            else
            {
                cout << "Podano niewłaściwy rodzaj figury geometrycznej!" << endl;
                continue;
            }
            cout << kolejnosc[i] << "." << i << ": \tPole = " << tabFigur[i]->liczPole() << "; \n\tObwód = " << tabFigur[i]->liczObwod() << endl;
        }
        catch(const exception&){
            cout << "Podano nieprawidłowy argument!" << endl;
        }
    }
    for(int i = 0; i < kolejnosc.length(); i++)
    {
        delete tabFigur[i];
    }
    delete []tabFigur;
    return 0;
}
