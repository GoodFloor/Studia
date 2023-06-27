import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * Klasa główna subprogramu pozwalającego na wybór koloru
 */
public class MyColorPicker extends Application {
    /**
     * Metoda uruchamiająca UI programu
     */
    public void start(Stage stage) {
        ColorPicker colorPicker = new ColorPicker();
        Text text = new Text("Wybierz nowy kolor");
        Button accept = new Button("Zatwierdź");
        text.setFont(Font.font("verdana", 25));
        accept.setFont(Font.font("verdana", 25));
        accept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(colorPicker.getValue());
                System.exit(0);
            }
        });
        BorderPane root = new BorderPane(colorPicker, text, null, accept, null);
        Scene scene = new Scene(root, 250, 125);
        

        stage.setTitle("Wybór koloru");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda główna programu
     * @param args parametry wiersza poleceń
     */
    public static void main(String[] args) {
        launch(args);
    }
}
