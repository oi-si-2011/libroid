package libroid.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import libroid.model.Book;

public class LibraryTable extends JTable{
    private static final Logger logger = Logger.getLogger(LibraryTable.class.getName());
    private static TableModel tableModel = new TableModel();

    public LibraryTable(){
        setModel(tableModel);
        setAutoCreateRowSorter(true);
        tableModel.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        tableModel.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        tableModel.addBook(new Book("Velke U", "Neal Stephenson"));
        tableModel.addBook(new Book("Hordubal", "K. Capek"));
        tableModel.addBook(new Book("A", "1"));
        tableModel.addBook(new Book("B", "2"));
        addMouseListener(new BookMenu(this));
    }

    void removeBook() {
        int count = getSelectedRowCount();
        if(count > 0){
            switch (JOptionPane.showConfirmDialog(null, "Do you really want to remove selected book(s) from your library?", "Remove book", JOptionPane.WARNING_MESSAGE)){
                case JOptionPane.OK_OPTION: 
                    if(count > 1){
                        int [] selecetedBookIndexes = getSelectedRows();
                        Book [] selectedBooks = new Book [getSelectedRowCount()];
                        for (int i = 0; i < selecetedBookIndexes.length; i++) {
                            selecetedBookIndexes[i] = convertRowIndexToModel(selecetedBookIndexes[i]);
                            selectedBooks[i] = tableModel.getBook(selecetedBookIndexes[i]);
                        }
                        for (int i = 0; i < selectedBooks.length; i++) {
                            tableModel.removeBook(selectedBooks[i]);
                        }
                        tableModel.fireTableDataChanged();
                    }else{
                        int index = convertRowIndexToModel(getSelectedRow());
                        tableModel.removeBookByIndex(index);
                        tableModel.fireTableDataChanged();
                    }
                    updateUI();
                case JOptionPane.CANCEL_OPTION: break;
            }
        }
    }
}



class TableModel extends AbstractTableModel{

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