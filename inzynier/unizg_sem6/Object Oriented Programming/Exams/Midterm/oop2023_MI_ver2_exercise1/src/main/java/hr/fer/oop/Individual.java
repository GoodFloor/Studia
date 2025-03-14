package hr.fer.oop;

/**
 * It models an individual (private) user entity.
 */
public class Individual extends User implements Account{

    /**It specifies the transaction limit, i.e. the maximum amount that can be used in a transaction (regardless of whether it is payment or payout).*/
    private final int limit;
    /**It represents the address.*/
    private String address;

    /**
     * Basic constructor that sets all of parameters
     * @param name user name
     * @param password user password
     * @param balance user initial balance
     * @param address user address
     * @param limit transaction limit
     */
    public Individual(String name, String password, int balance, String address, int limit) {
        super(name, password, balance);
        this.limit = limit;
        this.address = address;
    }
    
    public int getLimit() {
        return limit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void performTransaction(int value) {
        if (Math.abs(value) <= limit && -value <= balance ) {
            balance += value;
        }
    }

    @Override
    public void changePassword(String newPassword) {
        if (newPassword.length() >= 6) {
            password = newPassword;
        }
    }
}
