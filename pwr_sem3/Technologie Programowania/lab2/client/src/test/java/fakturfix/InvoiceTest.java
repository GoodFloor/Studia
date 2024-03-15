package fakturfix;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InvoiceTest 
{
    Client client;
    double PRICE1, PRICE2;
    int QUANTITY1, QUANTITY2;
    Invoice invoice;
    public InvoiceTest() {
        client = new Client("nazwa", "123456", "ul. Prosta 1 Wrocław", "123 456 789", "przykladowy.adres@szkrzynka.com");
        PRICE1 = Math.ceil(Math.random() * 10000 + 1) / 100;
        PRICE2 = Math.ceil(Math.random() * 10000 + 1) / 100;
        QUANTITY1 = (int)Math.ceil(Math.random() * 100 + 1);
        QUANTITY2 = (int)Math.ceil(Math.random() * 100 + 1);
        invoice = new Invoice("Test", client);
        invoice.addEntry("Nazwa 1", PRICE1, QUANTITY1);
        invoice.addEntry("Nazwa 2", PRICE2, QUANTITY2);
    }
    @Test
    public void totalPriceTest() {
        assertTrue(invoice.totalPrice() == (PRICE1 * QUANTITY1) + (PRICE2 * QUANTITY2));
    }
    @Test
    public void numberOfElementsTest() {
        assertTrue(invoice.numberOfElements() == 2);
    }
    @Test
    public void getClientName() {
        assertTrue(invoice.getClientName() == "nazwa");
    }
}
 