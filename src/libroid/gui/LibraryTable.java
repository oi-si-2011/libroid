package libroid.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import libroid.model.Book;
import libroid.model.Model;

/**
 * Tabulka se seznamem knih. Asi nejdůležiější prvek hlavního okna.
 */
public class LibraryTable extends JTable {

    private static final Logger logger = Logger.getLogger(LibraryTable.class.getName());
    private Model appDataModel;
    private LibraryTableModel tableModel;
    private TableRowSorter sorter = new TableRowSorter<LibraryTableModel>();

    public LibraryTable(Model dataModel) {
        this.appDataModel = dataModel;
        tableModel = new LibraryTableModel(appDataModel);
        setModel(tableModel);
        setAutoCreateRowSorter(true);
        setDragEnabled(true);
        sorter = (TableRowSorter) getRowSorter();
    }

    List<Book> getSelectedBooks() {
        List<Book> selectedBooks = new ArrayList<Book>();
        for (int sri : getSelectedRows()) {
            int modelIndex = convertRowIndexToModel(sri);
            selectedBooks.add(appDataModel.getBook(modelIndex));
        }
        return selectedBooks;
    }

    /**
     * Vrátí vybranou knihu, nebo null, pokud není žádná vybrána.
     */
    Book getSelectedBook() {
        List<Book> selectedBooks = getSelectedBooks();
        if (selectedBooks.size() == 1) {
            return selectedBooks.get(0);
        }
        return null;
    }

    void setRowFilter(RowFilter<LibraryTableModel, Object> rf) {
        // XXX TODO unchecked
        sorter.setRowFilter(rf);
    }

    void addListSelectionListener(ListSelectionListener lsl) {
        getSelectionModel().addListSelectionListener(lsl);
    }

    public LibraryTableModel getLibraryTableModel() {
        assert tableModel == super.getModel();
        return tableModel;
    }

}
