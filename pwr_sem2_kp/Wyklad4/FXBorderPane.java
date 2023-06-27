import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
 
public class FXBorderPane extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     
    @Override
    public void start(Stage stage) 
    {   
        Button btn = new Button("Wykonaj"); 
        TextArea txtArea = new TextArea();
        Label lbl = new Label();
       
        BorderPane root = new BorderPane(lbl, txtArea, null, btn, null);

        btn.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
                lbl.setText(txtArea.getText());
                txtArea.setText("");
            }
            });

        root.setPrefSize(400, 400);     
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BorderPane");
        stage.show();       
    }
}