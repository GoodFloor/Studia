import java.awt.*;

public class borderDemo {
  public static void main(String[] args) {
    Frame okno = new Frame("borderDemo");
    Button l1 = new Button("Napis1");
    Button l2 = new Button("Napis2");
    Button l3 = new Button("Napis3");
    Button l4 = new Button("Napis4");
    Button l5 = new Button("Napis5");
    Button l6 = new Button("Napis6");

    
    okno.setBounds(100,100,400,400);
    okno.setLayout(new BorderLayout());
    okno.add(l1,BorderLayout.NORTH);
    okno.add(l2,BorderLayout.SOUTH);
    okno.add(l3,BorderLayout.WEST);
    okno.add(l4,BorderLayout.EAST);
    okno.add(l5,BorderLayout.CENTER);
    okno.add(l6,BorderLayout.CENTER);


    okno.setVisible(true);
  }
}
