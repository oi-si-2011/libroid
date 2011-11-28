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
    private JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private FilterField filterTextField;
    private ListsInventory listsInventory;
    private Model model;
    private JButton addBookButton = new JButton("+ Book");
    private JButton addListButton = new JButton("+ List");
    // </editor-fold>

    public MainFrame(Model model) {
        this.model = model;
        setupAttributes();
        setupMenu();
        setupComponents();
    }

    private void setupAttributes() {
        setTitle("Libroid");
        setSize(1000, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));
        setLayout(new BorderLayout());
    }

    private void setupMenu() {
        // <editor-fold defaultstate="collapsed" desc="File">
        menu = new JMenu("File");
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.setMnemonic(KeyEvent.VK_F);

        menuItem = new JMenuItem("Add a book", new ImageIcon("icons/addBook.gif"));
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds a new book into library");
        menuItem.addActionListener(new AddBookDialog(model));
        menu.add(menuItem);

        menuItem = new JMenuItem("Add a list", new ImageIcon("icons/addList.gif"));
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds a new list of books");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addListDialog();
            }
        });
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Close the app");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menu.add(menuItem);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="List">
        menu = new JMenu("List");
        menuBar.add(menu);
        menu.setMnemonic(KeyEvent.VK_L);

        menuItem = new JMenuItem("Create");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Create a new list of books");
        //menuItem.addActionListener();
        menu.add(menuItem);

        menuItem = new JMenuItem("Import");
        menuItem.setMnemonic(KeyEvent.VK_I);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Import a list of books");
        //menuItem.addActionListener();
        menu.add(menuItem);

        menuItem = new JMenuItem("Edit");
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Edit lists");
        //menuItem.addActionListener();
        menu.add(menuItem);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Tools">
        menu = new JMenu("Tools");
        menuBar.add(menu);
        menu.setMnemonic(KeyEvent.VK_T);

        menuItem = new JMenuItem("Settings");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.getAccessibleContext().setAccessibleDescription("Edit setting of the program");
        //menuItem.addActionListener();
        menu.add(menuItem);
        // </editor-fold>
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
        LibraryTable libraryTable = new LibraryTable(model);

        filterTextField = new FilterField(libraryTable);

        leftPanel.setPreferredSize(new Dimension(200, 500));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        listsInventory = new ListsInventory(model, libraryTable, filterTextField);
        leftPanel.add(new JScrollPane(listsInventory));

        libraryTable.addMouseListener(new BookMenu(libraryTable, model, listsInventory));

        setupToolBar();

        content.add(leftPanel);
        content.add(new JScrollPane(libraryTable));

        add(toolBar, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private void setupToolBar() {
        toolBar.setLayout(new GridLayout(1, 2));
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addBookButton.addActionListener(new AddBookDialog(model));
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
}
