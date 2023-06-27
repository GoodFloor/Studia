import java.io.*;

// Uzywanie strumienia bytow
public class ByteStreamTest {
   public static void main(String args[])throws IOException {
      System.out.println("Podaj 10 znakow:");
      ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
      while( bOutput.size()<= 10 ) {
         // Wpisanie znakow z konsoli
         bOutput.write(System.in.read()); 
      }

      // Przepisanie znakow do tablicy
      byte b [] = bOutput.toByteArray();
      System.out.println("Wypisywanie podanych znakow");
      for(int x= 0 ; x < b.length; x++) {
         // printing the characters
         System.out.print((char)b[x]  + "   "); 
      }
      System.out.println("   ");

      
      // Wczytanie tablicy znakow do strumienia
      int c;
      ByteArrayInputStream bInput = new ByteArrayInputStream(b);
      System.out.println("Konwertowanie znakow do duzej litery:" );
         while(( c= bInput.read())!= -1) {
            System.out.println(Character.toUpperCase((char)c));
         }
         bInput.reset(); 
   }
}