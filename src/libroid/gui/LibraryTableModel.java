package libroid.gui;

import javax.swing.table.AbstractTableModel;
import libroid.model.Book;
import libroid.model.Model;

public class LibraryTableModel extends AbstractTableModel {

    private Model dataModel;
    private String[] columnNames = {"Name", "Author"};

    public LibraryTableModel(Model dataModel) {
        super();
        this.dataModel = dataModel;
    }

    public int getRowCount() {
        return dataModel.bookCount();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Book b = dataModel.getBook(rowIndex);
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
