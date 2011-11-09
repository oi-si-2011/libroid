package libroid.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import libroid.model.Model;

public class MainFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(MainFrame.class.getName());
    // <editor-fold defaultstate="collapsed" desc="Components init">
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu;
    private JMenuItem menuItem = new JMenuItem();
    private JPanel leftPanel = new JPanel();
    private JPanel toolBar = new JPanel(); //not used JToolbar for a reason!
    private JPanel bottomBar = new JPanel();
    private JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT );
    // </editor-fold>

    public MainFrame(Model model) {
        setupAttributes();
        setupMenu();
        setupComponents(model);
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
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Add a list", new ImageIcon("icons/addList.gif"));
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds a new list of books");
        //menuItem.addActionListener();
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

    private void setupComponents(Model model) {
        LibraryTable libraryTable = new LibraryTable(model);
        JScrollPane bookTableContainer = new JScrollPane(libraryTable);


        leftPanel.setPreferredSize(new Dimension(200, 500));
        leftPanel.setBackground(Color.white);

        add(toolBar, BorderLayout.NORTH);

        content.add(leftPanel);
        content.add(bookTableContainer);
        add(content, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }

}
