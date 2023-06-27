package fakturfix;

/**GRASP
 * Niskie sprzężenie - a właściwie brak sprzężenia z żadną klasą
 * Wysoka spójność - tylko przechowywanie danych pojedynczego produktu
 */
public class Product {
    public String name;
    public double price;
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
