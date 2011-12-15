package libroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Menu, které se objeví po pravém kliku na seznam v tabulce seznamů.
 */
class ListsMenu extends JPopupMenu {

    private final Model model;
    private final BookList bookList;

    ListsMenu(Model model, BookList bookList) {
        this.model = model;
        this.bookList = bookList;
        setup();
    }

    private void setup() {
        JMenuItem menuItem = new JMenuItem("Rename list");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                renameList();
            }
        });
        add(menuItem);

        menuItem = new JMenuItem("Delete list");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                removeList();
            }
        });
        add(menuItem);
    }

    private void renameList() {
        String name = JOptionPane.showInputDialog(null,
                "What's the new name?",
                "Rename list",
                JOptionPane.PLAIN_MESSAGE);
        bookList.setName(name);
    }

    private void removeList() {
        JOptionPane.showConfirmDialog(null,
                "Do you really want to remove this list?",
                "Confirm removal",
                JOptionPane.WARNING_MESSAGE);
        model.removeList(bookList);
    }
}
