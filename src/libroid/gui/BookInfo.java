package libroid.gui;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import libroid.model.Book;

/**
 * Panel v pravé části okna pro zobrazení detailnějších informací o knize.
 */
public class BookInfo extends JPanel {

    final JLabel nameLabel = new JLabel();
    final JLabel authorLabel = new JLabel();

    public BookInfo() {
        setPreferredSize(new Dimension(100, 200));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        reset();
        setupComponents();
    }

    private void setupComponents() {
        add(GUIUtil.withBoldFont(new JLabel("Name:")));
        add(nameLabel);
        add(GUIUtil.withBoldFont(new JLabel("Author:")));
        add(authorLabel);
    }

    /**
     * Uvedení do výchozího stavu - na začátku a po nevybrání žádné knihy.
     */
    private void reset() {
        nameLabel.setText("-");
        authorLabel.setText("-");
    }

    /**
     * Zobrazí informace o konkrétní knize. Voláno ze selection listeneru při
     * změně výběru v libraryTable.
     */
    void showBook(Book b) {
        if (b == null) {
            reset();
            return;
        }
        nameLabel.setText(b.getName());
        authorLabel.setText(b.getAuthor());
    }
}
