package fakturfix;

/**GRASP
 * Niskie sprzężenie - tylko z klasą Client
 * Wysoka spójność - tylko przechowywanie listy użytkowników i jej obsługa
 */
public class ClientsHandler {
    private Client clients[];

    public ClientsHandler() {
        clients = new Client[0];
    }
    public void addClient(String name, String id, String address, String phone, String email) {
        Client tempClients[] = new Client[clients.length + 1];
        for (int i = 0; i < clients.length; i++) {
            tempClients[i] = clients[i];
        }
        tempClients[clients.length] = new Client(name, id, address, phone, email);
        clients = tempClients;
    }
    public void printClientsShort() {
        System.out.println("################################################################");
        System.out.println("ID \tNazwa klienta\tAdres klienta");
        for (int i = 0; i < clients.length; i++) {
            System.out.println((i + 1) + ". " + clients[i].toString());
        }
        System.out.println("################################################################");
    }
    public int getSize() {
        return clients.length;
    }
    public Client getClient(int id) {
        return clients[id];
    }
    
}
