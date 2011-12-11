package libroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import libroid.model.Book;
import libroid.model.Model;

/**
 * Menu, které se objeví po pravém kliku na knihu v tabulce knih.
 */
class BookMenu extends JPopupMenu {

    private Model model;

    public BookMenu(Model m, final List<Book> selectedBooks) {
        //this.libraryTable = lt;
        this.model = m;
        assert selectedBooks.size() > 0;
        //this.listsInventory = li;

        JMenuItem menuItem;

        if (selectedBooks.size() == 1) {
            menuItem = new JMenuItem("Open book");
            menuItem.addActionListener(new OpenBookActionListener(this, selectedBooks.get(0)));
            add(menuItem);
        }

        menuItem = new JMenuItem(
                selectedBooks.size() == 1
                ? "Add book to list"
                : "Add books to list");
        menuItem.addActionListener(new SelectListDialog(model, selectedBooks));
        add(menuItem);

        menuItem = new JMenuItem(
                selectedBooks.size() == 1
                ? "Create new list with this book"
                : "Create new list with these books");
        menuItem.addActionListener(new CreateNewListActionListener(model, selectedBooks, this));
        add(menuItem);

        menuItem = new JMenuItem(
                selectedBooks.size() == 1
                ? "Remove book"
                : "Remove books");
        menuItem.addActionListener(new RemoveBooksActionListener(model, selectedBooks));
        add(menuItem);
    }

    private static class OpenBookActionListener implements ActionListener {

        private final BookMenu bookMenu;
        private final Book book;

        private OpenBookActionListener(BookMenu bookMenu, Book book) {
            this.bookMenu = bookMenu;
            this.book = book;
        }

        public void actionPerformed(ActionEvent e) {
            GUIUtil.openBook(book, bookMenu);
        }
    }

    private static class CreateNewListActionListener implements ActionListener {

        private final Model model;
        private final List<Book> selectedBooks;
        private final JComponent dialogOwner;

        public CreateNewListActionListener(Model model, List<Book> selectedBooks, JComponent dialogOwner) {
            this.model = model;
            this.selectedBooks = selectedBooks;
            this.dialogOwner = dialogOwner;
        }

        public void actionPerformed(ActionEvent e) {
            ListsInventory.createNewList(model, selectedBooks, dialogOwner);
        }
    }

    private static class RemoveBooksActionListener implements ActionListener {

        private final List<Book> selectedBooks;
        private final Model model;

        public RemoveBooksActionListener(Model model, List<Book> selectedBooks) {
            this.model = model;
            this.selectedBooks = selectedBooks;
        }

        public void actionPerformed(ActionEvent e) {
            int confirmDialogResult = JOptionPane.showConfirmDialog(null,
                    "Do you really want to remove selected book(s) from your library?",
                    "Remove book",
                    JOptionPane.WARNING_MESSAGE);

            if (confirmDialogResult == JOptionPane.OK_OPTION) {
                model.removeBooks(selectedBooks);
            }

        }
    }
}
