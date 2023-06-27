import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Ellipse;



// Nowa klasa elipsy
public class FXEllipse extends Ellipse{
        
    public FXEllipse(double x, double y, double width, double height) {
        super(x, y, width, height);
        setOnMouseClicked(new FXEllipseEventHandler());
        setOnMouseDragged(new FXEllipseEventHandler());
        setOnScroll(new FXEllipseScrollHandler());
  
    }
  
  
    /// Metoda sprawdza czy najechalismy na figure
    public boolean isHit(double x, double y) { 
       return getBoundsInLocal().contains(x,y);   
  
    }
  
    // Zmiana wspolrzednej x elipsy
    public void addX(double x) {  
        this.setCenterX(this.getCenterX() +x);
    }
  
    // Zmiana wspolrzednej y elipsy
    public void addY(double y) {         
      this.setCenterY(this.getCenterY() +y);
    }
  
    // Zmiana szerokosci elipsy
    public void addWidth(double w) {    
      this.setRadiusX(this.getRadiusX()+w);
    }
  
    // Zmiana wysokosci elipsy
    public void addHeight(double h) {     
      this.setRadiusY(this.getRadiusY()+h);
    }


 // Implementacja scrollowania   
class FXEllipseScrollHandler implements EventHandler<ScrollEvent>{

    FXEllipse ellipse;

    private void doScale(ScrollEvent e) {
              
    double x = e.getX();
    double y = e.getY();
  
    // Jesli nacisnelismy na elipse
    if (ellipse.isHit(x, y)) {                 
            ellipse.addWidth(e.getDeltaY()*0.2);
            ellipse.addHeight(e.getDeltaY()*0.2);
        }
    }            
  
    @Override
    public void handle(ScrollEvent event) {
      
      ellipse = (FXEllipse) event.getSource();
      if (event.getEventType()==ScrollEvent.SCROLL){
        doScale(event);
      }
    }
  }
  
  
  // Implementacja przesuwania
  class FXEllipseEventHandler implements EventHandler<MouseEvent>{
    
    FXEllipse ellipse;
    private double x;
    private double y;
  
    private void doMove(MouseEvent event) {
              
      double dx = event.getX() - x;
      double dy = event.getY() - y;
  
        // Jesli nacisnelismy na elipse
      if (ellipse.isHit(x, y)) {
          ellipse.addX(dx);
          ellipse.addY(dy);
        }
      x += dx;
      y += dy;            
    }
  
  
    @Override
    public void handle(MouseEvent event) {
  
      ellipse = (FXEllipse) event.getSource();
     if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
        x = event.getX();
        y = event.getY();
      }
      if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
        doMove(event);
      }
  
    }
  }
  

  }