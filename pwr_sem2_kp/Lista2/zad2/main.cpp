#include <iostream>
#include "parsowanie.hpp"
#include "WierszTrojkataPascala.hpp"

using namespace std;

int main(int argc, char* argv[])
{
	if(argc < 2)
		return 44;
	try
	{
		int n = charToInt(argv[1]);
		if(n < 0 || n > 33)
		{
			cout << argv[1] << " - nieprawidłowy numer wiersza" << endl;
			return 45;
		}
		WierszTrojkataPascala* wierszN = new WierszTrojkataPascala(n);
		for(int i = 2; i < argc; i++)
		{
			try
			{
				cout << argv[i];
				int k = charToInt(argv[i]);
				int w = wierszN->wspolczynnik(k);
				cout << " - " << w << endl;
			}
			catch(char const* ex)
			{
				cout << ex << endl;
			}
			
		}
		delete wierszN;
	}
	catch(char const* ex)
	{
		cout << argv[1] << ex << endl;
	}
	return 0;
}
 
