package libroid.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.ChangeListener;
import libroid.model.Model;

/**
 * Model pro tabulku knih.
 * Zobrazuje konkrétní list, nebo všechny knihy (pokud bookList je null).
 */
public class LibraryTableModel extends AbstractTableModel {

    private static final Logger logger = Logger.getLogger(LibraryTableModel.class.getName());
    private Model model;
    private BookList bookList;
    private String[] columnNames = {"Name", "Author"};

    public LibraryTableModel(Model model) {
        this.model = model;
        model.addChangeListener(new ModelChangeListener(this));
    }

    /**
     * Změní seznam, který je zobrazen v tabulce.
     */
    public void setBookList(BookList bookList) {
        this.bookList = bookList;
        logger.log(Level.INFO, "Setting book list {0}", bookList);
        fireTableDataChanged();

    }

    public int getRowCount() {
        if (bookList == null) {
            return model.bookCount();
        } else {
            return bookList.getBooksCount();
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Book b = getBook(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getName();
            case 1:
                return b.getAuthor();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    Book getBook(int rowIndex) {
        if (bookList == null) {
            return model.getBook(rowIndex);
        } else {
            return bookList.getBook(rowIndex);
        }

    }

    private static class ModelChangeListener implements ChangeListener {

        private final LibraryTableModel libraryTableModel;

        private ModelChangeListener(LibraryTableModel m) {
            this.libraryTableModel = m;
        }

        public void changePerformed() {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    libraryTableModel.fireTableDataChanged();
                }
            });
        }
    }
}
