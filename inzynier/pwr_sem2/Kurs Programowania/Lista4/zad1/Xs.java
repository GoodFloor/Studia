import java.awt.*;
import java.util.concurrent.Flow;

public class Xs {
    public static void main(String atgs[]) {
        Frame okno = new Frame("okno");
        okno.setLayout(new GridLayout(4, 4));
        okno.setBounds(100, 100, 1600, 900);
        Label wewe[] = new Label[5];
        for(int i = 0; i < 5; i++)
        {
            wewe[i] = new Label();
            wewe[i].setText("i" + i);
            okno.add(wewe[i]);
        }
        okno.setVisible(true);
    }
}
