package libroid.gui;

import javax.swing.AbstractListModel;
import libroid.model.BookList;
import libroid.model.Model;

/**
 * Ano, tato trida je opravdu docela hloupe pojmenovana. Nejake navrhy?
 */
public class ListListModel extends AbstractListModel {

    Model appModel;

    ListListModel(Model model) {
        this.appModel = model;
    }

    public int getSize() {
        return appModel.getAlLBookLists().size();
    }

    public Object getElementAt(int i) {
        BookList bl = appModel.getAlLBookLists().get(i);
        return bl.getName();
    }
}
