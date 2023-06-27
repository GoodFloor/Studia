import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class ListEx {

  public static void main(String args[]) {
      
    // Tworznie listy. Elementy moga sie powtarzac
    List<String> names = new ArrayList<String>();
    names.add("Nowak"); names.add("Kowalski"); 
    names.add("Bielecki"); names.add("Adamski");
    names.add("Kowalski");

    System.out.println("Wszystkie elementy:");
    for(String s : names) System.out.println("-> "+s);
    
    // Sortowanie listy
    System.out.println("Lista posortowana:");
    Collections.sort(names); System.out.println(names);
    // Mieszanie listy
    System.out.println("Lista pomieszana:");
    Collections.shuffle(names); System.out.println(names);
    //Odwaracanie listy
    System.out.println("Lista odwrocona:");
    Collections.reverse(names); System.out.println(names);

    System.out.println("Najmniejszy element:");
    System.out.println(Collections.min(names));
    System.out.println("Najwiekszy element:");
    System.out.println(Collections.max(names));

    // Pobieranie z listy elementu o okreslonym indeksie
    System.out.println("Element na indeksie 2:");
    System.out.println(names.get(2));

    // Usuwanie z listy elementu i okreslonym indeksie
    System.out.println("Po usunieciu elementu 2");
    names.remove(2); System.out.println(names);

    // Usuwanie elementu o danej nazwie
    System.out.println("Po usunieciu Kowalskiego:");
    names.remove("Kowalski"); System.out.println(names);

    System.out.println("Sprawdzanie czy jest Kowalski:");
    System.out.println(names.contains("Kowalski"));
  }
}
