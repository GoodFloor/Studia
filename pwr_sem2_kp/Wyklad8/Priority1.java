class Compute extends Thread {
  public Compute(int i) {
    this.setName("P"+i);
  }
  public void run() {
    int counter = 0;
    System.out.println("** START " + this.getName() + " Priority " + this.getPriority() + " **");
    try { Thread.sleep(1); } catch( InterruptedException e ) {};
    while( counter<10000000 ) { counter++; }
    System.out.println("** END " + this.getName() + " **");
  }
}

public class Priority1 {
  public static void main(String [] args) {
    Compute p0 = new Compute(0);
    Compute p1 = new Compute(1);
    Compute p2 = new Compute(2);
    Compute p3 = new Compute(3);
    Compute p4 = new Compute(4);

    // Ustawienie priorytetow.
    p0.setPriority(Thread.MAX_PRIORITY);
    p4.setPriority(Thread.MIN_PRIORITY);

    // Moze byc tak, ze program nie zakonczy wykonywania watkow demonow.
    p1.setDaemon(true);
    p4.setDaemon(true);
    
    // W wiekszosci przypadkow watki o wyzszym priorytecie beda wykonywane
    // wczesniej.
    p0.start();
    p1.start();
    p2.start();
    p3.start();
    p4.start();
    
  }
}
