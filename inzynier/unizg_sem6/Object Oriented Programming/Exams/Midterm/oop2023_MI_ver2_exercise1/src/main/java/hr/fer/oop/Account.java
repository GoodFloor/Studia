package hr.fer.oop;

/**
 * It specifies the user account functionality.
 */
public interface Account {
    /**
     * Method which executes the transaction and updates the balance.
     * @param value may be positive (increase of balance) and negative.
     */
    public void performTransaction(int value);
}
