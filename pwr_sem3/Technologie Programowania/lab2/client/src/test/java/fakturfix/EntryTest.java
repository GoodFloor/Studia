package fakturfix;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EntryTest 
{
    Entry entry;
    String NAME;
    double PRICE;
    int QUANTITY;
    public EntryTest() {
        NAME = "Mleko";
        PRICE = 4.99;
        QUANTITY = 12;
        entry = new Entry(NAME, PRICE, QUANTITY);
    }
    @Test
    public void subtotalTest(){
        assertTrue(entry.subtotal() == 59.88);
    }
    @Test
    public void getNameTest() {
        assertTrue(entry.getName() == NAME);
    }
    @Test
    public void getPriceTest() {
        assertTrue(entry.getPrice() == PRICE);
    }
    
}
 