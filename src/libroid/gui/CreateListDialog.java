package libroid.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.Model;

class CreateListDialog extends JFrame {
    private Model appDataModel;
    private JLabel label = new JLabel("Choose list name:");
    private JTextField name = new JTextField(15);
    private JButton button = new JButton("Confirm");
    private List<Book> booksList;
    private ListsInventory listInventory;

    public CreateListDialog(Model model, List<Book> books, ListsInventory li) {
        appDataModel = model;
        booksList = books;
        listInventory = li;

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(name.getText().length() > 0){
                    BookList bl = new BookList(name.getText());
                    bl.setBooks(booksList);
                    appDataModel.addBookList(bl);
                    listInventory.updateUI();
                    dispose();
                }
            }
        });

        setSize(308, 98);
        setLayout(new GridLayout(2, 1));
        JLabel top = new JLabel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        top.add(label);
        top.add(name);
        add(top);
        JLabel bottom = new JLabel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(button);
        add(bottom);

        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));
    }

}
