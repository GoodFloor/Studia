import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetEx {

  public static void main(String args[]) {
    // Tworzenie zbioru posortowanego
    SortedSet<String> names = new TreeSet<String>();
    names.add("Nowak"); names.add("Kowalski"); 
    names.add("Bielecki"); names.add("Adamski");
    names.add("Kowalski");
          
    System.out.println("Wszystkie elementy:");
    for(String s : names) System.out.println("-> "+s);
    
    System.out.println("Najmniejszy element:");
    System.out.println(Collections.min(names));

    System.out.println("Najwiekszy element:");
    System.out.println(Collections.max(names));

    System.out.println("Po usunieciu Kowalskiego:");
    names.remove("Kowalski"); System.out.println(names);

    System.out.println("Sprawdzanie czy jest Kowalski:");
    System.out.println(names.contains("Kowalski"));
  }
}
