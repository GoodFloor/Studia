import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;

class ObslugaOkna extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

class ObslugaPrzycisku implements ActionListener {
    MojeOkno okno;
    ObslugaPrzycisku(MojeOkno okno) {
        this.okno = okno;
    }
    public void actionPerformed(ActionEvent e) {
        okno.generuj();
    }
}

class MojeOkno extends Frame {
    TextField dane;
    MojPrzycisk przycisk;
    Label wynik;
    Panel bottom;
    Panel head;
    //Label[] wynik;

    MojeOkno() {
        super("Trójkąt Pascala");
        addWindowListener(new ObslugaOkna());
        setBounds(100, 100, 1600, 900);
        setLayout(new BorderLayout());
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

        dane = new TextField(40);
        przycisk = new MojPrzycisk(this);
        wynik = new Label("", Label.CENTER);
        head = new Panel();
        bottom = new Panel();

        head.setLayout(new FlowLayout());
        head.setBackground(Color.BLUE);
        head.add(dane);
        head.add(przycisk);

        bottom.setLayout(new BorderLayout());
        bottom.setBackground(Color.RED);
        wynik.setBackground(Color.GREEN);
        bottom.add(wynik, BorderLayout.CENTER);

        //pole = new Label[22];

        // wynik.setBounds(100, 450, 1600, 450);
        //wynik.setLayout(new GridLayout(23, 46));
        // przycisk.setSize(800, 100);
        // wynik.setSize(800, 100);

        add(head, BorderLayout.NORTH);
        add(bottom, BorderLayout.CENTER);

        //for(int i = 0; i < 22; i++) {
            //for(int j = 0; j < 45; j++) {
                //wynik.add(pole[i]);
            //}
        //}
    }

    public void generuj() {
        try {
            int wiersz = Integer.parseInt(dane.getText());
            if(wiersz < 0) {
                wynik.setText("Podana wartość musi być nieujemna!");
            }
            else {
                Label test = new Label();
                add(test);
                test.setText("text");
                /*int wspolczynniki[] = new int[wiersz + 1];
                wspolczynniki[0] = 1;
                for(int i = 1; i <= wiersz; i++) {
                    wspolczynniki[i] = 0;
                }
                for(int n = 0; n <= wiersz; n++) {
                    for(int i = 0; i <= n; i++) {
                        wynik[i].setText(wynik[i].getText() + wspolczynniki[i] + " ");
                    }
                    wynik[1].setText(wynik[1].getText() + "<br/>");
                    for(int i = n + 1; i > 0 && i <= wiersz; i--) {
                        wspolczynniki[i]+= wspolczynniki[i-1];
                    }
                }*/
            }
        } catch (Exception e) {
            wynik.setText("Należy podać wartość liczbową!");
        }
        dane.setText("");
    }

}

class MojPrzycisk extends Button {
    MojPrzycisk(MojeOkno okno) {
        super("Generuj!");
        addActionListener(new ObslugaPrzycisku(okno));
    }
}

public class Test {
    public static void main(String[] args) {
        MojeOkno okno = new MojeOkno();
        okno.setVisible(true);
    }
}