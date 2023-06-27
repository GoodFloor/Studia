import java.awt.*;
import java.awt.event.*;

class WindowAdapterDemo extends WindowAdapter {
  Label p;
  WindowAdapterDemo(Label p) { this.p = p; }
  // Metoda uruchamiana przy zamykaniu okna
  public void windowClosing(WindowEvent e) { System.exit(0); }
  // Metoda uruchamiana przy aktywowaniu okna
  public void windowActivated(WindowEvent e) { p.setText("Aktywne"); }
  // Metoda uruchamiana przy deaktywowaniu okna
  public void windowDeactivated(WindowEvent e) { p.setText("Nie aktywne"); }
 
}

public class przyklad2 {
  public static void main(String[] args) {
    Frame okno = new Frame("Przyklad 2");
    Label napis = new Label("Label",Label.CENTER);

    // Ustawienie kolorow 
    napis.setBackground(Color.RED);
    napis.setForeground(Color.BLUE);
    
       
    // Ustawienie fontow
    napis.setFont(new Font(Font.SERIF,Font.BOLD,100));
    // Ustawienie obslugi okna: nalezy uruchomic metode addWindowListener
    // z parametrem ktorym jest obiekt typu WindowAdapter
    okno.addWindowListener(new WindowAdapterDemo(napis));

    okno.setBounds(100,100,640,240);
    okno.add(napis);
    okno.setVisible(true);
  }
}
