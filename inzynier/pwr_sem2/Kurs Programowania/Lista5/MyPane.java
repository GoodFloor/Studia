import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Klasa płótna na którym rysowane są obiekty
 */
public class MyPane extends Pane {
    int trianglePoints;
    Rectangle pointA;
    Rectangle pointB;
    /**
     * Główny konstruktor klasy MyPane
     */
    MyPane() {
        super();
        pointA = new Rectangle(0, 0, 10, 10);
        pointA.setFill(Color.WHITE);
        pointA.setStroke(Color.BLACK);
        pointA.setStrokeWidth(2);
        pointA.setVisible(false);
        pointB = new Rectangle(0, 0, 10, 10);
        pointB.setFill(Color.WHITE);
        pointB.setStroke(Color.BLACK);
        pointB.setStrokeWidth(2);
        pointB.setVisible(false);
        getChildren().add(pointA);
        getChildren().add(pointB);
        trianglePoints = 0;
    }
}
