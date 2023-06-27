import java.awt.*;
import java.awt.event.*;

// Klasa WindowAdapter odpowiedzialna za zamykanie okna
class MyWindowAdapter extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

/*--------------------------------------------------------------------*/
// Nowa klasa obslugujaca przycisk do przepisywania tekstu
class MyButton extends Button {
  MyButton(MyFrame f) { 
      // Wywolanie konstruktora klasy bazowej Button
    super("Przepisz"); 
    // Zeby mogl dzialac trzeba dodac obsluge zadan
    // Metoda addActionListener pobiera obiekt typu ActionListener  
    // jako parametr
    addActionListener(new MyButtonAdapter(f));
  }
}

/*--------------------------------------------------------------------*/
// Klasa pochodna od ActionListener, implementuje metode actionPerformed
// ktora jest odpowiedzialna za akcje po nacisnieciu na przycisk
class MyButtonAdapter implements ActionListener {
  MyFrame f;
  MyButtonAdapter(MyFrame f) { this.f = f; }  
  public void actionPerformed(ActionEvent e) { f.action(); }
}

/*--------------------------------------------------------------------*/
// Tak samo jak w przypadku MyButton. Definiujemy Checkboxa i dodajemy obiekt 
// klasy MyCheckBoxListener pochodzacy od ItemListener jako parametr
class MyCheckBox extends Checkbox
{
    Label l;
    public MyCheckBox(Label l) {
        this.l=l;
        addItemListener(new MyCheckBoxListener(l));
    }    
}

/*--------------------------------------------------------------------*/
// Implementacja interfejsu ItemListener. Zeby checkbox reagowal na zaznaczenie
// i odznaczenie trzeba zaimplementowac metode itemStateChanged. 
class MyCheckBoxListener implements ItemListener{
    Label l;
    public MyCheckBoxListener(Label l)
    {
        this.l=l;
    }
    // Jesli zaznaczony bedzie checkbox, to na Label l bedzie wypisany text.
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange()==1)
        {
            l.setText("Checked");
        }
        else
        {
            l.setText("Not Checked");
        }
    }
}

/*--------------------------------------------------------------------*/
//Obsluga myszy. Zasada podobna jak przy checkboxie.
class MyMouseAdapter extends MouseAdapter
{
    Label l;
    public MyMouseAdapter(Label l)
    {
        this.l=l;
    }
    
    // Implementacja metody mouseDragged: po nacisnieciu lewego przycisku i 
    // przesunieciu myszy, pojawi sie napis
    public void mouseDragged(MouseEvent e) {
            l.setText("Mouse Dragged");
         }                
 
    
    // Implementacja metody mouseWheelMoved: przesuwanie ko³ka myszy
    public void mouseWheelMoved(MouseWheelEvent e) {
    	 l.setText("Mouse Wheeled");
    }
}
/*--------------------------------------------------------------------*/
class MyFrame extends Frame {
    // Deklaracja elementow 
    
    Label wynik;
    TextField dane;
    Checkbox  c1;
    
  MyFrame() {
    super("Program");
    setBounds(100,100,640,480);
    // Obsluga klasy odziedziczonej po Frame
    addWindowListener(new MyWindowAdapter());
    setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
    setLayout(new GridLayout(4,1));

    // Tworzenie obiektow elementow graficznych
    MyButton akcja = new MyButton(this); 
    wynik = new Label();
    dane = new TextField(40);

    
    
    // Przyklad alternatywnego tworzenia CheckBox. Tak samo jak wyzej tylko
    // bez tworzenia nowej klasy
    c1 = new Checkbox();
    c1.addItemListener(new ItemListener(){public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange()==1)
        {
            wynik.setText("Checked");
        }
        else
        {
            wynik.setText("Not Checked");
        }
    }});

    // Podpinanie obslugi myszy pod Label.
    wynik.addMouseMotionListener(new MyMouseAdapter(wynik) );
    wynik.addMouseWheelListener(new MyMouseAdapter(wynik) );
    
    // Dodawanie obslugi klawiszy. Po nacisnieciu a lub b przepisze sie 
    // na dol. Jest realizowane za pomoca klasy anonimowej
    dane.addKeyListener(new KeyAdapter(){
    public void keyPressed(KeyEvent e)
    {
        char ch = e.getKeyChar();
        if ((ch=='a')|| (ch=='b'))
        {
            String str = wynik.getText();
            wynik.setText(str+ch);
        }
    }
    });
    
   
    // Dodawanie elementow graficznych do Frame 
    add(dane);
    add(akcja);
    add(wynik);
    add(c1);
    setResizable(false);
  }
   
  // Metoda pobiera tekst z gory i wpisuje na dole
  public void action() {
    wynik.setText(dane.getText());
    dane.setText("");
  }
}

/*--------------------------------------------------------------------*/
public class program1 {
  public static void main(String[] args) {
    MyFrame frame = new MyFrame();
    frame.setVisible(true);
  }
}
