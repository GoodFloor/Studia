import java.util.Collections;
import java.util.LinkedList;

public class SortedListEx {

  public static void main(String args[]) {
      
    // Tworzenie listy  (kolejka dwukierunkowa)
    LinkedList<String> names = new LinkedList<String>();
    names.add("Nowak"); names.add("Kowalski"); 
    names.add("Bielecki"); names.add("Adamski");
    // Dodawanie elementu na poczatek (koniec) listy
    names.addFirst("Kowalski"); names.addLast("Adamski");
 
    System.out.println("Wszystkie elementy:");
    for(String s : names) System.out.println("-> "+s);


    System.out.println("Najmniejszy element:");
    System.out.println(Collections.min(names));
    System.out.println("Najwiekszy element:");
    System.out.println(Collections.max(names));

    // Pobieranie pierwszego elementu listy
    System.out.println("Pierwszy element:");
    System.out.println(names.getFirst());
    System.out.println(names.get(0));

    // Usuwanie elementu 2
    System.out.println("Po usunieciu elementu 2");
    names.remove(2); System.out.println(names);

    // Usuwanie elementu Kowalski
    System.out.println("Po usunieciu Kowalskiego:");
    names.remove("Kowalski"); System.out.println(names);
    
    System.out.println("Czy jest Kowalski?");
    System.out.println(names.contains("Kowalski"));
  }
}
