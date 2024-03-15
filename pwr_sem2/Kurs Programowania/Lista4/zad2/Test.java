import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

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
        okno.wykonaj();
    }
}

class MojPrzycisk extends Button {
    MojPrzycisk(MojeOkno okno) {
        super("Wykonaj!");
        addActionListener(new ObslugaPrzycisku(okno));
    }
}

class MojeOkno extends Frame{
    TextField dane;
    MojPrzycisk generuj;
    TextArea wynik;
    MojeOkno() {
        super("Wiersz trójkąta Pascala");
        setBounds(100, 100, 800, 400);
        setLayout(new BorderLayout());
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        addWindowListener(new ObslugaOkna());

        dane = new TextField(15);
        generuj = new MojPrzycisk(this);
        wynik = new TextArea(15, 30);
        wynik.setEditable(false);

        add(dane, BorderLayout.NORTH);
        add(generuj, BorderLayout.SOUTH);
        add(wynik, BorderLayout.CENTER);
        setResizable(false);

    }
    public void wykonaj() {
        String komenda = "../../Lista2/zad2/main ";
        komenda += dane.getText();

        try {
            Process exec = Runtime.getRuntime().exec(komenda);
            BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(exec.getErrorStream()));

            dane.setText("");
            wynik.setText("");

            String out = in.readLine();
            while(out != null) {
                wynik.append(out + "\n");
                out = in.readLine();
            }
            in.close();

            out = err.readLine();
            while(out != null) {
                wynik.append(out + "\n");
                out = err.readLine();
            }
            err.close();

        } catch (Exception e) {
            wynik.setText(e.getMessage());
        }
    }
}

public class Test {
    public static void main(String[] args) {
        MojeOkno okno = new MojeOkno();
        okno.setVisible(true);
    }
}
