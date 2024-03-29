package libroid.gui;

import javax.swing.AbstractListModel;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Model pro seznamy knih.
 * Pozor, první položka je taková speciální, znamená, že žádné seznam knih
 * není vybrán a mají se zobrazit všechny knihy.
 */
public class ListsInventoryModel extends AbstractListModel {

    Model appModel;

    ListsInventoryModel(Model model) {
        this.appModel = model;
    }

    public int getSize() {
        return appModel.getAllBookLists().size() + 1;
    }

    public Object getElementAt(int i) {
        if (i == 0) {
            return "All books";
        }
        BookList bl = appModel.getAllBookLists().get(i-1);
        return bl.getName();
    }

    BookList getBookList(int i) {
        if (i == 0) {
            return null;
        }
        BookList bl = appModel.getAllBookLists().get(i-1);
        return bl;
    }

}
