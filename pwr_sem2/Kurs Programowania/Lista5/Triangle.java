import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Klasa Prostokąta
*/
public class Triangle extends Polygon{
    boolean isFocused;
    boolean moved;
    Paint fillColor = Color.GRAY;
    Paint borderColor = Color.BLACK;
    Pane parent;
    MyContextMenu contextMenu;
    Point2D pointA;
    Point2D pointB;
    Point2D pointC;
    Point2D center;
    /**
     * Konstruktor
     * @param canvas płótno na którym ma się narysować figura
     * @param pointsList współrzędne wierzchołków trójkąta
     */
    Triangle(Pane canvas, double[] pointsList) {
        super(pointsList);
        parent = canvas;
        isFocused = true;
        moved = false;
        pointA = new Point2D(pointsList[0], pointsList[1]);
        pointB = new Point2D(pointsList[2], pointsList[3]);
        pointC = new Point2D(pointsList[4], pointsList[5]);
        setOnMouseDragged(new TriangleMouseHandler());
        setOnMouseClicked(new TriangleMouseHandler());
        setOnScroll(new TriangleScrollHandler());
        setFill(fillColor);
        setStroke(borderColor);
        setStrokeWidth(3);
        center = new Point2D(pointA.midpoint(pointB).midpoint(pointC).getX(), pointA.midpoint(pointB).midpoint(pointC).getY());
        contextMenu = new MyContextMenu(this);
    }
    /**
     * Konstruktor pomocniczy - obraca i przesuwa stworzony trójkąt
     * @param canvas płótno na którym ma się narysować figura
     * @param pointsList współrzędne wierzchołków trójkąta
     * @param rotation wartość rotacji
     * @param translateX dodatkowe przesunięcie na osi X - dla łatwiejszego późniejszego przenoszenia
     * @param translateY dodatkowe przesunięcie na osi Y - dla łatwiejszego późniejszego przenoszenia
    */
    Triangle(Pane canvas, double[] pointsList, double rotation, double translateX, double translateY) {
        this(canvas, pointsList);
        setRotate(rotation);
        setTranslateX(translateX);
        setTranslateY(translateY);
    }
    /**
     * Przesunięcie figury o wartość parametru na osi X
     * @param dx wartość przesunięcia
     */
    public void addX(double dx) {
        this.setLayoutX(this.getLayoutX() + dx);
        pointA = pointA.add(dx, 0);
        pointB = pointB.add(dx, 0);
        pointC = pointC.add(dx, 0);
    }
    /**
     * Przesunięcie figury o wartość parametru na osi Y
     * @param dx wartość przesunięcia
     */
    public void addY(double dx) {
        this.setLayoutY(this.getLayoutY() + dx);
        pointA = pointA.add(0, dx);
        pointB = pointB.add(0, dx);
        pointC = pointC.add(0, dx);
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
class TriangleMouseHandler implements EventHandler<MouseEvent> {
    Triangle triangle;
    private double mouseX;
    private double mouseY;
    @Override
    /**
     * Metoda obsługi zdarzenia
     */
    public void handle(MouseEvent event) {
        triangle = (Triangle) event.getSource();
        double dx = event.getSceneX() - mouseX;
        double dy = event.getSceneY() - mouseY;
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if(triangle.isFocused) {
                triangle.isFocused = false;
                triangle.setStrokeWidth(0);
                //System.out.println(triangle.pointA.getX() + ", " + triangle.pointA.getY());
            }
            else {
                triangle.isFocused = true;
                triangle.setStrokeWidth(3);
            }
        }
        if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            if(triangle.isFocused) {
                triangle.addX(dx);
                triangle.addY(dy);
                if(!triangle.moved) {
                    triangle.setTranslateX(-triangle.center.getX());
                    triangle.setTranslateY(-triangle.center.getY());
                    triangle.moved = true;
                }
            }
        }
        mouseX += dx;
        mouseY += dy;
    }
}

/**
 * Klasa obsługująca scrollowanie myszą
 */
class TriangleScrollHandler implements EventHandler<ScrollEvent> {
    Triangle triangle;
    /**
     * Główna metoda
     */
    @Override
    public void handle(ScrollEvent event) {
        triangle = (Triangle) event.getSource();
        if(triangle.isFocused && triangle.isHit(event.getX(), event.getY())) {
            double deltaY = event.getDeltaY() * 0.1;
            double a1 = (triangle.pointB.getY() - triangle.pointA.getY()) / (triangle.pointB.getX() - triangle.pointA.getX());
            double b1 = triangle.pointA.getY() - a1 * triangle.pointA.getX();
            double a2 = (triangle.pointC.getY() - triangle.pointA.getY()) / (triangle.pointC.getX() - triangle.pointA.getX());
            double b2 = triangle.pointA.getY() - a2 * triangle.pointA.getX();
            double a3 = (triangle.pointB.getY() - triangle.pointC.getY()) / (triangle.pointB.getX() - triangle.pointC.getX());
            double b3 = (a1 * (triangle.pointB.getX() + deltaY) + b1) - a3 * (triangle.pointB.getX() + deltaY);
            double x3 = (b3 - b2) / (a2 - a3);
            double y3 = a3 * x3 + b3;
            double[] points = {
                triangle.pointA.getX(), triangle.pointA.getY(), 
                triangle.pointB.getX() + deltaY, a1 * (triangle.pointB.getX() + deltaY) + b1, 
                x3, y3};
            triangle.parent.getChildren().add(new Triangle(triangle.parent, points, triangle.getRotate(), triangle.getTranslateX(), triangle.getTranslateY()));
            triangle.setVisible(false);
            triangle = null;
            System.gc();
        }
    }
}
