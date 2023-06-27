class Baza {
  	int a; 
	Baza() { a=0; System.out.println("Domyslny konstruktor klasy Baza"); }  
	Baza(int i) { a=i; System.out.println("Konstruktor klasy Baza" ); } 
	public void m1() { System.out.println("Baza.m1()"); }  
	public void m2() { System.out.println("Baza.m2()"); }
}


class Potomna extends Baza {
	Potomna() { super(0); System.out.println("Domyslny konstruktor klasy Potomna"); }  
	Potomna(int i) { a=i; System.out.println("Konstruktor klasy Potomna" ); }  
	public void m3() { System.out.println("Potomna.m3()"); super.m2(); } 
	public void m2() { System.out.println("Potomna.m2()"); }
}

	
public class SuperTest {
	public static void main(String[] args) {   


            System.out.println("new a ------");         
            // Poprawnie. Klasa Potomna dziedziczy po klasie Baza
            Baza a = new Potomna();   

            System.out.println("new b ------");   
            Potomna b = new Potomna(1);  
            
            System.out.println("new c ------");              
            // Poprawnie. Kazda klasa dziedziczy po klasie Object
            Object c = new Baza();

            
            // Niepoprawnie. Nie kazda klasa bazowa jest potomna. 
            // Potomna p = new Baza();
            
            // Niepoprawnie. m3 jest zdefiniwane z klasie Potomna, a nie Baza.
            // a.m3();   
            
            System.out.println("b.m1() ------");  
            b.m1();  
    
            System.out.println("a.m2() ------"); 
            // Wywola sie metoda z klasy Potomna, chociaz jest dla obiektu Baza
            a.m2();    
            System.out.println("b.m3() ------");  
            b.m3();   
		
            // Sprawdza czy a jest typu Potomna
            if( a instanceof Potomna ) {     
		System.out.println("a wskazuje na obiekt klasy Potomna");    
		((Potomna) a).m3();
            }
    
            if( c instanceof Potomna )      
		System.out.println("c wskazuje na obiekt klasy Potomna");  
            }
}
