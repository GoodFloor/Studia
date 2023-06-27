import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

    private final double points[][] = { 
        { 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 }, 
        { 200, 85 }, { 150, 125 }, { 160, 190 }, { 100, 150 }, 
        { 40, 190 }, { 50, 125 }, { 0, 85 } 
    };
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setPaint(Color.gray);
        g2d.translate(25, 5);

        GeneralPath star = new GeneralPath();

        // Ustawiamy sie na pierwszym punkcie
        star.moveTo(points[0][0], points[0][1]);

        // Rysujemy linie od punktu w ktorym jestesmy do punktu: points[k][0], points[k][1]
        for (int k = 1; k < points.length; k++)
            star.lineTo(points[k][0], points[k][1]);

        star.closePath();
        g2d.fill(star);        
        
        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);
    }
}

public class Star extends JFrame {
    
    public Star() {

        initUI();
    }    
    
    private void initUI() {
        
        add(new Surface());        
        setTitle("Star");
        setSize(350, 250);
        setLocationRelativeTo(null);           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        
        Star ex = new Star();
        ex.setVisible(true);      
    }    
}