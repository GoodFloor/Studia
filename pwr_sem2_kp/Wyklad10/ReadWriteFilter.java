import java.io.*;

public class ReadWriteFilter {
  public static void main(String args[]) throws IOException {
    // Tworzenie obiektu pliku do zapisu oraz strumienia zapisywania do pliku.  
    FileOutputStream fos = new FileOutputStream("mydata");
    DataOutputStream dos = new DataOutputStream(fos);
    dos.writeInt(23);
    dos.writeDouble(145.10);
    dos.writeInt('B'+1);
    dos.writeBoolean(true);
    dos.writeChar('Y');
    dos.close();
    fos.close();

   // Tworzenie obiektu pliku do odczytu oraz strumienia odczytywania z pliku. 
    FileInputStream fis = new FileInputStream("mydata");
    DataInputStream dis = new DataInputStream(fis);
    System.out.println(dis.readInt());
    System.out.println(dis.readDouble());
    System.out.println(dis.readInt());
    System.out.println(dis.readBoolean());
    System.out.println(dis.readChar());
    dis.close();
    fis.close();
  }
}
