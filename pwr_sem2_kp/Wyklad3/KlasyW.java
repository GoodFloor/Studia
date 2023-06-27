// Klasa otaczajaca
class KlasaA {
    int PoleA;
    static int PoleF;

    // Obiekty klasy wewnetrznej
    KlasaB b = new KlasaB(); 
    KlasaD g = new KlasaD();
       
    public void m2() { 
        System.out.println("m2()"); 
        b.PoleB=3;// Odwolanie do pola z klasy prywatnej (nawet prywatnego)
        b.m3();  
        g.m4();// Odwolanie do metody klasy wewnatrznej typu private
    } 
	
    // Klasa wewnetrzna
    class KlasaB{
        private int PoleB;
        // Odwolanie do pola z klasy zewnetrznej
        public void m3() {
            PoleA=1; 
            System.out.println("m3()"); 
            KlasaC.PoleC=5; // OK. PoleC jest statyczne
            //KlasaC.m4();//Nie mozna. m4 nie jest metoda statyczna
            }
        }
    // Statyczna klasa wewnetrzna
    static class KlasaC{
        static int PoleC;
        public void m4() {PoleF=1;} // Odwolanie do statycznego pola klasy otaczajacej      
    }
    
    // Klasa wewnetrzna prywatna
    private class KlasaD{
        private int PoleD;
        // Odwolanie do pola z klasy zewnetrznej
        public void m5() {PoleA=5;}
        }
}	

public class KlasyW {
    public static void main(String[] args) {          
        // Tworzenie obiektow klasy wewnetrznej(sposoby)
        KlasaA a = new KlasaA();
        KlasaA.KlasaB c = a.new KlasaB();
        KlasaA.KlasaB d = new KlasaA().new KlasaB();
        d.m3();  // OK. m3 jest publiczne
        // d.PoleB=5; // Nie mozna. PoleB jest private
              
        KlasaA.KlasaC.PoleC=5;// OK. Odwolanie do statycznego pola statycznej wewnetrznej
        
        // Nie mozna bo KlasaD jest prywatna (dostep z poza klasy 
        // otaczajacej jest zabroniony)
        //KlasaA.KlasaD e = new KlasaA().new KlasaD();
    }
}
