import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FXDialogDemo extends Application {
   @Override
   public void start(Stage stage) {
      //Obsluga okna dialogowego
      Dialog<String> dialog = new Dialog<String>();
      dialog.setTitle("Dialog");
      ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
      dialog.setContentText("To jest przykladowe okno dialogowe");
      dialog.getDialogPane().getButtonTypes().add(type);
      
      Text txt = new Text("Nacisnij przycisk by pokazac okno dialogowe");
      Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
      txt.setFont(font);
     
     
      // Obsluga wyswietlenia okna dialogowego dialogu  
      Button button = new Button("Pokaz dialog"); 
      EventHandler<ActionEvent> evnHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            dialog.showAndWait();
        }
    };
      button.setOnAction(evnHandler);

      HBox pane = new HBox(15);

      pane.setPadding(new Insets(50, 150, 50, 60));
      pane.getChildren().addAll(txt, button);
 
      Scene scene = new Scene(new Group(pane), 595, 250, Color.BEIGE);
      stage.setTitle("Dialog");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}