package fakturfix;

/**
 * Zewnętrzny system bazodanowy
 */
public class Database {
    public Database(String user, String password, String name) {
        String placeholder = user;
        placeholder = password;
        placeholder = name;
        System.out.println("Połączono z bazą " + placeholder);
    }
    public void update(String tableName, String input) {
        /*Operacje bazy danych */
    }
    public String select(String tableName) {
        /*Operacje bazy danych */
        return "placeholder";
    }
}
