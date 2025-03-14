package fakturfix;

/**GRASP
 * Niskie sprzężenie - tylko z klasą Entry oraz Client
 * Wysoka spójność - tylko przechowywanie danych pojedynczej faktury
 */
public class Invoice {
    public Entry productList[];
    public String name;
    public Client client;
    public Invoice(String name, Client client) {
        productList = new Entry[0];
        this.name = name;
        this.client = client;
    }
    public void addEntry(String name, double price, int quantity) {
        Entry tempArray[] = new Entry[productList.length + 1];
        for (int i = 0; i < productList.length; i++) {
            tempArray[i] = productList[i];
        }
        tempArray[tempArray.length - 1] = new Entry(name, price, quantity);
        productList = tempArray;
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    public void printInvoice() {
        System.out.println("################################################################");
        client.printClient();
        System.out.println("################################################################");
        System.out.println("Produkt \tCena jedn. \tIlość \tCena łączna");
        for (int i = 0; i < productList.length; i++) {
            String output = (i + 1) + ". " + productList[i].toString();
            System.out.println(output);
        }
        System.out.println("################################################################");
        System.out.println("Całkowita należność: \t\t\t" + this.totalPrice() + " zł");
        System.out.println("################################################################");
    }
    public double totalPrice() {
        double total = 0;
        for (Entry entry : productList) {
            total += entry.subtotal();
        }
        return total;
    }
    public int numberOfElements() {
        return productList.length;
    }
    public String getClientName() {
        return client.name;
    }
    public String toDatabase() {
        String result = "";
        for (Entry entry : productList) {
            result += entry.toDatabase() + "\n";
        }
        return result;
    }
    
}
