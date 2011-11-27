package libroid.gui;

import java.awt.dnd.DropTarget;
import java.util.List;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import libroid.model.Book;
import libroid.model.Model;

public class ListsInventory extends JList{

    private Model model;
    private LibraryTable table;
    private ListsInventoryModel inventoryModel;

    public ListsInventory(Model m, LibraryTable t){
        model = m;
        table = t;

        inventoryModel = new ListsInventoryModel(model);
        setModel(inventoryModel);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setDropTarget(new DropTarget());

        addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                table.setModel(new LibraryTableModel(model.getBookList(getSelectedIndex())));
            }
        });
    }

    public void createNewList(List<Book> selectedBooks) {
        new CreateListDialog(model, selectedBooks, this).setVisible(true);
    }
}
