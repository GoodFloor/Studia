
public class ProcessDemo {

   public static void main(String[] args) {
      try {
         // Tworzenie procesu i wywolanie aplikacji notepad
         System.out.println("Creating Process...");
         Process p = Runtime.getRuntime().exec("notepad.exe");

         // 5 sekund oczekiwania
         Thread.sleep(5000);
         p.destroy();

      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }
}