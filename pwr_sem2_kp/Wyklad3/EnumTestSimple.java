public class EnumTestSimple {
    
    public enum Kolor {
        CZERWONY, ZIELONY, NIEBIESKI;
    }

    public static void main(String[] args) {
 
        Kolor kolor = Kolor.CZERWONY;
        // Kolor kolor1 = new Kolor(); //Tak nie mozna. Definiujemy jak obiekt statyczny
        
        // Wykorzystanie switch
        switch (kolor) {
            case CZERWONY:
                System.out.println("Czerwony");
            break;
            case ZIELONY:
                System.out.println("Zielony");
            break;
            case NIEBIESKI:
                System.out.println("Niebieski");
            break;
            default:
                System.out.println("Niokreslony");
            break;
        }

        // Wykorzystanie equals
        if(kolor.equals(Kolor.CZERWONY)) {
            System.out.println("Zgadza sie");
        }

        if(kolor.equals(Kolor.NIEBIESKI)) {
            System.out.println("Zgadza sie");
        } else {
            System.out.println("Nie zgadza sie");
        }
    }
}