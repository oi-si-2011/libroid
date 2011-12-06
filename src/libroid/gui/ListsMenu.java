package libroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class ListsMenu extends JPopupMenu {

    private ListsInventory listInventory;

    public ListsMenu(ListsInventory aThis) {
        listInventory = aThis;

        JMenuItem menuItem = new JMenuItem("Rename list");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                listInventory.rename();
            }
        });
        add(menuItem);

        menuItem = new JMenuItem("Delete list");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                listInventory.delete();
            }
        });
        add(menuItem);
    }
}
