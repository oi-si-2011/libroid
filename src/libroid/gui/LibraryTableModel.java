package libroid.gui;

import javax.swing.table.AbstractTableModel;
import libroid.model.Book;
import libroid.model.BookList;

public class LibraryTableModel extends AbstractTableModel {

    private BookList bookList;
    private String[] columnNames = {"Name", "Author"};

    public LibraryTableModel(BookList bookList) {
        this.bookList = bookList;
    }

    public int getRowCount() {
        return bookList.getBooksCount();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Book b = bookList.getBook(rowIndex);
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
