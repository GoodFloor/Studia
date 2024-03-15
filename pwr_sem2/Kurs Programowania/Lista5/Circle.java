import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Ellipse;
//import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.lang.Math;

/**
 * Klasa okręgu
*/
public class Circle extends Ellipse{
    boolean positionSet;
    boolean radiusSet;
    boolean isFocused;
    Paint fillColor = Color.GRAY;
    Paint borderColor = Color.BLACK;
    Pane parent;
    MyContextMenu contextMenu;
    //CircleResizer resizer;
    /**
     * Konstruktor klasy tworzącej okrąg
     * @param canvas płótno na którym ma się narysować okrąg
     */
    Circle(Pane canvas) {
        super(0, 0, 50, 50);
        parent = canvas;
        positionSet = false;
        radiusSet = false;
        isFocused = false;
        setOnMouseDragged(new CircleCreatorHandler());
        setOnMouseClicked(new CircleCreatorHandler());
        setOnMouseMoved(new CircleCreatorHandler());
        setFill(fillColor);
        setStroke(borderColor);
        setStrokeWidth(3);
        contextMenu = new MyContextMenu(this);
        //resizer = new CircleResizer(this);
    }
    /**
     * Przesunięcie figury o wartość parametru na osi X
     * @param dx wartość przesunięcia
     */
    public void addX(double dx) {
        this.setCenterX(this.getCenterX() + dx);
        //resizer.addX(dx);
    }
    /**
     * Przesunięcie figury o wartość parametru na osi Y
     * @param dx wartość przesunięcia
     */
    public void addY(double dx) {
        this.setCenterY(this.getCenterY() + dx);
        //resizer.addY(dx);
    }
    /**
     * Zmiana promienia o daną wartość
     * @param dx zmiana promienia
     */
    public void addRadius(double dx) {
        this.setRadiusX(this.getRadiusX() + dx);
        this.setRadiusY(this.getRadiusY() + dx);
        //resizer.addX(dx);
    }
    /**
     * Ustawienie promienia na daną wartość
     * @param dx nowa wartość promienia
     */
    public void setRadius(double dx) {
        this.setRadiusX(dx + 10);
        this.setRadiusY(dx + 10);
        //resizer.setX(this.getCenterX() + this.getRadiusX() - 5);
        //resizer.setY(this.getCenterY() - 5);
    }
    /**
     * Sprawdzenie czy punkt znajduje się w środku obiektu
     * @param x współrzędna x punktu
     * @param y współrzędna y punktu
     * @return wartość true lub false
     */
    public boolean isHit(double x, double y) { 
        return getBoundsInLocal().contains(x,y);   
    }
}

/**
 * Klasa obsługi myszy na okręgu
 */
class CircleCreatorHandler implements EventHandler<MouseEvent> {
    Circle circle;
    private double mouseX;
    private double mouseY;
    @Override
    /**
     * Metoda obsługi zdarzenia
     */
    public void handle(MouseEvent event) {
        circle = (Circle) event.getSource();
        double dx = event.getX() - mouseX;
        double dy = event.getY() - mouseY;
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if(!circle.positionSet) {
                circle.positionSet = true;
            }
            else if(!circle.radiusSet) {
                circle.radiusSet = true;
                circle.setStrokeWidth(0);
                circle.setOnScroll(new CircleScrollHandler());
            }
            else if(circle.isFocused) {
                circle.isFocused = false;
                circle.setStrokeWidth(0);
                //circle.resizer.setVisible(false);
            }
            else {
                circle.isFocused = true;
                circle.setStrokeWidth(3);
                //circle.resizer.setVisible(true);
            }
        }
        if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            if(!circle.positionSet) {
                circle.addX(dx);
                circle.addY(dy);
            }
            else if(!circle.radiusSet) {
                //circle.addRadius(Math.sqrt(Math.pow(event.getX() - circle.getCenterX(), 2) + Math.pow(event.getY() - circle.getCenterY(), 2)) - circle.getRadiusX());
            }
            else if(circle.isFocused) {
                circle.addX(dx);
                circle.addY(dy);
            }
        }
        if(circle.positionSet && !circle.radiusSet && event.getEventType() == MouseEvent.MOUSE_MOVED) {
            circle.setRadius(Math.sqrt(Math.pow(event.getX()-circle.getCenterX(), 2) + Math.pow(event.getY()-circle.getCenterY(), 2)));
        }
        mouseX += dx;
        mouseY += dy;
    }
}

/**
 * Klasa obsługująca scrollowanie myszą
 */
class CircleScrollHandler implements EventHandler<ScrollEvent> {
    Circle circle;
    /**
     * Główna metoda
     */
    @Override
    public void handle(ScrollEvent event) {
        circle = (Circle) event.getSource();
        if(circle.isFocused && circle.isHit(event.getX(), event.getY())) {
            circle.addRadius(event.getDeltaY() * 0.1);
        }
    }
}

// class CircleResizer extends Rectangle {
//     Circle root;
//     CircleResizer(Circle root) {
//         super(root.getCenterX() + root.getRadiusX() - 5, root.getCenterY() - 5, 10, 10);
//         setFill(Color.WHITE);
//         setStroke(Color.BLACK);
//         setStrokeWidth(2);
//         setVisible(false);
//         setOnMouseDragged(new CircleResizerHandler());
//         this.root = root;
//         root.parent.getChildren().add(this);
//     }
//     /**
//      * Przesunięcie resizera o wartość parametru na osi X
//      * @param dx wartość przesunięcia
//      */
//     public void addX(double dx) {
//         this.setX(this.getX() + dx);
//     }
//     /**
//      * Przesunięcie resizera o wartość parametru na osi Y
//      * @param dx wartość przesunięcia
//      */
//     public void addY(double dx) {
//         this.setY(this.getY() + dx);
//     }
// }

// class CircleResizerHandler implements EventHandler<MouseEvent> {
//     CircleResizer resizer;
//     private double mouseX;
//     private double mouseY;
//     @Override
//     /**
//      * Metoda obsługi zdarzenia
//      */
//     public void handle(MouseEvent event) {
//         resizer = (CircleResizer) event.getSource();
//         Circle circle = resizer.root;
//         double dx = event.getX() - mouseX;
//         double dy = event.getY() - mouseY;
//         if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
//             if(resizer.isVisible()) {
//                 circle.addRadius(Math.sqrt(Math.pow(event.getX() - circle.getCenterX(), 2) + Math.pow(event.getY() - circle.getCenterY(), 2)) - circle.getRadiusX());
//             }
//         }
//         mouseX += dx;
//         mouseY += dy;
//     }
// }
