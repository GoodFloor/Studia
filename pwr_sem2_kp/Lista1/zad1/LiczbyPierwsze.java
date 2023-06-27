public class LiczbyPierwsze {

	//Atrybuty
	boolean[] czyPierwsze;
	int[] tabLiczbPierwszych;


	public int ileLiczbPierwszych;

	//Konstruktor
	LiczbyPierwsze(int n)
	{
		//Sito
		ileLiczbPierwszych = 0;
		czyPierwsze = new boolean[n + 1];
		czyPierwsze[0] = false;
		czyPierwsze[1] = false;
		for(int i = 2; i <= n; i++)
		{
			czyPierwsze[i] = true;
		}
		for(int i = 2; i <= n; i++)
		{
			if(czyPierwsze[i])
			{
				ileLiczbPierwszych++;
				for(int j = 2 * i; j <= n; j+=i)
				{
					czyPierwsze[j] = false;
				}
			}
		}

		//Wpisuję wszystkie liczby pierwsze z zakresu do nowej tablicy
		tabLiczbPierwszych = new int[ileLiczbPierwszych];
		int t = 0;
		for(int i = 2; i <= n; i++)
		{
			if(czyPierwsze[i])
			{
				tabLiczbPierwszych[t] = i;
				t++;
			}
		}
	}

	//Metoda
	public int liczba(int m)
	{
		//if(m >= ileLiczbPierwszych)

		return tabLiczbPierwszych[m];
	}
}