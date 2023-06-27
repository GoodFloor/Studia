// Klasa watku
class DisplayMessage2 extends Thread {
  private String message;
  private boolean exit;

  public DisplayMessage2(String message) {
    this.message = message;
    exit=false;
  }
  // Glowna metoda watku
  public void run() {

    int i = 0;
    while(!exit) {
      System.out.println(message + ": " + i);
      i++;
      try { Thread.sleep(100); }catch (InterruptedException e) {}
    }
    System.out.println(message + " Stopped.");
  }
  public void stopThread()
  {
      exit = true;
  }
}

public class RunnableDemo2 {
  public static void main(String [] args) {
    
    // Tworzenie i start watku 1  
    System.out.println("Creating the hello thread...");
    DisplayMessage2 hello = new DisplayMessage2("Hello");
    System.out.println("Starting the hello thread...");
    hello.start();
   
    // Tworzenie i start watku 2    
    System.out.println("Creating the goodbye thread...");
    DisplayMessage2 bye= new DisplayMessage2("Goodbye");
    System.out.println("Starting the goodbye thread...");
    bye.start();
    
    try {
      Thread.sleep(2000); // Usypianie watku glownego programu.
      hello.stopThread(); // zatrzymanie watku 1
      bye.stopThread(); // zatrzymanie watku 2
  }
  catch (InterruptedException e) {
      System.out.println("Caught:" + e);
  }
    

  }
}

