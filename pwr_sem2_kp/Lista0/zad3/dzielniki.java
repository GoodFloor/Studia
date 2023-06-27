import java.util.*;

public class dzielniki {
	public static int div(int n) {
		if(n < 0)
			n *= (-1);
		int d = n - 1;
		if(n == 1)
			return 1;
		while(n % d != 0)
		{
			d--;
		}
		return d;
	}
	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++)
		{
			System.out.println(args[i] + ": ");
			int n = 0;
			try { 
				n = Integer.parseInt(args[i]); 
				if(n != 0)
					System.out.println("\t" + div(n));
				else 
					System.out.println("\t" + "0 ma nieskonczenie wiele dzielnikow");
			}
			catch (NumberFormatException ex) {
				System.out.println("\t" + args[i] + " nie jest liczba calkowita");
			}
		}
	}	
}