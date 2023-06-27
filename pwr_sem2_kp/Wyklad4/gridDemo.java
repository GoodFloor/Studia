import java.awt.*;

public class gridDemo {
  public static void main(String[] args) {
    Frame okno = new Frame("gridDemo");
    Button l1 = new Button("Napis1");
    Button l2 = new Button("Napis2");
    Button l3 = new Button("Napis3");
    Button l4 = new Button("Napis4");
    Button l5 = new Button("Napis5");
    
    okno.setBounds(100,100,400,400);
    okno.setLayout(new GridLayout(4,4));

    okno.add(l1);
    okno.add(l2);
    okno.add(l3);
    okno.add(l4);
    okno.add(l5);
    
    okno.setVisible(true);
  }
}
