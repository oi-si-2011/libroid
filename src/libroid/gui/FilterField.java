package libroid.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FilterField extends JTextField {

    private LibraryTable table;

    public FilterField(LibraryTable table) {
        this.table = table;

        setColumns(20);
        setText("Filter");
        setForeground(Color.lightGray);
        setFocusAccelerator('f');
        requestFocusInWindow(false);

        addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setText("");
            }
        });

        getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }

            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });

        addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                setText("");
                setForeground(Color.black);
            }

            public void focusLost(FocusEvent e) {
                if (getText().equals("")) {
                    setText("Filter");
                    setForeground(Color.lightGray);
                }
            }
        });
    }

    private void newFilter() {
        if (getText().equals("Filter")) {
            return;
        }
        RowFilter<LibraryTableModel, Object> rf = new RowFilter<LibraryTableModel, Object>() {

            @Override
            public boolean include(Entry<? extends LibraryTableModel, ? extends Object> entry) {
                if (entry.getStringValue(0).toLowerCase().startsWith(getText().toLowerCase()) || entry.getStringValue(1).toLowerCase().startsWith(getText().toLowerCase())) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        table.setRowFilter(rf);
    }

    void setTable(LibraryTable t) {
        table = t;
    }
}