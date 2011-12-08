package libroid.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import libroid.model.Book;
import libroid.model.ChangeListener;
import libroid.model.Model;

/**
 * Panel v pravé části okna pro zobrazení detailnějších informací o knize.
 */
public class BookInfo extends JPanel {

    private static final Logger logger = Logger.getLogger(BookInfo.class.getName());
    private final JFrame ownerFrame;
    private final Model model;
    // GUI widgety
    private final JLabel nameLabel = new JLabel();
    private final JLabel authorLabel = new JLabel();
    private final JButton editButton = new JButton("Edit");
    // aktuálně zobrazená kniha
    private Book shownBook;

    public BookInfo(JFrame ownerFrame, Model model) {
        this.ownerFrame = ownerFrame;
        this.model = model;
        setPreferredSize(new Dimension(150, 200));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        reset();
        setupComponents();
        model.addChangeListener(new ModelChangeListener());
    }

    private void setupComponents() {
        add(GUIUtil.withBoldFont(new JLabel("Name:")));
        add(nameLabel);
        add(GUIUtil.withBoldFont(new JLabel("Author:")));
        add(authorLabel);
        add(editButton);

        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                EditBookDialog.show(ownerFrame, model, shownBook);
            }
        });
    }

    /**
     * Uvedení do výchozího stavu - na začátku a po nevybrání žádné knihy.
     */
    private void reset() {
        shownBook = null;
        nameLabel.setText("-");
        authorLabel.setText("-");
    }

    /**
     * Zobrazí informace o konkrétní knize. Voláno ze selection listeneru při
     * změně výběru v libraryTable.
     */
    public void showBook(Book b) {
        shownBook = b;
        reloadBook();
    }

    /**
     * Zobrazení informací o konkrétní knize.
     */
    private void reloadBook() {
        if (shownBook == null) {
            reset();
            return;
        }
        nameLabel.setText(shownBook.getName());
        authorLabel.setText(shownBook.getAuthor());
        logger.log(Level.INFO, "Book reloaded - name: {0} author: {1}",
                new Object[]{shownBook.getName(), shownBook.getAuthor()});
    }

    private class ModelChangeListener implements ChangeListener {

        public ModelChangeListener() {
        }

        /**
         * Znovnunačtení knihy.
         */
        public void changePerformed() {
            reloadBook();
        }
    }
}
