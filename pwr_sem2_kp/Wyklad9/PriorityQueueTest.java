import java.util.*;  

// Typ obiektow do przechowywania
class Book implements Comparable<Book>{  
    int id;  
    String name,author,publisher;  
    int quantity;  
    public Book(int id, String name, String author, String publisher, int quantity) {  
        this.id = id;  
        this.name = name;  
        this.author = author;  
        this.publisher = publisher;  
        this.quantity = quantity;  
    }  
    // Okreslenie priorytetu na obiektach ksiazka(wedlug id)
    public int compareTo(Book b) {  
        if(id>b.id){  
            return 1;  
        }else if(id<b.id){  
            return -1;  
        }else{  
        return 0;  
        }  
    }  
}  

// Kolejka priorytetowa. Obiekty sa w okreslonym porzadku

public class PriorityQueueTest {  
    public static void main(String[] args) {  
        Queue<Book> queue=new PriorityQueue<Book>();  
        //Tworzenie obiektow  
        Book b1=new Book(121,"Let us C","Yashwant Kanetkar","BPB",8);  
        Book b2=new Book(233,"Operating System","Galvin","Wiley",6);  
        Book b3=new Book(101,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);  
    
        //Dodawanie do kolejki 
        queue.add(b1);  
        queue.add(b2);  
        queue.add(b3);  
        System.out.println("Rozmiar kolejki: " + queue.size()); 
        System.out.println(); 

        System.out.println("Wypisanie elementow kolejki:"); 

        for(Book d:queue){  
            System.out.println(d.id+" "+d.name+" "+d.author+" "+d.publisher+" "+d.quantity);  
            }  
        System.out.println(); 
    
        
        // Pobieranie i usuwanie obiektu z kolejki
        System.out.println("Pobranie i usuniecie pierwszego elementu kolejki:"); 
        Book b = queue.poll();
        System.out.println(b.id+" "+b.name+" "+b.author+" "+b.publisher+" "+b.quantity);  
        System.out.println(); 

        // Pobieranie ale nie usuwanie
        System.out.println("Pobranie ale nie usuniecie elementu kolejki:"); 
        Book c = queue.peek();
        System.out.println(c.id+" "+c.name+" "+c.author+" "+c.publisher+" "+c.quantity);
        System.out.println(); 
        
        // Usuwanie obiektu
        System.out.println("Usuniecie elementu kolejki:");  
        queue.remove();  
        System.out.println(); 

        // Wypisywanie obiektow
        System.out.println("Wypisanie elementow kolejki:"); 
        for(Book d:queue){  
            System.out.println(d.id+" "+d.name+" "+d.author+" "+d.publisher+" "+d.quantity);  
            }  
    }  
}  