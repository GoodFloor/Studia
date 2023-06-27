class Moj1Exception extends Exception {};
class Moj2Exception extends Exception {};
class TablicaBool2 {
  private boolean[] t;
  
  // Kazda metoda deklaruje (po throws) wyj¹tek, ktory moze zwrocic.
  public boolean getValue( int i ) throws Moj2Exception {
    if( (i<0) || (i>=t.length) ) throw new Moj2Exception();
    return t[i]; 
  }  
  public void setValue( int i, boolean b ) throws Moj2Exception {
    if( (i<0) || (i>=t.length) ) throw new Moj2Exception();
    t[i] = b;  
  }  
  TablicaBool2( int i ) throws Moj1Exception {
    if(i<=0) throw new Moj1Exception();
    t = new boolean[i]; 
  }
}
public class TablicaBoolTest2 {  
  public static void main( String[] arg ) {
    TablicaBool2 t;  
    // Jesli metoda moze zwrocic jakis wyjatek, nalezy zrobic catch ktory przechwycic
    try {
        int n = 2;
      t = new TablicaBool2(n);
      t.setValue(20,true);
    }
    // Przechwytywanie wyjatkow w kolejnosci: najpierw specyficzne, potem ogolne
    catch( Moj2Exception e ) {
      System.out.println( "Przekroczenie zakresu" );
    }
    catch( Moj1Exception e ) {
      System.out.println( "Zle inicjowanie tablicy!" );
    }
    catch( Exception e ) {
      System.out.println( "Niespodzianka" );
    }
    // Wywola sie niezaleznie od tego, czy wejdziemy do catch, czy nie
    finally
    {
        System.out.println( "Koniec" );
    }
  }
}