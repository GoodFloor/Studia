import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasa wątku klienta uruchamianego na serwerze 
 */
public class ServerThread extends Thread {
    private Socket socket;
    /**
     * Konstruktor nowego wątku
     * @param socket Port komunikacji z klientem
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    /**
     * Główna funkcja wątku
     */
    public void run() {
        try {
            InputStream clientInput = socket.getInputStream();
            BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(clientInput));

            OutputStream clientOutput = socket.getOutputStream();
            PrintWriter outputWriter = new PrintWriter(clientOutput, true);

            String inputLine = "";
            String variableType = "";
            Tree<Integer> integerTree = new Tree<Integer>();
            Tree<Double> doubleTree = new Tree<Double>();
            Tree<String> stringTree = new Tree<String>();

////////////////////////// TestTree - drzewo domyślne dla typu INTEGER[dla ułatwienia testowania]
            integerTree.insert(10);
            integerTree.insert(7);
            integerTree.insert(5);
            integerTree.insert(11);
            integerTree.insert(3);
            integerTree.insert(13);
            integerTree.insert(9);
            integerTree.insert(12);
            integerTree.insert(20);
            integerTree.insert(17);
            integerTree.insert(14);
            integerTree.insert(1);
            integerTree.insert(2);
            integerTree.insert(0);
            integerTree.insert(6);
            integerTree.insert(50);
            integerTree.insert(18);
            integerTree.insert(21);
            integerTree.insert(51);
/////////////////////////////

            inputLine = inputBuffer.readLine();
            while (!inputLine.equals("exit")) {
                variableType = inputBuffer.readLine();
                switch (inputLine) {
                    case "search":
                        inputLine = inputBuffer.readLine();
                        switch (variableType) {
                            case "INTEGER":
                                outputWriter.println(integerTree.search(Integer.parseInt(inputLine)));
                                break;
                            case "DOUBLE":
                                outputWriter.println(doubleTree.search(Double.parseDouble(inputLine)));
                                break;
                            case "STRING":
                                outputWriter.println(stringTree.search(inputLine));
                                break;                        
                            default:
                                break;
                        }
                        break;
                    case "insert":
                        inputLine = inputBuffer.readLine();
                        switch (variableType) {
                            case "INTEGER":
                                integerTree.insert(Integer.parseInt(inputLine));
                                break;
                            case "DOUBLE":
                                doubleTree.insert(Double.parseDouble(inputLine));
                                break;
                            case "STRING":
                                stringTree.insert(inputLine);
                                break;                        
                            default:
                                break;
                        }
                        break;
                    case "delete":
                        inputLine = inputBuffer.readLine();
                        switch (variableType) {
                            case "INTEGER":
                                integerTree.delete(Integer.parseInt(inputLine));
                                break;
                            case "DOUBLE":
                                doubleTree.delete(Double.parseDouble(inputLine));
                                break;
                            case "STRING":
                                stringTree.delete(inputLine);
                                break;                        
                            default:
                                break;
                        }
                        break;
                    case "draw":
                        switch (variableType) {
                            case "INTEGER":
                                outputWriter.println(integerTree.draw());
                                break;
                            case "DOUBLE":
                                outputWriter.println(doubleTree.draw());
                                break;
                            case "STRING":
                                outputWriter.println(stringTree.draw());
                                break;                        
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                inputLine = inputBuffer.readLine();
            }
            socket.close();
            System.out.println("A client has disconnected");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
