abstract class Pojazd {    
    public abstract int PodajCene();
    public int PodajCene1(){return 500;};
	
}
class Ciezarowy extends Pojazd{
    public int PodajLadownosc()
    {return 500;};
    
    public int PodajCene()
    {return 500;}
}
class SamochodDostawczy extends Pojazd{   
    public int PodajIloscMiejsc()
    {return 8;}
    
    public int PodajLadownosc()
    {return 500; }
    
    public int PodajCene()
    {   return 60000;}
}
public class Abstrakcyjny {
    public static void main(String[] args) {
           
	    Pojazd s = new Ciezarowy();
    
	    s.PodajCene();			
            Pojazd s1 = new SamochodDostawczy();

            //Pojazd s2 = new Pojazd();

            Pojazd p[] = new Pojazd[2];     
  
            p[0]=s;
            p[1]=s1;
            
            System.out.println(p[0].PodajCene());
            System.out.println(p[1].PodajCene());
    }
}