package libroid.gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FilterField extends JTextField {

    private static final Logger logger = Logger.getLogger(FilterField.class.getName());
    private LibraryTable table;
    private boolean helpTextIsSet = false;

    public FilterField(LibraryTable table) {
        this.table = table;

        setColumns(20);
        setFocusAccelerator('f');
        requestFocusInWindow(false);

        setHelpText();

        getDocument().addDocumentListener(new TextChangedDocumentListener(this));
        addFocusListener(new SetHelpTextFocusListener(this));
    }

    private void textChanged() {
        if (!helpTextIsSet) {
            table.setFilterText(getText());
        }
    }

    private void setHelpText() {
        if (helpTextIsSet || !getText().isEmpty()) {
            return;
        }
        assert getText().isEmpty();
        helpTextIsSet = true; // pozor, toto musi byt nastaveno pred setText()
        setText("Filter");
        setForeground(Color.lightGray);
    }

    private void removeHelpText() {
        if (!helpTextIsSet) {
            return;
        }
        setText("");
        setForeground(Color.black);
        helpTextIsSet = false;
    }

    private static class TextChangedDocumentListener implements DocumentListener {

        private final FilterField filterField;

        public TextChangedDocumentListener(FilterField ff) {
            this.filterField = ff;
        }

        public void changedUpdate(DocumentEvent e) {
            filterField.textChanged();
        }

        public void insertUpdate(DocumentEvent e) {
            filterField.textChanged();
        }

        public void removeUpdate(DocumentEvent e) {
            filterField.textChanged();
        }
    }

    private class SetHelpTextFocusListener implements FocusListener {

        private final FilterField filterField;

        private SetHelpTextFocusListener(FilterField ff) {
            this.filterField = ff;
        }

        public void focusGained(FocusEvent e) {
            filterField.removeHelpText();
        }

        public void focusLost(FocusEvent e) {
            filterField.setHelpText();
        }
    }
}