import java.io.*;

public class ProcessDemo1 {

   public static void main(String[] args) {
      try {
         // Tworzenie nowego procesu wywolujacego program pr2.exe z parametrami
         System.out.println("Creating Process...");
         Process p = Runtime.getRuntime().exec("pr2.exe e r v c ");

         // Tworznie obiektu BufferedReader  zeby wyswietlic z niego na konsoli
         // Zeby go zrobic nalezy dostac sie do tego co zwraca proces. Robi sie
         // to za pomoca getInputStream
         
         BufferedReader reader = new BufferedReader(
                 new InputStreamReader(p.getInputStream()));
         String line;
         while ((line = reader.readLine()) != null) {
             System.out.println(line);
         }
      
         reader.close();

      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }
}