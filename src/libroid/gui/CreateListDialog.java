package libroid.gui;

import java.awt.FlowLayout;
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
    private JTextField name = new JTextField(50);
    private JButton button = new JButton("Confirm");
    private List<Book> booksList;

    public CreateListDialog(Model model, List<Book> books) {
        appDataModel = model;
        booksList = books;

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(name.getText().length() > 0){
                    BookList bl = new BookList(name.getText());
                    bl.setBooks(booksList);
                    appDataModel.addBookList(bl);
                    dispose();
                }
            }
        });

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(label);
        add(name);
        add(button);
        pack();

        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));
    }

}
