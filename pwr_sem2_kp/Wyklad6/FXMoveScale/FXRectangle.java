import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


// Nowa klasa prostokata
public class FXRectangle extends Rectangle {


    public FXRectangle(double x, double y, double width, double height) { 
        super(x, y, width, height);
        setFill(Color.RED);
        setOnMouseClicked(new FXRectangleEventHandler());
        setOnMouseDragged(new FXRectangleEventHandler());
        setOnScroll(new FXRectangleScrollHandler());
    }

    /// Metoda sprawdza czy najechalismy na figure
    public boolean isHit(double x, double y) { 
        return getBoundsInLocal().contains(x,y);   
    }

    // Zmiana wspolrzednej x prostakata
    public void addX(double x) {  
        setX(getX()+x);
    }

    // Zmiana wspolrzednej y prostakata
    public void addY(double y) {  
        setY(getY()+y);
    }
    
    // Zmiana szerokosci prostokata
    public void addWidth(double w) {
        setWidth(getWidth()+w);
    }
    
    // Zmiana wysokosci prostokata
    public void addHeight(double h) {     
        setHeight(getHeight()+h);
    }


    // Implementacja przesuwania
  class FXRectangleEventHandler implements EventHandler<MouseEvent>{
    
    FXRectangle rectangle;
    private double x;
    private double y;
  
    private void doMove(MouseEvent event) {
              
      double dx = event.getX() - x;
      double dy = event.getY() - y;
  
        // Jesli nacisnelismy na elipse
       if (rectangle.isHit(x, y)) {
            rectangle.addX(dx);
            rectangle.addY(dy);
        }
      x += dx;
      y += dy;            
    }
  
  
    @Override
    public void handle(MouseEvent event) {
  
     rectangle = (FXRectangle) event.getSource();
     if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
        x = event.getX();
        y = event.getY();
      }
      if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
        doMove(event);
      }
  
    }
  }

  // Implementacja scrollowania   
class FXRectangleScrollHandler implements EventHandler<ScrollEvent>{

    FXRectangle rectangle;

    private void doScale(ScrollEvent e) {
              
    double x = e.getX();
    double y = e.getY();
  
    // Jesli nacisnelismy na elipse
    if (rectangle.isHit(x, y)) {                 
            rectangle.addWidth(e.getDeltaY()*0.2);
            rectangle.addHeight(e.getDeltaY()*0.2);
        }
    }            
  
    @Override
    public void handle(ScrollEvent event) {
      
        rectangle = (FXRectangle) event.getSource();
      if (event.getEventType()==ScrollEvent.SCROLL){
        doScale(event);
      }
    }
  }
}