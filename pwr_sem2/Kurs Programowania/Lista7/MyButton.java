import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * Klasa obsługująca przyciski wysyłające komendy
 */
public class MyButton extends Button{
    TextField source;
    Client host;
    Type variableType;
    /**
     * Obsługiwane typy danych
     */
    public enum Type {
        /**
         * Typ obsługujący drzewo liczb całkowitych
         */
        INTEGER, 
        /**
         * Typ obsługujący drzewo liczb zmiennoprzecinkowych
         */
        DOUBLE, 
        /**
         * Typ obsługujący drzewo ciągów znaków
         */
        STRING;
    }
    /**
     * Konstruktor przycisku
     * @param name nazwa wyświetlana na przycisku = wysyłana komenda
     * @param variableType typ zmiennej dla której przycisk wywołuje komendę
     * @param source źródło pobierania dodatkowych danych dla komendy
     * @param host aplikacja klienta
     */
    MyButton(String name, Type variableType, TextField source, Client host) {
        super(name);
        this.source = source;
        this.host = host;
        this.variableType = variableType;
        setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Funkcja wywoływana po wciśnięciu przycisku
             * @param event rodzaj wydarzenia
             */
            @Override
            public void handle(ActionEvent event) {
                host.send(name, variableType.toString(), source.getText());
                source.setText("");
            }
        });
    }
    

}
