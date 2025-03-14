import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.lang.Math;

/**
 * Klasa Prostokąta
*/
public class MyRectangle extends Rectangle{
    boolean positionSet;
    boolean radiusSet;
    boolean isFocused;
    Paint fillColor = Color.GRAY;
    Paint borderColor = Color.BLACK;
    Pane parent;
    MyContextMenu contextMenu;
    Point2D pointA;
    Point2D pointB;
    /**
     * Konstruktor
     * @param canvas płótno na którym ma się narysować figura
     */
    MyRectangle(Pane canvas) {
        super(0, 0, 50, 50);
        parent = canvas;
        positionSet = false;
        radiusSet = false;
        isFocused = false;
        setOnMouseDragged(new RectangleMouseHandler());
        setOnMouseClicked(new RectangleMouseHandler());
        setOnMouseMoved(new RectangleMouseHandler());
        setFill(fillColor);
        setStroke(borderColor);
        setStrokeWidth(3);
        contextMenu = new MyContextMenu(this);
    }
    /**
     * Przesunięcie figury o wartość parametru na osi X
     * @param dx wartość przesunięcia
     */
    public void addX(double dx) {
        this.setX(this.getX() + dx);
    }
    /**
     * Przesunięcie figury o wartość parametru na osi Y
     * @param dx wartość przesunięcia
     */
    public void addY(double dx) {
        this.setY(this.getY() + dx);
    }
    /**
     * Transformacja o daną wartość
     * @param dx wartość transformacji
     */
    public void addRadius(double dx) {
        this.setWidth(this.getWidth() + dx);
        this.setHeight(this.getHeight() + dx);
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
 * Klasa obsługi myszy na prostokącie
 */
class RectangleMouseHandler implements EventHandler<MouseEvent> {
    MyRectangle rectangle;
    private double mouseX;
    private double mouseY;
    @Override
    /**
     * Metoda obsługi zdarzenia
     */
    public void handle(MouseEvent event) {
        rectangle = (MyRectangle) event.getSource();
        double dx = event.getX() - mouseX;
        double dy = event.getY() - mouseY;
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if(!rectangle.positionSet) {
                rectangle.positionSet = true;
                rectangle.pointA = new Point2D(event.getX(), event.getY());
            }
            else if(!rectangle.radiusSet) {
                rectangle.radiusSet = true;
                rectangle.setStrokeWidth(0);
                rectangle.setOnScroll(new RectangleScrollHandler());
            }
            else if(rectangle.isFocused) {
                rectangle.isFocused = false;
                rectangle.setStrokeWidth(0);
            }
            else {
                rectangle.isFocused = true;
                rectangle.setStrokeWidth(3);
            }
        }
        if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            if(!rectangle.positionSet) {
                rectangle.addX(dx);
                rectangle.addY(dy);
            }
            else if(rectangle.isFocused) {
                rectangle.addX(dx);
                rectangle.addY(dy);
            }
        }
        if(rectangle.positionSet && !rectangle.radiusSet && event.getEventType() == MouseEvent.MOUSE_MOVED) {
            rectangle.pointB = new Point2D(event.getX(), event.getY());
            rectangle.setWidth(Math.abs(rectangle.pointA.getX() - rectangle.pointB.getX()) + 10);
            rectangle.setHeight(Math.abs(rectangle.pointA.getY() - rectangle.pointB.getY()) + 10);
        }
        mouseX += dx;
        mouseY += dy;
    }
}

/**
 * Klasa obsługująca scrollowanie myszą
 */
class RectangleScrollHandler implements EventHandler<ScrollEvent> {
    MyRectangle rectangle;
    /**
     * Główna metoda
     */
    @Override
    public void handle(ScrollEvent event) {
        rectangle = (MyRectangle) event.getSource();
        if(rectangle.isFocused && rectangle.isHit(event.getX(), event.getY())) {
            rectangle.addRadius(event.getDeltaY() * 0.1);
        }
    }
}
