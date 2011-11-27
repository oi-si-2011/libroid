package libroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.Model;

public class SelectListDialog extends JFrame implements ActionListener{
    Model model;
    JList list;
    List<Book> books;

    public SelectListDialog(Model m, List<Book> b){
        this.model = m;
        this.books = b;

        setTitle("Select List:");
        //setUndecorated(true);
        list = new JList(new ListsInventoryModel(model));
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                BookList bookList = model.getBookList(list.getSelectedIndex());
                bookList.addBooks(books);
                dispose();
            }
        });
        add(list);
        pack();
        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(true);
    }
}
