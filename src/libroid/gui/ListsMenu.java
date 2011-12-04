package libroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class ListsMenu extends JPopupMenu implements MouseListener{
    private ListsInventory list;

    public ListsMenu(ListsInventory aThis) {
        list = aThis;

        JMenuItem menuItem = new JMenuItem("Rename list");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.rename();
            }
        });
        add(menuItem);

        menuItem = new JMenuItem("Delete list");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.delete();
            }
        });
        add(menuItem);
    }

    public void mouseClicked(MouseEvent e) {
        list.getComponentAt(e.getPoint());
        list.getSelectionModel().setSelectionInterval(WIDTH, WIDTH);
        if(e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()){
            show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}