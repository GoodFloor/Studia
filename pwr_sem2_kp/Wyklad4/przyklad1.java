import java.awt.*;

public class przyklad1 {
  public static void main(String[] args) {
    Frame okno = new Frame("Przyklad 1");
    Button przycisk = new Button("Przycisk");
    
    okno.setBounds(100,100,640,480);

    okno.add(przycisk);
    okno.setVisible(true);
  }
}
