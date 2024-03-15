public class Test {
	public static void main(String[] args) {
		if(args.length == 0)
			return;
		LiczbyPierwsze wynik;
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
					if(m < 0 || m >= wynik.ileLiczbPierwszych)
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
		}
	}	
}