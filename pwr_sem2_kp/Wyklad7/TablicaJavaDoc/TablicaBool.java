/**
 *  Klasa definiujaca wyjatek wykorzystywany w klasie {@link TablicaBool TablicaBool}
 */
class MojException extends Exception {
  /** Konstruktor podstawowy 
   *  @param w komunikat b³êdu
   */
  MojException(String w) { super(w); };
}

/** 
 *  Klasa definiuj¹ca tablice boolowska
 *  @version 1.0
 *  @author Wojciech Macyna */
public class TablicaBool {

  /** Tablica przechowujaca wartosci */
  private boolean[] t;

  /** Funkcja zwracajaca wartosci elementu tablicy 
   *  @param i indeks zwracanego elementu
   *  @return wartosc zwracanego elementu
   *  @throws MojException wyjatek w przypadku blednych indeksow
   *  @see TablicaBool
   *  @see TablicaBool
    */
  public boolean getValue( int i ) throws MojException
  {
    if( (i<0) || (i>=t.length) ) throw new MojException("Przekroczenie zakresu");
    return t[i];
    /// 
  }

  /** Funkcja ustawiajaca wartosc elementu tablicy 
   *  @param i indeks ustawianego elementu
   *  @param b wartosc ustawianego elementu
   *  @throws MojException wyjatek w przypadku blednych indeksow
   * @see TablicaBool#getValue(int) 
   */
  public void setValue( int i, boolean b ) throws MojException
  {
    if( (i<0) || (i>=t.length) ) throw new MojException("Przekroczenie zakresu");
    t[i] = b;
  }

  /** Podstawowy konstruktor
   *  @param i deklarowana wielkosc tablicy
   *  @throws MojException wyjatek w przypadku blednego rozmiaru 
   */
  TablicaBool( int i ) throws MojException
  {
     if(i<=0) throw new MojException("Za mala wielkosc danych");
     try {
       t = new boolean[i];
     }
     catch( OutOfMemoryError e ) {
       throw new MojException("Za malo pamieci");
     }
  }

/**
 * Glowna funkcja testujaca klase
 * @param arg 
 */
  public static void main( String[] arg ) {
    TablicaBool t;
    int n = 1<<4;

    try {
      t = new TablicaBool(n);
      t.setValue(20,true);
    }
    catch( Exception e ) {
      System.out.println("2 - "+ e.getMessage() );
    }
    catch( Error e ) {
      System.out.println("3 - "+ e.getMessage() );
    }
  }
}

