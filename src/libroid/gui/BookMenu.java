package libroid.gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import libroid.model.Book;
import libroid.model.Model;

class BookMenu extends JPopupMenu implements MouseListener {
    private LibraryTable libraryTable;
    private BookDescription description = new BookDescription();
    private Model model;
    private ListsInventory listsInventory;

    public BookMenu(LibraryTable lt, Model m, ListsInventory li){
        this.libraryTable = lt;
        this.model = m;
        this.listsInventory = li;

        if(lt.getSelectedRowCount() == 1){
            JMenuItem menuItem = new JMenuItem("Open book");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Book book = libraryTable.getSelectedBooks().get(0);
                    try {
                        File f = new File(book.getUri());
                        System.out.println(book.getUri());
                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(f);
                        }else{
                            throw new IOException("Desktop not supported");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Couldn't open the book. Eighter the source file wasn't found or the filetype isn't supported.", "An error has occured", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            add(menuItem);

            menuItem = new JMenuItem("Add to list");
            menuItem.addActionListener(new SelectListDialog(model, libraryTable.getSelectedBooks()));
            add(menuItem);

            menuItem = new JMenuItem("Remove book");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    libraryTable.removeSelectedBooks();
                }
            });
            add(menuItem);

        }else{
            JMenuItem menuItem = new JMenuItem("Create new list");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    listsInventory.createNewList(libraryTable.getSelectedBooks());
                }
            });
            add(menuItem);

            menuItem = new JMenuItem("Add to list");
            menuItem.addActionListener(new SelectListDialog(model, libraryTable.getSelectedBooks()));
            add(menuItem);

            menuItem = new JMenuItem("Remove books");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    libraryTable.removeSelectedBooks();
                }
            });
            add(menuItem);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
            if(libraryTable.getSelectedRowCount() == 1){
                description.setOriginPoint(e.getXOnScreen(), e.getYOnScreen());
                description.setBook(libraryTable.getSelectedBooks().get(0));
                description.showPanel();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()){
            if(libraryTable.getSelectedRowCount() <= 1){
                int rowIndex = libraryTable.rowAtPoint(e.getPoint());
                libraryTable.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
            }
            BookMenu m = new BookMenu(libraryTable, model, listsInventory);
            m.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}