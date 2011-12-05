package libroid.gui;

import javax.swing.table.AbstractTableModel;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Model pro tabulku knih.
 * Zobrazuje konkrétní list, nebo všechny knihy (pokud bookList je null).
 */
public class LibraryTableModel extends AbstractTableModel {

    private Model model;
    private BookList bookList;
    private String[] columnNames = {"Name", "Author"};

    public LibraryTableModel(Model model) {
        this.model = model;
    }

    /**
     * Změní seznam, který je zobrazen v tabulce.
     */
    public void setBookList(BookList bookList) {
        this.bookList = bookList;
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
        Book b;
        if (bookList == null) {
            b = model.getBook(rowIndex);
        } else {
            b = bookList.getBook(rowIndex);
        }
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
}
