import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class FXMovingScaling extends Application {

  public void start(Stage primaryStage) {
    
    FXEllipse ellipse = new FXEllipse(100.0d, 80.0d, 50.0d, 20.0d);
    FXRectangle rect = new FXRectangle(200.0d, 180.0d, 50.0d, 20.0d);
    

    BorderPane pane = new BorderPane();
    Pane gr = new Pane();
    gr.getChildren().add(ellipse);
    gr.getChildren().add(rect);
    pane.setCenter(gr);
    pane.setPadding(new Insets(0, 20, 0, 20));
   

    Scene scene = new Scene(pane, 250, 250);
    primaryStage.setTitle("MovingScaling");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  

  public static void main(String[] args) {
    launch(args);
  }
}









