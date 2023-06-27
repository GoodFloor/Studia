import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
* Klasa główna
*/
public class Program extends Application{
    MyPane canvas;
    /**
     * Metoda uruchamiająca interfejs graficzny
     */
    @Override
    public void start(Stage mainStage) {
        canvas = new MyPane();
        canvas.setOnMouseClicked(new TrianglePointsPicker());
        MyDialogWindow informationWindow = new MyDialogWindow(MyDialogWindow.Type.INFO);
        MyDialogWindow instructionWindow = new MyDialogWindow(MyDialogWindow.Type.INSTRUCTIONS);
        MenuBar topMenu = new MenuBar();
        Menu insertMenu = new Menu("Wstaw");
        Menu helpMenu = new Menu("Pomoc");
        /**
         * Metoda określająca zachowanie po naciśnięciu elementu menu
         */
        EventHandler<ActionEvent> menuEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem item = (MenuItem) event.getSource();
                switch (item.getText()) {
                    case "Wyjdź":
                        System.exit(0);
                        break;
                    case "Okrąg":
                        canvas.getChildren().add(new Circle(canvas));
                        break;
                    case "Prostokąt":
                        canvas.getChildren().add(new MyRectangle(canvas));
                        break;
                    case "Trójkąt":
                        canvas.trianglePoints = 3;
                        break;
                    case "Informacje":
                        informationWindow.showAndWait();
                        break;
                    case "Instrukcja użytkownika":
                        instructionWindow.showAndWait();
                        break;
                    default:
                        break;
                }
            }
        };
        MenuItem insertCircle = new MenuItem("Okrąg");
        insertCircle.setOnAction(menuEvent);
        MenuItem insertRectangle = new MenuItem("Prostokąt");
        insertRectangle.setOnAction(menuEvent);
        MenuItem insertTriangle = new MenuItem("Trójkąt");
        insertTriangle.setOnAction(menuEvent);
        MenuItem info = new MenuItem("Informacje");
        info.setOnAction(menuEvent);
        MenuItem instructions = new MenuItem("Instrukcja użytkownika");
        instructions.setOnAction(menuEvent);
        MenuItem exit = new MenuItem("Wyjdź");
        exit.setOnAction(menuEvent);
        insertMenu.getItems().addAll(insertCircle, insertRectangle, insertTriangle);
        helpMenu.getItems().addAll(info, instructions, new SeparatorMenuItem(), exit);
        topMenu.getMenus().addAll(insertMenu, helpMenu);

        mainStage.setTitle("Edytor graficzny");
        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setTop(topMenu);
        Scene mainScene = new Scene(root, 800, 800);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /**
     * Główna metoda programu
     * @param args Argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * Klasa wspomagająca tworzenie trójkąta poprzez wybieranie punktów
 */
class TrianglePointsPicker implements EventHandler<MouseEvent> {
    MyPane canvas;
    Point2D pointA;
    Point2D pointB;
    Point2D pointC;
    /**
     * Obsługa kliknięć myszą
     */
    public void handle(MouseEvent event) {
        this.canvas = (MyPane) event.getSource();
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            switch (canvas.trianglePoints) {
                case 0:
                break;
                case 1:
                    pointC = new Point2D(event.getX(), event.getY());
                    canvas.trianglePoints--;
                    canvas.pointA.setVisible(false);
                    canvas.pointB.setVisible(false);
                    canvas.getChildren().add(new Triangle(canvas, new double[] {pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY(), pointC.getX(), pointC.getY()}));
                break;
                case 2:
                    pointB = new Point2D(event.getX(), event.getY());
                    canvas.pointB.setX(event.getX() - 5);
                    canvas.pointB.setY(event.getY() - 5);
                    canvas.pointB.setVisible(true);
                    canvas.trianglePoints--;
                break;
                case 3:
                    pointA = new Point2D(event.getX(), event.getY());
                    canvas.pointA.setX(event.getX() - 5);
                    canvas.pointA.setY(event.getY() - 5);
                    canvas.pointA.setVisible(true);
                    canvas.trianglePoints--;
                break;            
                default:
                    canvas.trianglePoints = 0;
                break;
            }
        }
    }
}