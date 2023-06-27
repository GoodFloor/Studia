// Klasa w¹tku
class DisplayMessage implements Runnable {
  private String message;
  public DisplayMessage(String message) {
    this.message = message;
  }
  // G³ówna metoda w¹tku
  public void run() {
    // Pêtla nieskoñczona. W¹tek siê nie koñczy  
    while(true) {
      System.out.println(message);
    }
  }
}

public class RunnableDemo {
  public static void main(String [] args) {
    
    // Tworzenie i start w¹tku 1  
    System.out.println("Creating the hello thread...");
    DisplayMessage hello = new DisplayMessage("Hello");
    Thread thread1 = new Thread(hello);
    System.out.println("Starting the hello thread...");
    thread1.start();
    
    // Tworzenie i start w¹tku 2    
    System.out.println("Creating the goodbye thread...");
    DisplayMessage bye = new DisplayMessage("Goodbye");
    Thread thread2 = new Thread(bye);
    System.out.println("Starting the goodbye thread...");
    thread2.start();
    
    // W¹tki nie s¹ zsynchronizowane. Wypisywanie woadomosci Hello i Goodbye 
    // te¿ nie jest zsynchronizowane
  }
}

