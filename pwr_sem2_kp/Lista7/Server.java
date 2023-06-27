import java.net.ServerSocket;
import java.net.Socket;

/**
 * Główna klasa serwera
 */
public class Server {
    /**
     * główna metoda serwera
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(52137)) {
            System.out.println("Server started successfully. Listening on port 52137");
            boolean running = true;
            while (running) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                new ServerThread(socket).start();
            }
        } catch (Exception e) {
            System.out.println("Server exception");
        }
    }
}
