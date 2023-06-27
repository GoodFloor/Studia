interface Pojazd {    
    public int PodajCene();
//public int A=1;

}
interface Ciezarowy extends Pojazd{
    public int PodajLadownosc();
}
interface Samochod extends Pojazd{
    public int PodajIloscMiejsc();

}

class SamochodOsobowy implements Samochod{   
    public int PodajIloscMiejsc()
    {
        return 4;
    }
    public int PodajCene()
    {
        return 40000;
    }
}
class SamochodDostawczy implements Samochod, Ciezarowy{   
    public int PodajIloscMiejsc()
    {
        return 8;
    }
    public int PodajLadownosc()
    {
        return 500;
    }
    public int PodajCene()
    {
       return 60000;
    }

}

public class Interfejsy {
    public static void main(String[] args) {
		
            Samochod s = new SamochodOsobowy();
	    s.PodajIloscMiejsc();

        Samochod  p[] = new Samochod [2]; 
        // Samochod d = new Samochod();
			
            Samochod s1 = new SamochodDostawczy();
            Ciezarowy s2 = new SamochodDostawczy();

	    Pojazd p = new SamochodOsobowy();
	    int cena = p.PodajCene(); 

            
	   System.out.println(cena);
    }
}