import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
 
public class FXGridDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        Button l1 = new Button("Napis1");
        Button l2 = new Button("Napis2");
        Button l3 = new Button("Napis3");
        Button l4 = new Button("Napis4");
        Button l5 = new Button("Napis5");


        grid.add(l1, 1, 0); 
        grid.add(l2, 2, 0);
        grid.add(l3, 0, 1);
        grid.add(l4, 0, 2);
        grid.add(l5, 0, 3);


        primaryStage.setScene(new Scene(grid, 300, 250));
        primaryStage.show();
    }
}