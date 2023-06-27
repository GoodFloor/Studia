
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


class Surface extends JPanel {

    private ZRectangle zrect;
    private ZEllipse zell;

    public Surface() {
        initUI();
    }
    
    private void initUI() {
        
        // Tworzenie obiektu odpowiedzialnego za obsluge myszy
        MovingAdapter ma = new MovingAdapter();
        
        // Wywolanie metod odpowiedzialnych za dodawanie obslugi myszy
        addMouseMotionListener(ma);
        addMouseListener(ma);
        
        // Wywolanie metody odpowiedzialnyj za dodanie zdarzen ruszania kolkiem
        // myszy
        addMouseWheelListener(new ScaleHandler());

        // Tworzenie obiektow dwoch figur geometrycznych'
        
        zrect = new ZRectangle(50, 50, 50, 50);
        zell = new ZEllipse(150, 70, 80, 80);
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font font = new Font("Serif", Font.BOLD, 40);
        g2d.setFont(font);
        
        // Rysowanie dwoch figur geometrycznych
        g2d.setPaint(new Color(0, 0, 200));
        g2d.fill(zrect);
        g2d.setPaint(new Color(0, 200, 0));
        g2d.fill(zell);        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        doDrawing(g);        
    }

    // Nowa klasa elipsy
    class ZEllipse extends Ellipse2D.Float {
        
        public ZEllipse(float x, float y, float width, float height) {
            setFrame(x, y, width, height);
        }

        /// Metoda sprawdza czy najechalismy na figure
        public boolean isHit(float x, float y) {    
            return getBounds2D().contains(x, y);
        }

        // Zmiana wspolrzednej x elipsy
        public void addX(float x) {  
            this.x += x;
        }

        // Zmiana wspolrzednej y elipsy
        public void addY(float y) {         
            this.y += y;
        }

        // Zmiana szerokosci elipsy
        public void addWidth(float w) {    
            this.width += w;
        }

        // Zmiana wysokosci elipsy
        public void addHeight(float h) {     
            this.height += h;
        }
    }

    // Nowa klasa prostokata
    class ZRectangle extends Rectangle2D.Float {

        public ZRectangle(float x, float y, float width, float height) { 
            setRect(x, y, width, height);
        }

        /// Metoda sprawdza czy najechalismy na figure
        public boolean isHit(float x, float y) { 
            return getBounds2D().contains(x, y);
        }

        // Zmiana wspolrzednej x prostakata
        public void addX(float x) {  
            this.x += x;
        }

        // Zmiana wspolrzednej y prostakata
        public void addY(float y) {  
            this.y += y;
        }
        
        // Zmiana szerokosci prostokata
        public void addWidth(float w) {
            this.width += w;
        }
        
        // Zmiana wysokosci prostokata
        public void addHeight(float h) {     
            this.height += h;
        }
    }

    class MovingAdapter extends MouseAdapter {

        private int x;
        private int y;

        // Po nacisnieciu myszy zapamietane sa wspolrzedne
        @Override
        public void mousePressed(MouseEvent e) {   
            x = e.getX();
            y = e.getY();
        }

        // Przy przesuwaniu myszy uruchamia sie metoda doMove
        @Override
        public void mouseDragged(MouseEvent e) {
            doMove(e);
        }   
        
        // Metoda oblicza o ile przesunelismy sie w stosunku do poprzedniego
        // polozenia i rysuje na nowo figure
        private void doMove(MouseEvent e) {
            
            int dx = e.getX() - x;
            int dy = e.getY() - y;

            // Jesli nacisnelismy na prostokat
            if (zrect.isHit(x, y)) {               
                zrect.addX(dx);
                zrect.addY(dy);
                repaint();
            }

            // Jesli nacisnelismy na elipse
            if (zell.isHit(x, y)) {
                zell.addX(dx);
                zell.addY(dy);
                repaint();
            }

            x += dx;
            y += dy;            
        }
    }

    // Klasa obslugujaca kolko myszy
    class ScaleHandler implements MouseWheelListener {
        
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            doScale(e);
        }
        
        private void doScale(MouseWheelEvent e) {
            
            int x = e.getX();
            int y = e.getY();

            // Jesli krecimy kolkiem myszy to zmiania sie szerokosc i wysokosc
            // figury
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

                if (zrect.isHit(x, y)) {                
                    float amount =  e.getWheelRotation() * 5f;
                    zrect.addWidth(amount);
                    zrect.addHeight(amount);
                    repaint();
                }

                if (zell.isHit(x, y)) {                 
                    float amount =  e.getWheelRotation() * 5f;
                    zell.addWidth(amount);
                    zell.addHeight(amount);
                    repaint();
                }
            }            
        }
    }
}

public class MovingScaling extends JFrame {
    
    public MovingScaling() {      
        initUI();
    }
    
    private void initUI() {    
        add(new Surface());
        setTitle("Moving and scaling");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {        
        MovingScaling ex = new MovingScaling();
        ex.setVisible(true);
    }

}
