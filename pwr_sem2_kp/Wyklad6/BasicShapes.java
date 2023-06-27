
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {
    
    private void doDrawing(Graphics g) {

        // Ustawienie grafiki
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(150, 150, 150));

        // Rysowanie roznych figur w zaleznosci od metody
        g2d.fillRect(30, 20, 50, 50);
        g2d.fillRect(120, 20, 90, 60);
        g2d.fillRoundRect(250, 20, 70, 60, 150, 25);


        g2d.fill(new Ellipse2D.Double(10, 100, 80, 100));
        g2d.fillArc(120, 130, 110, 100, 5, 150);
        g2d.fillOval(270, 130, 50, 50);
   } 

    @Override
    public void paintComponent(Graphics g) {       
        super.paintComponent(g);
        doDrawing(g);
    }    
}

public class BasicShapes extends JFrame {

    public BasicShapes() {
        initUI();
    }
    
    private void initUI() {
        add(new Surface());    
        setTitle("Basic shapes");
        setSize(350, 250);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        BasicShapes ex = new BasicShapes();
        ex.setVisible(true);
    }
}
