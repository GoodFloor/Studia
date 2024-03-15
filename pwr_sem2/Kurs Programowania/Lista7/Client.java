import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Console;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Główna klasa aplikacji klienta
 */
public class Client extends Application{
    Socket socket;
    PrintWriter serverInput;
    BufferedReader serverOutput;
    Console console;
    TextField output;
    MyPane outputBig;
    /**
     * Główna funkcja GUI klienta
     * @param primaryStage okno
     * @throws Exception wyjątek wyrzucany w razie wystąpienia błędu
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane base = new BorderPane();
        output = new TextField();
        output.setEditable(false);
        outputBig = new MyPane();
        TabPane canvas = new TabPane();
        base.setTop(canvas);
        base.setBottom(output);
        base.setCenter(outputBig);
        canvas.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        MyButton.Type variablesTypes[] = {MyButton.Type.INTEGER, MyButton.Type.DOUBLE, MyButton.Type.STRING};
        String commands[] = {"search", "insert", "delete", "draw"};
        GridPane tabsCanvas[] = new GridPane[variablesTypes.length];
        Tab tabs[] = new Tab[variablesTypes.length];
        for (int i = 0; i < variablesTypes.length; i++) {
            tabsCanvas[i] = new GridPane();
            tabs[i] = new Tab(variablesTypes[i].toString(), tabsCanvas[i]);
            for (int j = 0; j < commands.length; j++) {
                TextField dataField = new TextField();
                MyButton button = new MyButton(commands[j], variablesTypes[i], dataField, this);
                tabsCanvas[i].add(dataField, 0, j);
                tabsCanvas[i].add(button, 1, j);
                if(commands[j] == "draw")
                    dataField.setEditable(false);
            }
            canvas.getTabs().add(tabs[i]);
        }
        primaryStage.setTitle("Drzewo binarne");
        Scene primaryScene = new Scene(base, 800, 800);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        try {
            socket = new Socket("localhost", 52137);
            serverInput = new PrintWriter(socket.getOutputStream(), true);
            serverOutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            console = System.console();
            primaryStage.setOnCloseRequest(event -> {
                try {
                    serverInput.println("exit");
                    socket.close();
                } catch (Exception e) {
                    System.out.println("Połączenie nie zostało zamknięte poprawnie");
                }
            });
        } catch (Exception e) {
            System.out.println("Błąd serwera");
        }
    }
    /**
     * Funkcja wysyłająca polecenie do serwera
     * @param message1 Pierwsza część polecenia - wykonywana komenda
     * @param message2 Druga część polecenia - typ danych (którego drzewa dotyczy komenda)
     * @param message3 Trzecia część polecenia - dodatkowe argumenty jak na przykład wartość której szukamy w drzewie
     */
    public void send(String message1, String message2, String message3) {
        
        //outputBig.setText("");
        try {
            if (message1 == "draw") {
                serverInput.println(message1);
                serverInput.println(message2);
            }
            else if (message1.isEmpty() || message2.isEmpty() || message3.isEmpty()) {
                return;
            }
            else {
                serverInput.println(message1);
                serverInput.println(message2);
                serverInput.println(message3);
            }
            String outputMessage;
            switch (message1) {
                case "search":
                    output.setText("");
                    if (serverOutput.readLine().equals("true")) {
                        output.setText("Podana wartość istnieje w drzewie");
                    }
                    else {
                        output.setText("Podana wartość nie istnieje w drzewie");
                    }
                    break;
                case "insert":
                    output.setText("");
                    send("draw", message2, message3);
                    output.setText("Wykonano");
                    break;
                case "delete":
                    output.setText("");
                	send("draw", message2, message3);
                    output.setText("Wykonano");
                    break;
                case "draw":
                    outputBig.clear();
                    outputMessage = serverOutput.readLine();
                    while (!outputMessage.equals("end")) {
                        outputBig.draw(outputMessage);
                        outputMessage = serverOutput.readLine();
                    }
                    break;
                default:
                    output.setText("Nieprawidłowe polecenie");
                    break;
            }
        } catch (Exception e) {
            output.setText("Błąd serwera: " + e.getMessage());
        }
    }
/**
 * Główna metoda programu klienta
 * @param args argumenty wiersza poleceń
 */
    public static void main(String[] args) {
        launch(args);
    }
}
