import java.io.*;

class CountBytes {
  public static void main(String[] args)
    throws FileNotFoundException, IOException
  {
    // Zliczanie ilosci bytow w pliku  
    FileInputStream in = new FileInputStream("InFile.txt");
    int total = 0;
    while (in.read() != -1) total++;

    System.out.println(total + " bytes");
    in.close();
  }
}
        