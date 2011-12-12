package libroid.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
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
    private final LibraryTableModel tableModel;
    private TableRowSorter sorter = new TableRowSorter<LibraryTableModel>();
    private LibraryTableRowFilter rowFilter;

    public LibraryTable(Model dataModel) {
        this.appDataModel = dataModel;
        tableModel = new LibraryTableModel(appDataModel);
        setModel(tableModel);
        setAutoCreateRowSorter(true);
        setDragEnabled(true);
        sorter = (TableRowSorter) getRowSorter();
        rowFilter = new LibraryTableRowFilter();
        sorter.setRowFilter(rowFilter);
    }

    public LibraryTableRowFilter getRowFilter() {
        return rowFilter;
    }

    List<Book> getSelectedBooks() {
        List<Book> selectedBooks = new ArrayList<Book>();
        for (int sri : getSelectedRows()) {
            int modelIndex = convertRowIndexToModel(sri);
            selectedBooks.add(tableModel.getBook(modelIndex));
        }
        logger.log(Level.INFO, "Selected books: {0}", selectedBooks);
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

    void addListSelectionListener(ListSelectionListener lsl) {
        getSelectionModel().addListSelectionListener(lsl);
    }

    public LibraryTableModel getLibraryTableModel() {
        assert tableModel == super.getModel();
        return tableModel;
    }

    void setFilterText(String text) {
        rowFilter.setFilterText(text);
        // nevim, jak lepe obnovit tabulku tak, aby se filtr aplikoval, nez
        // takto:
        tableModel.fireTableDataChanged();
    }

}
