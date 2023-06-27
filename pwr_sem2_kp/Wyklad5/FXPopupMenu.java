import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.SeparatorMenuItem;


public class FXPopupMenu extends Application {


  @Override
  public void start(Stage stage) {

      Label myLabel = new Label("Nacisnij prawy przycisk myszy na tekscie");

      EventHandler<ActionEvent> evnHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            MenuItem m = (MenuItem) event.getSource();      

            System.out.println("Popup menu item ["
            + m.getText() + "] was pressed.");                 
     
        }
    };

            
      ContextMenu contextMenu = new ContextMenu();
       
      MenuItem itemL = new MenuItem("Left");
      itemL.setOnAction(evnHandler);
      MenuItem itemC = new MenuItem("Center");
      itemC.setOnAction(evnHandler);
      MenuItem itemR = new MenuItem("Right");
      itemR.setOnAction(evnHandler);
      MenuItem itemF = new MenuItem("Full");
      itemF.setOnAction(evnHandler);
      SeparatorMenuItem separator= new SeparatorMenuItem();
      MenuItem itemS = new MenuItem("Settings . . .");
      itemS.setOnAction(evnHandler);
     

      contextMenu.getItems().addAll(itemL,  itemC,  itemR, itemF, separator, itemS);
    
      // Wywolanie po nacisnieciu prawego przycisku na tekscie
      myLabel.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

          @Override
          public void handle(ContextMenuEvent event) {        
              contextMenu.show(myLabel, event.getScreenX(), event.getScreenY());              
          }
      });


      
      // Wywolanie gdy menu jest wyswietlane
      contextMenu.setOnShown(new EventHandler<WindowEvent>() {

          @Override
          public void handle(WindowEvent event) {
            System.out.println("Popup menu will be visible!");
          }
      });
   
       
      // Wywolanie gdy menu jest chowane
      contextMenu.setOnHidden(new EventHandler<WindowEvent>() {

          @Override
          public void handle(WindowEvent event) {
            System.out.println("Popup menu will be invisible!");
          }
      });

        BorderPane root = new BorderPane();
        root.setCenter(myLabel);
        Scene scene = new Scene(root, 350, 200);
       
        stage.setTitle("PopupMenu JavaFX");
        stage.setScene(scene);
        stage.show();
  }

  public static void main(String[] args) {
      Application.launch(args);
  }

}