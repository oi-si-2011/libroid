package libroid.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
    private final JLabel fileLabel = new JLabel();
    private final JLabel booklet = new JLabel(new ImageIcon("icons/logo.jpg"));
    private final JButton editButton = new JButton("Edit");
    private final JButton openButton = new JButton("Open");
    // aktuálně zobrazená kniha
    private Book shownBook;

    public BookInfo(JFrame ownerFrame, Model model) {
        this.ownerFrame = ownerFrame;
        this.model = model;
        setPreferredSize(new Dimension(150, 200));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        reloadBook();
        setupComponents();
        model.addChangeListener(new ModelChangeListener());
    }

    private void setupComponents() {
        add(GUIUtil.withBoldFont(new JLabel("Name:")));
        add(nameLabel);
        add(GUIUtil.withBoldFont(new JLabel("Author:")));
        add(authorLabel);
        add(GUIUtil.withBoldFont(new JLabel("File:")));
        add(fileLabel);
        add(booklet);
        add(editButton);
        add(openButton);

        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                EditBookDialog.show(ownerFrame, model, shownBook);
            }
        });

        openButton.addActionListener(new OpenButtonActionListener(this));
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
            // Uvedení do výchozího stavu - na začátku a po nevybrání žádné knihy.
            nameLabel.setText("-");
            authorLabel.setText("-");
            fileLabel.setText("-");
            return;
        }
        nameLabel.setText(shownBook.getName());
        authorLabel.setText(shownBook.getAuthor());
        booklet.setIcon(new ImageIcon(shownBook.getBooklet()));
        if (shownBook.getFile() == null) {
            fileLabel.setText("-");
        } else {
            fileLabel.setText(shownBook.getFile().toString());
        }
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

    private class OpenButtonActionListener implements ActionListener {

        private final BookInfo bookInfo;

        public OpenButtonActionListener(BookInfo bi) {
            this.bookInfo = bi;
        }

        public void actionPerformed(ActionEvent ae) {
            GUIUtil.openBook(shownBook, bookInfo);
        }
    }
}
