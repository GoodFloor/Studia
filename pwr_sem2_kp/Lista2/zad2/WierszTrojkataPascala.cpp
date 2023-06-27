#include "WierszTrojkataPascala.hpp"

//Konstruktor
WierszTrojkataPascala::WierszTrojkataPascala(int n) 
{
	wspolczynniki = new int[n + 1];
	int temp = n / 2;
	if(n % 2 != 0)
		temp++;
	int wsp = 1;
	for(int k = 0; k <= temp; k++) 
	{
		wspolczynniki[k] = wsp;
		wspolczynniki[n - k] = wsp;
		//wsp = (wsp * (n - k)) / (k + 1);
		wsp = wsp * (((n - k) * 1.0) / ((k + 1) * 1.0));
	}
	numerWiersza = n;
}

//Destruktor
WierszTrojkataPascala::~WierszTrojkataPascala()
{
    delete []wspolczynniki;
}

//Metoda
int WierszTrojkataPascala::wspolczynnik(int m)throw (char const*)
{
	if(m < 0 || m > numerWiersza)
			throw " - błędny zakres";
	return wspolczynniki[m];
}
