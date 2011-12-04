package libroid.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
        setLayoutOrientation( JList.HORIZONTAL_WRAP );

        setTransferHandler(new TransferHandler(){

        });

        addListSelectionListener(this);
        addMouseListener(new ListsMenu(this));

        setCellRenderer(new CellRenderer());
    }

    public void valueChanged(ListSelectionEvent e) {
        table.setModel(new LibraryTableModel(model.getBookList(getSelectedIndex())));
        //filterField.setTable(table);
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
        JOptionPane.showConfirmDialog(null, "Do you really want to remove this list?", "Confirm removal", JOptionPane.WARNING_MESSAGE);
        model.removeList(getSelectedIndex());
        updateUI();
    }

    void rename(){
        String name = JOptionPane.showInputDialog(null, "What's the new name?", "Rename list", JOptionPane.PLAIN_MESSAGE);
        model.getBookList(getSelectedIndex()).setName(name);
        updateUI();
    }
}

class CellRenderer extends JLabel implements ListCellRenderer{
    public CellRenderer(){
        setOpaque(true);
        setBackground(Color.white);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String name = (String) list.getModel().getElementAt(index);
        setText(name);
        if(isSelected){
            setBackground(Color.green);
            //add(new JTextField(name));
            return this;
        }else{
            setBackground(Color.white);
            //JLabel label = new JLabel(name);
            //label.setMinimumSize(new Dimension(200, 60));
            //add(label);
            return this;
        }
    }
}
