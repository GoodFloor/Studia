import java.awt.*;
import java.awt.event.*;

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
    //Label pole[];

    MojeOkno() {
        super("Trójkąt Pascala");
        addWindowListener(new ObslugaOkna());
        setBounds(100, 100, 1600, 900);
        setLayout(new GridLayout(2, 2));
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

        dane = new TextField(40);
        przycisk = new MojPrzycisk(this);
        wynik = new Label();
        //pole = new Label[22];

        // wynik.setBounds(100, 450, 1600, 450);
        //wynik.setLayout(new GridLayout(23, 46));
        // przycisk.setSize(800, 100);
        // wynik.setSize(800, 100);


        add(dane);
        add(przycisk);
        add(wynik);

        //for(int i = 0; i < 22; i++) {
            //for(int j = 0; j < 45; j++) {
                //wynik.add(pole[i]);
            //}
        //}
    }

    public void generuj() {
        wynik.setText("<html>");
        try {
            int wiersz = Integer.parseInt(dane.getText());
            if(wiersz < 0) {
                wynik.setText(wynik.getText() + "&emsp;Podana wartość musi być nieujemna!");
            }
            else {
                int wspolczynniki[] = new int[wiersz + 1];
                wspolczynniki[0] = 1;
                for(int i = 1; i <= wiersz; i++) {
                    wspolczynniki[i] = 0;
                }
                for(int n = 0; n <= wiersz; n++) {
                    for(int i = 0; i <= n; i++) {
                        wynik.setText(wynik.getText() + wspolczynniki[i] + " ");
                    }
                    wynik.setText(wynik.getText() + "<br/>");
                    for(int i = n + 1; i > 0 && i <= wiersz; i--) {
                        wspolczynniki[i]+= wspolczynniki[i-1];
                    }
                }
            }
        } catch (Exception e) {
            wynik.setText(wynik.getText() + "Należy podać wartość liczbową!");
        }
        wynik.setText(wynik.getText() + "</html>");
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