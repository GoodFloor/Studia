import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Klasa mojego menu kontekstowego - z opcjami obrotu i zmiany koloru
 */
public class MyContextMenu extends ContextMenu {
    /**
     * Konstruktor obiektu menu kontekstowego
     * @param root figura którą menu kontekstowe będzie obługiwać
     */
    MyContextMenu(Shape root) {
        Menu rotate = new Menu("Obróć");
        MenuItem colorChange = new MenuItem("Zmień kolor");
        rotate.getItems().addAll(new MenuItem("5"), new MenuItem("10"), new MenuItem("15"), new MenuItem("30"), new MenuItem("45"), new MenuItem("90"), new MenuItem("180"));
        root.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {        
                show(root, event.getScreenX(), event.getScreenY());              
            }
        });
        EventHandler<ActionEvent> rotateEvent = new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                MenuItem eventSource = (MenuItem) event.getSource();
                root.setRotate(root.getRotate() + Integer.parseInt(eventSource.getText()));
            }
        };

        EventHandler<ActionEvent> colorChanger = new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                root.setFill(getColor());
            }
        };
        for(MenuItem item : rotate.getItems()) {
            item.setOnAction(rotateEvent);
        }
        colorChange.setOnAction(colorChanger);
        getItems().addAll(rotate, colorChange);
    }
    /**
     * Metoda uruchamiająca subprogram MyColor Picker i zwracająca kolor wybrany przez użytkownika
     * @return kolor wybrany przez użytkownika
     */
    Color getColor() {
        String color = "";
        String ColorPickerApp = "java --module-path D:\\Apps\\Programowanie\\Java\\javafx-sdk-18.0.1\\lib --add-modules javafx.controls MyColorPicker";
        try {
            Process exec = Runtime.getRuntime().exec(ColorPickerApp);
            BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(exec.getErrorStream()));

            String out = in.readLine();
            while(out != null) {
                color = out;
                out = in.readLine();
            }
            in.close();

            out = err.readLine();
            while(out != null) {
                System.out.println(out);
                out = err.readLine();
            }
            err.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Color.valueOf(color);
    }
}
