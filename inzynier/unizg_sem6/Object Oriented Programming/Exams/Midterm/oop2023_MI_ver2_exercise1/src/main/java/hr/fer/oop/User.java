package hr.fer.oop;

/**
 * It specifies a user account.
 */
public abstract class User {
    /**It specifies the name.*/
    protected String name;
    /**It models the balance.*/
    protected int balance;
    /**It models the password.*/
    protected String password;

    /**
     * Basic constructor which sets the name, password and the balance.
     * @param name
     * @param balance
     * @param password
     */
    public User(String name, String password, int balance) {
        this.name = name;
        this.balance = balance;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public abstract void changePassword(String password);    
}
