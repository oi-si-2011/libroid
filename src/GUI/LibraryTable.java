package GUI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import spravceknih.Book;

public class LibraryTable extends JTable{
    private TableModel tableModel = new TableModel();

    public LibraryTable(){
        setModel(tableModel);
        //setAutoCreateRowSorter(true);
        tableModel.addBook(new Book("Vlakna hypercesu", "R. Susta"));
        tableModel.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        tableModel.addBook(new Book("Velke U", "N. Stephenson"));
        tableModel.addBook(new Book("Hordubal", "K. Capek"));
        addMouseListener(new bookMenu(this));
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getColumnModel().getColumn(0).setPreferredWidth(50);
    }

    void removeBook() {
        int count = getSelectedRowCount();
        if(count > 0){
            switch (JOptionPane.showConfirmDialog(null, "Do you really want to remove selected book(s) from your library?", "Remove book", JOptionPane.WARNING_MESSAGE)){
                case JOptionPane.OK_OPTION: 
                    if(count > 1){
                        int [] selecetedBooks = getSelectedRows();
                        for (int i = 0; i < selecetedBooks.length; i++) {
                            tableModel.removeBook(selecetedBooks[i]-i);
                        }
                    }else{
                        for (int i = 0; i < getRowCount(); i++) {
                            Object name = tableModel.getValueAt(i, 0);
                            if(name.equals(tableModel.getValueAt(getSelectedRow(), 0))){
                                tableModel.removeBook(i);
                                updateUI();
                                break;
                            }
                        }
                    }
                    updateUI();
                case JOptionPane.CANCEL_OPTION: break;
            }
        }
    }
}

class TableModel extends AbstractTableModel{

    private String[] columnNames = {"#","Name","Author"};
    List<Book> list = new ArrayList<Book>();

    public void addBook(Book b){
        b.setIndex(list.size());
        list.add(b);
    }

    public void removeBook(int index){
        list.remove(index);
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Book b = list.get(rowIndex);
        switch (columnIndex){
            case 0: return b.getIndex();
            case 1: return b.getName();
            case 2: return b.getAuthor();
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
}