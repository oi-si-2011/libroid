package libroid.gui;

import javax.swing.AbstractListModel;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Ano, tato trida je opravdu docela hloupe pojmenovana. Nejake navrhy?
 */
public class ListsInventoryModel extends AbstractListModel {

    Model appModel;

    ListsInventoryModel(Model model) {
        this.appModel = model;
    }

    public int getSize() {
        return appModel.getAllBookLists().size();
    }

    public Object getElementAt(int i) {
        BookList bl = appModel.getAllBookLists().get(i);
        return bl.getName();
    }
}
