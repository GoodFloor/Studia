import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
 
public class FXHierarchy extends Application {
 
   @Override
   public void start(Stage stage) {

        Group top = new Group();
        Group bottom = new Group();

        Rectangle r = new Rectangle(25,25,250,250);
        Circle c = new Circle(50, 50, 30);
        c.setFill(Color.YELLOW);
        r.setFill(Color.BLUE);

        Button btn = new Button("Button");
        ImageView im = new ImageView("flower.jpg");
        im.setFitHeight(200);
        im.setFitWidth(250);

        top.getChildren().add(r);
        top.getChildren().add(c);

        bottom.getChildren().add(im);
        bottom.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        BorderPane root = new BorderPane(null, top, null, bottom, null);

        Scene scene = new Scene(root, 500, 500, Color.BLACK);
 
       stage.setTitle("Root Demo");
       stage.setScene(scene);
       stage.show();
   }
 
   public static void main(String[] args) {
       launch(args);
   }
}