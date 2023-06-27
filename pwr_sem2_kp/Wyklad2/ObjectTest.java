class TestObject 
{	
    public int a;  	
    TestObject( int i ){ a=i;}
	
    public int getValue() { 
	return a; }
}

public class ObjectTest {
    public static void main(String[] arg) {
   
    TestObject a=new TestObject(1);	
    TestObject b=new TestObject(2);
  
    
    Object c;
    c= new TestObject(1) ;
    
    // Wykorzystanie metod odziedziczonych z klasy Object
    
    System.out.println("Klasa obiektu a: "+a.getClass());    
    System.out.println("Hash obiektu a: "+a.hashCode());
    
    System.out.println("Hash obiektu b: "+b.hashCode());
    
    System.out.println("Porownanie a z a: "+a.equals(a));    
    System.out.println("Porownanie a z b: "+a.equals(b));

    System.out.println("Klasa obiektu c: "+c.getClass());
    System.out.println("Porownanie c z a: "+a.equals(c));

    }
}
