import java.io.*;

public class CharStreamTest {
   public static void main(String args[])throws IOException {

      System.out.println("Podaj 10 znakow:");
      CharArrayWriter bOutput = new CharArrayWriter(12);
      while( bOutput.size()< 10 ) {
         // Wpisanie znakow z konsoli
         bOutput.write(System.in.read()); 
      }

      // Przepisanie znakow do tablicy
      char b [] = bOutput.toCharArray();
      System.out.println("Wypisywanie podanych znakow");
      for(int x= 0 ; x < b.length; x++) {
         System.out.print(b[x]  + "   "); 
      }
      System.out.println("   ");

      // Wczytanie tablicy znakow do strumienia
      int c;
      CharArrayReader bInput = new CharArrayReader(b);
      System.out.println("Konwertowanie znakow do duzej litery: " );
         while(( c= bInput.read())!= -1) {
            System.out.println(Character.toUpperCase((char)c));
         }
         bInput.reset(); 
   }
}