public class EnumTest {
    
    // Podobna definicja do klasy wewnetrznej
    public enum Kolor {
        CZERWONY(true), 
        ZIELONY(false), 
        NIEBIESKI(false);
 
        boolean ladny;
        int numer;
        
        private Kolor(boolean czyLadny) {
            ladny = czyLadny;
        }      
        public String czyLadnyKolor(Kolor kolor){
            return (kolor.ladny) ? "ladny" : "brzydki";
        }
    }
 
    public static String czyLadny(Kolor kolor) {
        return (kolor.ladny) ? "ladny" : "brzydki";
    }
        
    public static void main(String[] args) {
 
        Kolor kolor = EnumTest.Kolor.CZERWONY;      
        System.out.println("Kolor czerwony jest "+czyLadny(kolor));
        
        String str = EnumTest.Kolor.NIEBIESKI.czyLadnyKolor(kolor);
        System.out.println("Kolor niebieski jest "+str);
 
        Kolor[] kolory = Kolor.values(); 
        for(int i=0; i<kolory.length; i++) {
            System.out.println(kolory[i]+" jest "+czyLadny(kolory[i]));
        }
    }
 

 
}