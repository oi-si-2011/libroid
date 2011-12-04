package libroid.gui;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import libroid.model.Book;

/**
 *
 */
public class BookInfo extends JPanel {

    final JLabel nameLabel = new JLabel();
    final JLabel authorLabel = new JLabel();

    public BookInfo() {
        setPreferredSize(new Dimension(150, 200));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        reset();
        setupComponents();
    }

    private void reset() {
        nameLabel.setText("-");
        authorLabel.setText("-");
    }

    private void setupComponents() {
        add(GUIUtil.withBoldFont(new JLabel("Name:")));
        add(nameLabel);
        add(GUIUtil.withBoldFont(new JLabel("Author:")));
        add(authorLabel);
    }

    void showBook(Book b) {
        if (b == null) {
            reset();
            return;
        }
        nameLabel.setText(b.getName());
        authorLabel.setText(b.getAuthor());
    }
}
