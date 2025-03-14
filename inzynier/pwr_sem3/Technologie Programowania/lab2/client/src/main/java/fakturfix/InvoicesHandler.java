package fakturfix;

/**GRASP
 * Niskie sprzężenie - tylko z klasą Invoice
 * Wysoka spójność - tylko przechowywanie listy faktur i jej obsługa
 */
public class InvoicesHandler {
    private Invoice invoices[];

    public InvoicesHandler() {
        invoices = new Invoice[0];
    }
    public void addInvoice(String name, Client client) {
        Invoice tempInvoices[] = new Invoice[invoices.length + 1];
        for (int i = 0; i < invoices.length; i++) {
            tempInvoices[i] = invoices[i];
        }
        tempInvoices[invoices.length] = new Invoice(name, client);
        invoices = tempInvoices;
    }
    public void printInvoices() {
        System.out.println("################################################################################################");
        System.out.println("ID faktury \tNazwa faktury\tKlient\t\tIlość pozycji \tCałkowita kwota faktury");
        for (int i = 0; i < invoices.length; i++) {
            System.out.println((i + 1) + "\t\t" + invoices[i].name + "\t\t" + invoices[i].getClientName() + "\t\t" +invoices[i].numberOfElements() + "\t\t" + invoices[i].totalPrice());
        }
        System.out.println("################################################################################################");
    }
    public void printInvoice(int id) {
        invoices[id].printInvoice();
    }
    public void addEntry(int id, String name, double price, int quantity) {
        invoices[id].addEntry(name, price, quantity);
    }
    public int getSize() {
        return invoices.length;
    }
    public Invoice getInvoice(int i) {
        return invoices[i];
    }
     
}
