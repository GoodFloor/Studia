package fakturfix;

import java.util.Scanner;

/**GRASP
 * Niskie sprzężenie - tylko z klasami InvoicesHandler, ClientsHandler i DatabaseAdapter
 * Wysoka spójność - tylko obsługa użytkownika, proste UI
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int mode = 1; //1 - menu główne, 2 - wybór faktury, 3 - widok faktury
        int activeInvoice = 0;
        DatabaseAdapter db = new DatabaseAdapter("admin", "admin", "invoiceDB");
        InvoicesHandler invoices = new InvoicesHandler();
        ClientsHandler clients = new ClientsHandler();

        System.out.println( "Witaj w aplikacji fakturfix!");
        while (run) {
            if(mode == 1) {
                System.out.println( "Wybierz co chcesz zrobić: \n\t1 - stwórz nową fakturę \n\t2 - wyświetl zapisane faktury \n\t3 - edytuj/wyświetl zapisaną fakturę \n\t4 - zapisz/pobierz fakturę z bazy \n\t0 - wyjdź z aplikacji");
                try {
                    int choice = Integer.parseInt(input.nextLine());
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    switch (choice) {
                        case 1:
                            System.out.println("Podaj nazwę faktury: ");
                            String name = input.nextLine();
                            System.out.println("Wpisz ID klienta dla którego chcesz wystawić fakturę, 0 aby anulować lub wpisz nazwę dla nowego klienta:");
                            clients.printClientsShort();
                            String readLine = input.nextLine();
                            int clientId;
                            try {
                                clientId = Integer.parseInt(readLine);
                                if(clientId == 0) {
                                    break;
                                }
                                if(clientId < 0 || clientId > clients.getSize()) {
                                    System.out.println("Błędne dane!");
                                    break;
                                }
                                
                            } catch (Exception e) {
                                clientId = clients.getSize();
                                System.out.println("Podaj NIP klienta o nazwie " + readLine + ": ");
                                String id = input.nextLine();
                                System.out.println("Podaj adres klienta " + readLine + ": ");
                                String address = input.nextLine();
                                System.out.println("Podaj numer telefonu klienta " + readLine + ": ");
                                String phone = input.nextLine();
                                System.out.println("Podaj email klienta " + readLine + ": ");
                                String email = input.nextLine();
                                clients.addClient(readLine, id, address, phone, email);
                            }
                            invoices.addInvoice(name, clients.getClient(clientId));
                            mode = 3;
                            activeInvoice = invoices.getSize() - 1;
                            System.out.println("\033[H\033[2J");
                            System.out.flush();
                            break;
                        case 2:
                            invoices.printInvoices();
                            break;
                        case 3:
                            mode = 2;
                            break;
                        case 4:
                            System.out.println("Wybierz ID faktury do zapisania, wpisz 0 aby anulować: ");
                            invoices.printInvoices();
                            try {
                                int saveInvoice = Integer.parseInt(input.nextLine());
                                System.out.println("\033[H\033[2J");
                                System.out.flush();
                                if(saveInvoice > 0 && saveInvoice <= invoices.getSize()) {
                                    db.saveInvoice(invoices.getInvoice(saveInvoice - 1));
                                }
                            } catch (Exception e) {
                                System.out.println("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Niepoprawny format danych!");
                            }
                                System.out.println("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Błędny wybór!");
                            break;
                        case 0:
                            run = false;
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Błędny wybór!");
                }
            }
            else if(mode == 2) {
                activeInvoice = 0;
                System.out.println("Wybierz ID faktury z którą chcesz pracować lub wpisz 0 aby powrócić do menu głównego: ");
                invoices.printInvoices();
                try {
                    activeInvoice = Integer.parseInt(input.nextLine());
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    if(activeInvoice > 0 && activeInvoice <= invoices.getSize()){
                        mode = 3;
                        activeInvoice--;
                    }
                    else if(activeInvoice == 0) {
                        mode = 1;
                    }
                } catch (Exception e) {
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Błędny wybór!");
                }
            }
            else if(mode == 3) {
                invoices.printInvoice(activeInvoice);
                System.out.println("Wybierz co chcesz zrobić z tą fakturą: \n\t1 - dodać produkt \n\t0 - powrót do menu głównego");
                try {
                    int choice = Integer.parseInt(input.nextLine());
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    switch (choice) {
                        case 0:
                            mode = 1;
                            break;
                        case 1:
                            boolean tryRead = true;
                            String name = "";
                            double price = 0;
                            int quantity = 0;
                            while (tryRead) {
                                try {
                                    System.out.println("Podaj nazwę produktu: ");
                                    name = input.nextLine();
                                    tryRead = false;
                                } catch (Exception e) {
                                    System.out.println("Wprowadzone dane są w niepoprawnym formacie!");
                                }
                            }
                            tryRead = true;
                            while (tryRead) {
                                try {
                                    System.out.println("Podaj cenę jednostkową produktu: ");
                                    price = Double.parseDouble(input.nextLine());
                                    tryRead = false;
                                } catch (Exception e) {
                                    System.out.println("Wprowadzone dane są w niepoprawnym formacie!");
                                }
                            }
                            tryRead = true;
                            while (tryRead) {
                                try {
                                    System.out.println("Podaj ilość: ");
                                    quantity = Integer.parseInt(input.nextLine());
                                    tryRead = false;
                                } catch (Exception e) {
                                    System.out.println("Wprowadzone dane są w niepoprawnym formacie!");
                                }
                            }
                            invoices.addEntry(activeInvoice, name, price, quantity);
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Błędny wybór!");
                }
            }
            

        }
        System.out.println("Do widzenia!");
        input.close();
    }
}
