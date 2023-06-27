class Test {
  int a;
  Test( int a ) { this.a=a; System.out.println( "Utworzono "+a ); }
  protected void finalize() throws Throwable { System.out.println( "Zniszczono "+a ); }
}
public class DestruktorTest {
  public static void main(String[] arg) {
    Test a = new Test(1), b = new Test(2), c = new Test(3); // tworzymy 1,2,3
    
    a = b;
    try{ Thread.sleep(1000); } catch( InterruptedException e ) {};
    System.gc(); // usuwamy 1
    try{ Thread.sleep(1000); } catch( InterruptedException e ) {};
    b = c;
    a = new Test(4); // tworzymy 4
    try{ Thread.sleep(1000); } catch( InterruptedException e ) {};
    System.gc(); // usuwamy 2
    try{ Thread.sleep(1000); } catch( InterruptedException e ) {};
    b = c = null;
    try{ Thread.sleep(1000); } catch( InterruptedException e ) {};
    System.gc(); // usuwamy 3
    a = null;
    try{ Thread.sleep(1000); } catch( InterruptedException e ) {};
    System.gc(); // usuwamy 4
    try{ Thread.sleep(1000); } catch( InterruptedException e ) {};

    
    
  }
}
