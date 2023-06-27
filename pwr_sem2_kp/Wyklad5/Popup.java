// Test prawego przycisku myszy
// Po nacisnieciu prawym przyciskiem pojawia sie menu, po nacisnieciu elemenu
// wypisuje sie komunikat, jaki to przycisk
// Jest reakcja na zdarzenia pojawienia sie menu oraz gdy zrobi sie niewidoczne

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class Popup extends JPanel {

    // tworznie popup menu
  public JPopupMenu popup;

  public Popup() {
    popup = new JPopupMenu();
    ActionListener menuListener = new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.out.println("Popup menu item ["
            + event.getActionCommand() + "] was pressed.");
      }
    };
    // Tworzenie elementow menu wraz obsluga zdarzen dla tych elementow 
    JMenuItem item;
    popup.add(item = new JMenuItem("Left"));
    item.setHorizontalTextPosition(JMenuItem.LEFT);
    item.addActionListener(menuListener);
    popup.add(item = new JMenuItem("Center"));
    item.setHorizontalTextPosition(JMenuItem.LEFT);
    item.addActionListener(menuListener);
    popup.add(item = new JMenuItem("Right"));
    item.setHorizontalTextPosition(JMenuItem.LEFT);
    item.addActionListener(menuListener);
    popup.add(item = new JMenuItem("Full"));
    item.setHorizontalTextPosition(JMenuItem.LEFT);
    item.addActionListener(menuListener);
    popup.addSeparator();
    popup.add(item = new JMenuItem("Settings . . ."));
    item.addActionListener(menuListener);

    // Dodanie obslugi zdarzen do calego manu
    popup.addPopupMenuListener(new PopupPrintListener());

    // Dodanie obslugi zdarzen dla myszy
    addMouseListener(new MousePopupListener());
  }

  // Przy takich zdarzeniach myszy wyswietli sie menu 
  class MousePopupListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
       checkPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        checkPopup(e);
    }

    // Menu pojawi sie gdy przycisniemy prawy przycisk
    private void checkPopup(MouseEvent e) {
     if (e.isPopupTrigger()) {
        popup.show(Popup.this, e.getX(), e.getY());
      }
    }
  }

  // Dodanie obslugi zdarzen do calego menu. 
  class PopupPrintListener implements PopupMenuListener {
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
      System.out.println("Popup menu will be visible!");
    }

    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
      System.out.println("Popup menu will be invisible!");
    }

    public void popupMenuCanceled(PopupMenuEvent e) {
      System.out.println("Popup menu is hidden!");
    }
  }

  public static void main(String s[]) {
    JFrame frame = new JFrame("Popup Menu Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new Popup());
    frame.setSize(300, 300);
    frame.setVisible(true);
  }
}