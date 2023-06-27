
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

    private Point2D[] points;
    private final int SIZE = 10;
    private int pos;

    public Surface() {
        initUI();
    }

    private void initUI() {

        addMouseListener(new ShapeTestAdapter());
        addMouseMotionListener(new ShapeTestAdapter());
        pos = -1;

        // Tworzenie dwoch przeciwleglych punktow prostokata
        points = new Point2D[2];
        points[0] = new Point2D.Double(50, 50);
        points[1] = new Point2D.Double(150, 100);
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;

        // Rysowanie dwoch malych prostokatow na podstawie punktow stworzonych
        // wyzej
        for (Point2D point : points) {
            double x = point.getX() - SIZE / 2;
            double y = point.getY() - SIZE / 2;
            g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
        }

        // Tworzenie duzego prostokata z dwoch punktow tworzacych przekatna
        Rectangle2D r = new Rectangle2D.Double();
        r.setFrameFromDiagonal(points[0], points[1]);
        g2.draw(r);        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    // Klasa obslugujaca mysz
    private class ShapeTestAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {

            Point p = event.getPoint();

            for (int i = 0; i < points.length; i++) {

                // Pozyskiwanie wspolrzednych lewego gornego wierzcholka
                // malych kwadratow
                double x = points[i].getX() - SIZE / 2;
                double y = points[i].getY() - SIZE / 2;

                Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);

                // Jesli punkt nasisniecia myszy zawiera sie w kwadracie malym
                // to zwracany jest jego numer
                if (r.contains(p)) {
                    pos = i;
                    return;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            pos = -1;
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            if (pos == -1) {
                return;
            }

            // Jesli przesuniemy mysz do nowego polozenia i chwycimy kwadrat maly
            // to pobierany jest nowy punkt i rysunek jest od nowa rysowany
            // (wykonywana jest metoda doDrawing)
            points[pos] = event.getPoint();
            repaint();
        }
    }
}

public class ResizingRectangle extends JFrame {

    public ResizingRectangle()  { 
        initUI();
    }
    
    private void initUI() {
        add(new Surface());
        setTitle("Resize rectangle");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                  
    }
    
    public static void main(String[] args) {
        ResizingRectangle ex = new ResizingRectangle();
        ex.setVisible(true);
    }
}
