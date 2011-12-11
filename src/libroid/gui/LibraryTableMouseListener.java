package libroid.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import libroid.model.Model;

/**
 * Zpracovává kliky na knihy v tabulce knih.
 */
class LibraryTableMouseListener extends MouseAdapter {

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

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()) {
            if (libraryTable.getSelectedRowCount() <= 1) {
                int rowIndex = libraryTable.rowAtPoint(e.getPoint());
                libraryTable.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
            }
            BookMenu m = new BookMenu(libraryTable, model, listsInventory);
            m.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
