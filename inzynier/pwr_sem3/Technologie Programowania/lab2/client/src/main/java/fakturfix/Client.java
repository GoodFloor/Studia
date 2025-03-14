package fakturfix;

/**GRASP
 * Niskie sprzężenie - a właściwie brak sprzężenia z żadną klasą
 * Wysoka spójność - tylko przechowywanie danych pojedynczego klienta
 */
public class Client {
    public String name;
    private String id;
    private String address;
    private String phone;
    private String email;

    public Client(String name, String id, String address, String phone, String email) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    public void printClient() {
        System.out.println("Nazwa klienta: " + name + "\nNIP: " + id + "\nAdres: " + address + "\nTelefon: " + phone + "\nEmail: " + email);
    }
    public String toString() {
        return name + "\t\t" + address;
    }
    public String toDatabase() {
        return name + ";" + address;
    }
}
