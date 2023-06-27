class Moj1Exception extends Exception {};
class Moj2Exception extends Exception {};

class TablicaBool {

    private boolean[] t;

    public boolean getValue( int i ) throws Moj2Exception{
        if( (i<0) || (i>=t.length) ) throw new Moj2Exception();
            return t[i];
    }

    public void setValue( int i, boolean b ) throws Moj2Exception{
        if( (i<0) || (i>=t.length) ) throw new Moj2Exception();
           t[i] = b;
  }

    TablicaBool( int i ) throws Moj1Exception{ 
	if(i<=0) throw new Moj1Exception();
	t = new boolean[i]; }
}

public class TablicaBoolTest {
	public static void main( String[] arg ) {
		
	TablicaBool t;
	int n = 20;
	try { t = new TablicaBool(n); 
	}
	catch( Moj1Exception e ) { 
		System.out.println( "Zle inicjowanie tablicy! (1)" );
		return;
	}
	catch( OutOfMemoryError e ){      
		System.out.println( "Za duza tablica! (3)" );
		return;
	}

	try { t.setValue(20,true); 
	}    
	catch( Moj2Exception e ) {  
		System.out.println( "Przekroczenie zakresu (1)" ); 
	}
	n = 0;
	try { t = new TablicaBool(0);
            t.setValue(20,true); 
	}
	catch( Moj2Exception e ) {  
		System.out.println( "Przekroczenie zakresu (2)" ); 
	}
	catch( Moj1Exception e ) { 
		System.out.println( "Zle inicjowanie tablicy! (2)" );
		return;
	}
}
}
