import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyWindowAdapter extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

/*--------------------------------------------------------------------*/
class MyButtonAdapter implements ActionListener {
  ProgramProc p;

  MyButtonAdapter(ProgramProc p) { this.p = p; }

  public void actionPerformed(ActionEvent e) { p.action(); }
}

class MyButton extends Button {
  MyButton(ProgramProc p) { 
    super("Execute"); 
    addActionListener(new MyButtonAdapter(p));
  }
}

/*--------------------------------------------------------------------*/
class MyFrame extends Frame {
  MyFrame(ProgramProc p) {
    super("Program");
    setBounds(100,100,640,480);
    addWindowListener(new MyWindowAdapter());
    setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    setLayout(new BorderLayout());
    MyButton action = new MyButton(p);
    p.result = new TextArea(25,80);
    p.data = new TextField(80);
    add(p.data,BorderLayout.NORTH);
    add(action,BorderLayout.SOUTH);
    add(p.result,BorderLayout.CENTER);
    setResizable(false);
  }
}

/*--------------------------------------------------------------------*/
public class ProgramProc {
  MyFrame frame;
  TextArea result;
  TextField data;
  TextArea error;

  void action() {
    try {
        // Tworzenie procesu ktory wywoluje program z pola dane
        // Mozna wywolac program Pr2.exe z parametrami
      Process child = Runtime.getRuntime().exec( data.getText() );
       
      // W BufferedReader in pobieramy wynik procesu child
      BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream())); 
      // W BufferedReader inErr pobieramy blad dzialania procesu
      BufferedReader inErr = new BufferedReader(new InputStreamReader(child.getErrorStream()));
      
      String c;
      result.setText("");
      while ((c = in.readLine()) != null) result.append(c+"\n");
      in.close();    
      
      
      while ((c = inErr.readLine()) != null) result.append("Error: "+c+"\n");
      inErr.close();   
    }    
    catch(IOException e) {
      result.setText(e.getMessage());
    } 
    catch(IllegalArgumentException e) {
      result.setText(e.getMessage());
    } 
    finally{
      data.setText("");
    }  
  }

  public static void main(String[] args) {
    ProgramProc p = new ProgramProc();
    p.frame = new MyFrame(p);
    p.frame.setVisible(true);
  }
}
