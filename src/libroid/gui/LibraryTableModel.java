package libroid.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import libroid.model.Book;

public class LibraryTableModel extends AbstractTableModel {

    private String[] columnNames = {"Name","Author"};
    List<Book> list = new ArrayList<Book>();

    public void addBook(Book b){
        list.add(b);
    }

    public void removeBookByIndex(int index){
        list.remove(index);
    }

    public void removeBook(Book b){
        list.remove(b);
    }

    Book getBook(int i) {
        return list.get(i);
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Book b = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return b.getName();
            case 1: return b.getAuthor();
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

}

