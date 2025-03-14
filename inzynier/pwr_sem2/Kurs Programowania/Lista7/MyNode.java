import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Klasa wizualnej reprezentacji pojedynczego węzła
 */
public class MyNode extends Rectangle{
    /**
     * Konstruktor używany do narysowania węzła
     * @param depth rząd w którym węzeł ma zostać narysowany [0; 15]
     * @param alreadyInRow kolumna w której ma zostać narysowany [0; depth]
     */
    MyNode(int depth, int alreadyInRow) {
        super(alreadyInRow * (800 / (Math.pow(2, depth))), depth * 50, Math.ceil((800 / (Math.pow(2, depth)))), 50);
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(0.5);
    }
}
