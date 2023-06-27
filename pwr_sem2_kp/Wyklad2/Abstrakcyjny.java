// Klasa abstrakcyjna. Nie mozna tworzyc obiektu dla niej.
abstract class Pojazd {    
    public int Cena;
    // Metoda abstrakcyjna, bez implementacji
    public abstract int PodajCene();
    // Metoda zwykla z implementacja;
    public void PodajCene1(int cena){
        this.Cena=cena;
    };

}
class Ciezarowy extends Pojazd{
    public int PodajLadownosc(){
    	return 500;};
    
    public int PodajCene() {
    return 500;}
}
class SamochodDostawczy extends Pojazd{   
    public int PodajIloscMiejsc() {
    return 8;}
    
    public int PodajLadownosc() {
    return 500; }
    
    public int PodajCene(){
       return 60000;}
}
public class Abstrakcyjny {
    public static void main(String[] args) {

         // Nie mozna stworzyc obiektu, bo Pojazd to klasa abstrakcyjna
        //Pojazd s2 = new Pojazd();
         
        // Tworzenie obiektow od klas nieabstrakcyjnych
        Pojazd s = new Ciezarowy();
	    s.PodajCene();	
        Pojazd s1 = new SamochodDostawczy();
        s.PodajCene1(100);

       
        // Mozna stworzyc tablice dla Pojaz i przypisac obiekty tego typu
        Pojazd p[] = new Pojazd[2];     
        p[0]=s;
        p[1]=s1;
            
        System.out.println(p[0].PodajCene());
        System.out.println(p[1].PodajCene());
    }
}