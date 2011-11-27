package libroid.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.Model;

public class LibraryTable extends JTable {

    private static final Logger logger = Logger.getLogger(LibraryTable.class.getName());
    private Model appDataModel;
    private LibraryTableModel tableModel;
    private TableRowSorter sorter = new TableRowSorter<LibraryTableModel>();

    public LibraryTable(Model dataModel) {
        this.appDataModel = dataModel;
        BookList allBooksList = new BookList("Vsechny knihy");
        allBooksList.setBooks(appDataModel.getAllBooks());
        tableModel = new LibraryTableModel(allBooksList);
        appDataModel.addBookList(allBooksList);
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

    void removeSelectedBooks() {
        if (getSelectedRowCount() <= 0) {
            logger.info("No rows selected.");
            return;
        }

        List<Book> selectedBooks = getSelectedBooks();

        switch (JOptionPane.showConfirmDialog(null, "Do you really want to remove selected book(s) from your library?", "Remove book", JOptionPane.WARNING_MESSAGE)) {
            case JOptionPane.OK_OPTION:
                appDataModel.removeBooks(selectedBooks);
                tableModel.fireTableDataChanged();
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }

    void setRowFilter(RowFilter<LibraryTableModel, Object> rf) {
        sorter.setRowFilter(rf);
    }
}
