package fakturfix;

/**GRASP
 * Niskie sprzężenie - tylko z klasami Invoice i Client oraz z zewnętrzną bazą danych
 * Wysoka spójność - obsługa zewnętrznej bazy danych
 * Indirection - klasa jest pośrednikiem między aplikacją a zewnętrznym systemem bazodanowym
 */
public class DatabaseAdapter {
    private Database db;
    public DatabaseAdapter(String user, String password, String databaseName) {
        db = new Database(user, password, databaseName);
    }
    public void saveInvoice(Invoice invoice) {
        db.update("invoices", invoice.toDatabase());
        saveClient(invoice.client);
        System.out.println("Zapis udany!");
    }
    public void saveClient(Client client) {
        db.update("clients", client.toDatabase());
    }
}
