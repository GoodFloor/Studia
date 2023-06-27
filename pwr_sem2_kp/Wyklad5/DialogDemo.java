//Przyklad okna dialogowego

import java.awt.*;
import java.awt.event.*;

class dialogDemoWindowAdapter extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}
public class DialogDemo extends Frame implements ActionListener {
  private Dialog myDialog;
  private Button myFrameButton, myDialogButton;

  public DialogDemo() {
    myFrameButton = new Button("Dialog");
    myFrameButton.addActionListener(this);
    myDialogButton = new Button("OK");
    myDialogButton.addActionListener(this);
    // Tworzenie obiektu typu dialog. 
    // Parametr false - dostep do okna tworzacego dialog
    // Parametr true - brak dostepu , trzeba zamknac dialog wczesniej
    myDialog = new Dialog(this, "Dialog Window", true);
    myDialog.setSize(320,240);
    myDialog.add(myDialogButton);
    add(myFrameButton);
    addWindowListener(new dialogDemoWindowAdapter());
  }
  public void actionPerformed(ActionEvent e) {
    if( e.getActionCommand().equals("OK") ) myDialog.setVisible(false);
    else myDialog.setVisible(true);
  }

  public static void main(String[] args) {
    Frame f = new DialogDemo();
    f.setBounds(100,100,640,480);
    f.setVisible(true);
  }
}
