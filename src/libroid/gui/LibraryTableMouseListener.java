package libroid.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libroid.model.Book;
import libroid.model.Model;

/**
 * Zpracovává kliky na knihy v tabulce knih.
 */
class LibraryTableMouseListener extends MouseAdapter {

    private static final Logger logger = Logger.getLogger(LibraryTableMouseListener.class.getName());
    private final LibraryTable libraryTable;
    private final ListsInventory listsInventory;
    private final Model model;

    LibraryTableMouseListener(LibraryTable lt, Model m, ListsInventory li) {
        this.libraryTable = lt;
        this.model = m;
        this.listsInventory = li;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            // dvojklik
            if (libraryTable.getSelectedRowCount() == 1) {
                // tady bylo zobrazeni BookDescription, kdyz jeste nebylo BookInfo
            }
        }
    }

    /**
     * Zobrazí BookMenu.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()) {
            if (libraryTable.getSelectedRowCount() <= 1) {
                // nastavíme označení na knihu, na kterou se kliklo
                int rowIndex = libraryTable.rowAtPoint(e.getPoint());
                libraryTable.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
            }
            List<Book> selectedBooks = libraryTable.getSelectedBooks();
            if (selectedBooks.isEmpty()) {
                logger.warning("No book selected");
                return;
            }
            logger.log(Level.INFO, "Showing menu for selected books {0}", selectedBooks);
            BookMenu m = new BookMenu(model, selectedBooks);
            m.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
