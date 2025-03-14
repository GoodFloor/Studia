import java.awt.*;
import java.awt.event.*;



class ObslugaPrzycisku implements ActionListener {
    MojeOkno okno;
    ObslugaPrzycisku(MojeOkno okno) {
        this.okno = okno;
    }
    public void actionPerformed(ActionEvent e) {
        okno.generuj();
    }
}

class hmm extends Label {
    MojeOkno tutaj;

    hmm(MojeOkno okno, String tekst) {
        super(tekst);
        this.tutaj = okno;
        tutaj.add(this);
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
        setBounds(300, 300, 1000, 400);
        setLayout(new BorderLayout());
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        addWindowListener(new ObslugaOkna());

        dane = new TextField(15);
        przycisk = new MojPrzycisk(this);
        wynik = new Label("", Label.CENTER);
        head = new Panel();
        bottom = new Panel();

        head.setLayout(new FlowLayout());
        head.add(dane);
        head.add(przycisk);

        bottom.setLayout(new BorderLayout());
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
            else if(wiersz > 17) {
                wynik.setText("Podana wartość musi być mniejsza niż 18");
            }
            else {
                ObslugaWyniku wynik = new ObslugaWyniku(wiersz);
                /*wynik.setBackground(Color.YELLOW);
                bottom.add(new Label("Proszę"), BorderLayout.CENTER);
                /*this.remove(bottom);
                ObslugaOkna nowe = new ObslugaOkna();
                /*Label test = new Label();
                add(test);
                test.setText("text");
                int wspolczynniki[] = new int[wiersz + 1];
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

// class wierszTrojkata extends Label {
//     wierszTrojkata(int n) {
//         String wynik = "";

//     }
// }



class OknoWyniku extends Frame {
    OknoWyniku(int n) {
        super("Wynik dla n = " + n);
        if(n < 5)
            setBounds(100, 100, (n + 1) * 45, (n + 1) * 55);
        else if(n < 9)
            setBounds(100, 100, n * 55, n * 50);
        else if(n < 13)
            setBounds(100, 100, n * 70, n * 50);
        else if(n < 16)
            setBounds(100, 100, n * 85, n * 50);
        else 
            setBounds(0, 0, n * 100, n * 50);
        setLayout(new GridLayout(n + 1, 1));
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

        int wspolczynniki[] = new int[n + 1];
        wspolczynniki[0] = 1;
        for(int i = 1; i <= n; i++) {
            wspolczynniki[i] = 0;
        }
        String wypisz = "1 ";
        if(n >= 5)
            wypisz += " ";
        if(n >= 9)
            wypisz += " ";
        if(n >= 13)
            wypisz += " ";
        if(n >= 16)
            wypisz += " ";

        add(new Label(wypisz, Label.CENTER));

        for(int a = 1; a <= n; a++) {
            for(int i = a; i > 0; i--) {
                wspolczynniki[i]+= wspolczynniki[i-1];
            }
            wypisz = "";
            for(int i = 0; i <= a; i++) {
                wypisz+= wspolczynniki[i] + " ";
                if(n >= 5 && intLength(wspolczynniki[i]) <= 1)
                    wypisz += " ";
                if(n >= 9 && intLength(wspolczynniki[i]) <= 2)
                    wypisz += " ";
                if(n >= 13 && intLength(wspolczynniki[i]) <= 3)
                    wypisz += " ";
                if(n >= 16 && intLength(wspolczynniki[i]) <= 4)
                    wypisz += " ";
            }
            add(new Label(wypisz, Label.CENTER));
        }
    }
    public int intLength(int n) {
        int wynik = 0;
        if(n == 0)
            return 1;
        while(n > 0) {
            n /= 10;
            wynik++;
        }
        return wynik;
    }
}

class MojPrzycisk extends Button {
    MojPrzycisk(MojeOkno okno) {
        super("Generuj!");
        addActionListener(new ObslugaPrzycisku(okno));
    }
}

class ObslugaOkna extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

class ObslugaWyniku extends WindowAdapter {
    OknoWyniku okno;
    ObslugaWyniku(int n) {
        okno = new OknoWyniku(n);
        okno.addWindowListener(this);
        okno.setVisible(true);
    }
    public void windowClosing(WindowEvent e) {
        //System.exit(0);
        okno.dispose();
    }
}

public class Test {
    public static void main(String[] args) {
        MojeOkno okno = new MojeOkno();
        okno.setVisible(true);
        //ObslugaWyniku okno = new ObslugaWyniku(5);
    }
}