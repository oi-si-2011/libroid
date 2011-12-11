package libroid.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import libroid.model.Book;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Hlavní okno aplikace.
 */
public class MainFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(MainFrame.class.getName());
    // <editor-fold defaultstate="collapsed" desc="Components init">
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu;
    private JMenuItem menuItem = new JMenuItem();
    private JPanel leftPanel = new JPanel();
    private JPanel toolBar = new JPanel(); //not used JToolbar for a reason!
    private JPanel bottomBar = new JPanel();
    private FilterField filterTextField;
    private ListsInventory listsInventory;
    private Model model;
    private BookInfo bookInfo;
    private JButton addBookButton = new JButton("+ Book");
    private JButton addListButton = new JButton("+ List");
    private LibraryTable libraryTable;
    // </editor-fold>

    public MainFrame(Model model) {
        this.model = model;
        setupAttributes();
        //setupMenu();
        setupComponents();
    }

    private void setupAttributes() {
        setTitle("Libroid");
        setSize(1000, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));
        setLayout(new BorderLayout());
    }

    private void addListDialog() {
        String name = JOptionPane.showInputDialog(null,
                "What's the new list name?",
                "Create new empty list",
                JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.equals("")) {
            return;
        }
        model.addBookList(new BookList(name));
        listsInventory.updateUI();
    }

    private void setupComponents() {
        bookInfo = new BookInfo(this, model);

        libraryTable = new LibraryTable(model);
        libraryTable.addListSelectionListener(new LibraryTableSelectionListener(this));

        filterTextField = new FilterField(libraryTable);

        leftPanel.setPreferredSize(new Dimension(200, 500));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        listsInventory = new ListsInventory(model, libraryTable, filterTextField);
        leftPanel.add(new JScrollPane(listsInventory));

        libraryTable.addMouseListener(new LibraryTableMouseListener(libraryTable, model, listsInventory));

        setupToolBar();

        JSplitPane tableAndBookInfo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        tableAndBookInfo.add(new JScrollPane(libraryTable));
        tableAndBookInfo.add(bookInfo);

        JSplitPane leftPanelAndBooks = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        leftPanelAndBooks.add(leftPanel);
        leftPanelAndBooks.add(tableAndBookInfo);

        add(toolBar, BorderLayout.NORTH);
        add(leftPanelAndBooks, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private void setupToolBar() {
        toolBar.setLayout(new GridLayout(1, 2));
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addBookButton.addActionListener(new EditBookDialog.ShowDialogActionListener(this, model));
        addListButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addListDialog();
            }
        });
        left.add(addBookButton);
        left.add(addListButton);
        add(left);
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.add(filterTextField);

        toolBar.add(left);
        toolBar.add(right);
    }

    /**
     * Listener pro změny označení knih v libraryTable.
     * Způsobí zobrazení detailu knihy v bookInfo vpravo.
     */
    private static class LibraryTableSelectionListener implements ListSelectionListener {

        private final LibraryTable libraryTable;
        private final BookInfo bookInfo;

        private LibraryTableSelectionListener(MainFrame mf) {
            assert mf.libraryTable != null;
            assert mf.bookInfo != null;
            this.libraryTable = mf.libraryTable;
            this.bookInfo = mf.bookInfo;
        }

        public void valueChanged(ListSelectionEvent lse) {
            if (lse.getValueIsAdjusting()) {
                return;
            }
            Book b = libraryTable.getSelectedBook();
            bookInfo.showBook(b);
        }
    }
}
