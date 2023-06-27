// Interjes - z reguly tylko deklaracja metod
interface Pojazd {    
    public int PodajCene();
}

// Dziedziczenie interfejsow. Trzeba zaimplementowac wszystkie metody z interfejsu.
interface Ciezarowy extends Pojazd{
    public int PodajLadownosc();
}
interface Samochod extends Pojazd{
    public int PodajIloscMiejsc();

}

// Klasa moze implementowac wiele interfejsow
class SamochodDostawczy implements Samochod, Ciezarowy{   
    public int PodajIloscMiejsc(){
        return 8;
    }
    public int PodajLadownosc(){
        return 500;
    }
    public int PodajCene(){
       return 60000;
    }
}
class SamochodOsobowy implements Samochod{   
    public int PodajIloscMiejsc(){
        return 4;
    }
    public int PodajCene(){
        return 40000;
    }
}
public class Interfejsy {
    public static void main(String[] args) {
        
    // Nie mozna zrobib obiektu od interfejsu
    // Pojazd poj = new Pojazd();   

    // Mozna stworzyc obiekt dla interfejsu ale od klasy, ktora go implementuje
    Samochod s = new SamochodOsobowy();
	s.PodajIloscMiejsc();
	
    // Tak obiekty mozna tworzyc
    Samochod s1 = new SamochodDostawczy();
    Ciezarowy s2 = new SamochodDostawczy();
	Pojazd p = new SamochodOsobowy();
	int cena = p.PodajCene();
	System.out.println(cena);   
        
        // Tak nie mozna, bo Samochod osobowy nie implementuje Ciezarowy.
        // Ciezarowy s3 = new SamochodOsobowy();
        
    }
}