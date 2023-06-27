#include <iostream>
#include "LiczbyPierwsze.hpp"

using namespace std;

int znakDoLiczby(/*char znak*/)
{
	return 5/*((int)znak) - 48*/;
}

int main(int argc, char* argv[])
{
	if(argc == 0)
		return 44;
	LiczbyPierwsze* wynik;
	int t = 0;
	//cout << argc << "\n\"" << argv[0] << "\"\n\"" << argv[1] << "\"\n\"" << argv[2] << "\"\n\"" << znakDoLiczby(/*argv[0]*/);
	/*
		//Wczytywanie zakresu i tworzeni obiektu
		try 
		{ 
			int n = Integer.parseInt(args[0]); 
			if(n < 2)
			{
				System.out.println(args[0] + " - nieprawidlowy zakres");
				return;
			}
			wynik = new LiczbyPierwsze(n);
			//Wczytywanie kolejnych argumentów
			for(int i = 1; i < args.length; i++)
			{
				String wypisz = args[i] + " - ";
				try 
				{ 
					int m = Integer.parseInt(args[i]); 
					if(m < 0 || m > wynik.ileLiczbPierwszych)
					{
						wypisz += "liczba spoza zakresu";
					}
					else
					{
						wypisz += wynik.liczba(m);
					}
				}
				catch (NumberFormatException ex)
				{
					wypisz += "nieprawidlowa dana";
				}
				System.out.println(wypisz);
			}
		}
		catch (NumberFormatException ex)
		{
			System.out.println(args[0] + " - nieprawidlowa dana");
		}*/
	/*LiczbyPierwsze* wynik = new LiczbyPierwsze(14);
	for(int i=0; i<wynik->ileLiczbPierwszych; i++)
	{
		cout << i << " - " << wynik->liczba(i) << endl;
	}*/
	return 0;
}