package libroid.gui;

import java.awt.dnd.DropTarget;
import java.util.List;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Seznamy knih v levé části okna.
 */
public class ListsInventory extends JList implements ListSelectionListener {

    private Model model;
    private LibraryTable table;
    private FilterField filterField;
    private ListsInventoryModel inventoryModel;

    public ListsInventory(Model m, LibraryTable t, FilterField ff) {
        model = m;
        table = t;
        filterField = ff;

        inventoryModel = new ListsInventoryModel(model);
        setModel(inventoryModel);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setDropTarget(new DropTarget());

        addListSelectionListener(this);
    }

    public void valueChanged(ListSelectionEvent e) {
        table.setModel(new LibraryTableModel(model.getBookList(getSelectedIndex())));
        filterField.setTable(table);
    }

    public void createNewList(List<Book> selectedBooks) {
        String name = JOptionPane.showInputDialog(null,
                "What's the new list name?",
                "Create new empty list",
                JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.equals("")) {
            return;
        }
        BookList bl = new BookList(name);
        bl.addBooks(selectedBooks);
        model.addBookList(bl);
        updateUI();
    }
}
