

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SeparatorMenuItem;


public class FXMenuDemo extends Application {

    @Override
    public void start(Stage stage) {

        Label myLabel = new Label("Akcja");
        MenuBar myMenu = new MenuBar();
           
        Menu menu1 = new Menu("Menu 1");
        Menu menu2 = new Menu("Menu 2");
        Menu submenu = new Menu("Podmenu");
        SeparatorMenuItem separator= new SeparatorMenuItem();

        EventHandler<ActionEvent> evnHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MenuItem m = (MenuItem) event.getSource();      
                     
                if (m.getText().equals("Exit")) System.exit(0);
                else{
                    myLabel.setText("Wybrano "+m.getText());
                }
            }
        };



        MenuItem  i1 = new MenuItem("Akcja 1");
        i1.setOnAction(evnHandler);
        MenuItem  i2 = new MenuItem("Akcja 2");
        i2.setOnAction(evnHandler);
        MenuItem i3 = new MenuItem("Akcja 3");
        i3.setOnAction(evnHandler);
        MenuItem i4 = new MenuItem("Akcja 4");
        i4.setOnAction(evnHandler);
        MenuItem i5 = new MenuItem("Akcja 5");
        i5.setOnAction(evnHandler);
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(evnHandler);
       
         
        submenu.getItems().addAll(i1, i2, i3);
   
        menu1.getItems().addAll(submenu, separator, exit);
        menu2.getItems().addAll(i4, i5);
       
        // Add Menus to the MenuBar
        myMenu.getMenus().addAll(menu1, menu2);
       
        BorderPane root = new BorderPane();
        root.setTop(myMenu);
        root.setCenter(myLabel);
        Scene scene = new Scene(root, 350, 200);
       
        stage.setTitle("Menu JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}