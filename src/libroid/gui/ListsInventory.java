package libroid.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Seznamy knih v levé části okna.
 */
public class ListsInventory extends JList {

    private Model model;
    private LibraryTable libraryTable;
    private FilterField filterField;
    private ListsInventoryModel inventoryModel;

    public ListsInventory(Model m, LibraryTable t, FilterField ff) {
        model = m;
        libraryTable = t;
        filterField = ff;

        inventoryModel = new ListsInventoryModel(model);
        setModel(inventoryModel);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setDropTarget(new DropTarget());
        setLayoutOrientation(JList.VERTICAL);

        setTransferHandler(new TransferHandler() {
        });

        addListSelectionListener(new ListsInventorySelectionListener(this, libraryTable));
        addMouseListener(new ListsInventoryMouseListener(this));

        //setCellRenderer(new CellRenderer());
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

    void delete() {
        JOptionPane.showConfirmDialog(null,
                "Do you really want to remove this list?",
                "Confirm removal",
                JOptionPane.WARNING_MESSAGE);
        model.removeList(getSelectedIndex());
        updateUI();
    }

    void rename() {
        String name = JOptionPane.showInputDialog(null,
                "What's the new name?",
                "Rename list",
                JOptionPane.PLAIN_MESSAGE);
        model.getBookList(getSelectedIndex()).setName(name);
        updateUI();
    }

    private static class ListsInventoryMouseListener extends MouseAdapter {

        private final ListsInventory listInventory;

        private ListsInventoryMouseListener(ListsInventory li) {
            this.listInventory = li;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            listInventory.getComponentAt(e.getPoint());
            //listInventory.getSelectionModel().setSelectionInterval(WIDTH, WIDTH);

            ListsMenu m = new ListsMenu(listInventory);

            if (e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()) {
                m.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /**
     * ListSelectionListener navěšený na ListsInventory.
     * Způsobí, že po vybrání seznamu se v tabulce knih zobrazí pouze knihy
     * z toho vybraného seznamu.
     */
    private static class ListsInventorySelectionListener implements ListSelectionListener {

        private final LibraryTableModel libraryTableModel;
        private final ListsInventory listsInventory;

        private ListsInventorySelectionListener(ListsInventory li, LibraryTable libraryTable) {
            this.listsInventory = li;
            this.libraryTableModel = libraryTable.getLibraryTableModel();
        }

        public void valueChanged(ListSelectionEvent lse) {
            // V libraryTable zobrazíme zvolený seznam knih.
            int selectedIndex = listsInventory.getSelectedIndex();
            BookList bookList = listsInventory.inventoryModel.getBookList(selectedIndex);
            libraryTableModel.setBookList(bookList);
        }
    }
}

class CellRenderer extends JLabel implements ListCellRenderer {

    public CellRenderer() {
        setOpaque(true);
        setBackground(Color.white);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String name = (String) list.getModel().getElementAt(index);
        setText(name);
        if (isSelected) {
            setBackground(Color.green);
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    setBackground(Color.red);
                }
            });
            return this;
        } else {
            setBackground(Color.white);
            //JLabel label = new JLabel(name);
            //label.setMinimumSize(new Dimension(200, 60));
            //add(label);
            return this;
        }
    }
}
