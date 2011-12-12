package libroid.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.table.TableModel;

public class LibraryTableRowFilter extends RowFilter<TableModel, Integer> {

    private static final Logger logger = Logger.getLogger(LibraryTableRowFilter.class.getName());
    private String filterText;

    public void setFilterText(String filterText) {
        logger.log(Level.INFO, "Setting filterText to {0}", filterText);
        this.filterText = filterText;
    }

    @Override
    public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
        logger.log(Level.INFO, "entry: {0} filterText: {1}", new Object[]{entry, filterText});

        String name = entry.getStringValue(0).toLowerCase();
        String author = entry.getStringValue(1).toLowerCase();

        if (filterText == null || filterText.isEmpty()) {
            return true;
        }

        String t = filterText.toLowerCase();
        return (name.contains(t) || author.contains(t));
    }
}
