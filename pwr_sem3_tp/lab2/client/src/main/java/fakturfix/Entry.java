package fakturfix;

/**GRASP
 * Niskie sprzężenie - tylko z klasą Product
 * Wysoka spójność - klasa przechowuje dane dotyczące pojedynczego elementu faktury
 */
public class Entry {
    private Product product;
    public int quantity;
    public Entry(String name, double price, int quantity) {
        product = new Product(name, price);
        this.quantity = quantity;
    }
    public double subtotal() {
        return product.price * quantity;
    }
    public String getName() {
        return product.name;
    }
    public double getPrice() {
        return product.price;
    }
    public String toString() {
        return getName() + "\t" + getPrice() + " zł   \t" + quantity + "\t" + subtotal() + " zł";
    }
    public String toDatabase() {
        return getName() + ";" + getPrice() + ";" + quantity + ";" + subtotal() + ";"; 
    }
}
