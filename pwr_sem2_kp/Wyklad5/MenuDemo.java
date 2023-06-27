// Przyklad obslugi menu
import java.awt.*;
import java.awt.event.*;

class MenuDemoWindowAdapter extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

public class MenuDemo extends Frame implements ActionListener {
  private Label myLabel;
  private MenuBar myMenu;
  private Menu menu1, menu2, submenu;
  private MenuItem i1, i2, i3, i4, i5, exit;


  public MenuDemo() {
      // Tworzenie elementu menu, podmenu.
    myLabel = new Label("Start",Label.CENTER);
    myMenu = new MenuBar();
    menu1 = new Menu("Menu 1");
    menu2 = new Menu("Menu 2");
    myMenu.add( menu1 );
    myMenu.add( menu2 );
    submenu = new Menu("Podmenu");
    menu1.add( submenu );
    menu1.addSeparator();

// Tworzenie elementow menu oraz dodawanie do nich akcji
// Skoro klasa implementuje ActionListener, to musi implementowac metode
// actionPerformed
    i1 = new MenuItem("Akcja 1");
    i1.addActionListener(this);
    i2 = new MenuItem("Akcja 2");
    i2.addActionListener(this);
    i3 = new MenuItem("Akcja 3");
    i3.addActionListener(this);
    i4 = new MenuItem("Akcja 4");
    i4.addActionListener(this);
    i5 = new MenuItem("Akcja 5");
    i5.addActionListener(this);
    exit = new MenuItem("Exit");
    exit.addActionListener(this);

    submenu.add( i1 );
    submenu.add( i2 );
    submenu.add( i3 );
    menu1.add( exit );

    menu2.add( i4 );
    menu2.add( i5 );
    
    setLayout(new GridLayout(1,1));
    setMenuBar( myMenu );

    add(myLabel);
    addWindowListener(new MenuDemoWindowAdapter());
        
  }
  // Implementacja metody. Po nacisnieciu na element menu wywoluje jego komende
  public void actionPerformed(ActionEvent e) {
    if( e.getActionCommand().equals("Exit") ) System.exit(0);
    else myLabel.setText("Wybrano "+e.getActionCommand());
  }

  public static void main(String[] args) {
    Frame f = new MenuDemo();
    f.setBounds(100,100,640,480); 
    f.setVisible(true);
  }
}
