package libroid.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import libroid.model.Book;
import libroid.model.Model;

/**
 * Dialogové okno pro přidání knihy nebo úpravu stávající knihy.
 */
public class EditBookDialog extends JDialog {

    public static EditBookDialog show(JFrame owner, Model model) {
        return show(owner, model, null);
    }

    public static EditBookDialog show(JFrame owner, Model model, Book book) {
        EditBookDialog d = new EditBookDialog(owner, model, book);
        d.setModal(true);
        d.setVisible(true);
        return d;
    }
    private File file;
    private Book book;
    private final Model model;
    private final Book editedBook;
    private JFileChooser fileChooser = new JFileChooser();
    private JLabel currentFileLabel = new JLabel("-");
    private JTextField nameField = new JTextField(20);
    private JTextField authorField = new JTextField(20);
    private JTextField genre = new JTextField(12);
    private JTextField isbn = new JTextField(12);

    private EditBookDialog(JFrame owner, final Model model, Book book) {
        super(owner);
        this.model = model;
        this.editedBook = book;

        if (editedBook == null) {
            setTitle("Add Book");
        } else {
            setTitle("Edit Book");
        }

        GroupLayout layout = new GroupLayout(this.getContentPane());
        getContentPane().setLayout(layout);

        // pro vice informaci o GroupLayout:
        // http://docs.oracle.com/javase/tutorial/uiswing/layout/group.html

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        final JLabel fileLabel = new JLabel("File:");
        final JLabel nameLabel = new JLabel("Name:");
        final JLabel authorLabel = new JLabel("Author:");

        final JButton fileShowChooserButton = new JButton("Change");
        final JButton cancelButton = new JButton("Cancel");
        final JButton confirmButton = new JButton("Confirm");

        currentFileLabel.setBackground(Color.BLUE);

        fillFieldValuesWithBookParameters();

        fileShowChooserButton.addActionListener(new ShowFileChooserActionListener());
        cancelButton.addActionListener(new DisposeActionListener());
        confirmButton.addActionListener(new ConfirmActionListener(model));

        layout.setHorizontalGroup(
                /* */layout.createParallelGroup(GroupLayout.Alignment.TRAILING).
                /*   */addGroup(
                /*     */layout.createSequentialGroup().
                /*       */addGroup(
                /*         */layout.createParallelGroup().
                /*           */addComponent(fileLabel).
                /*           */addComponent(nameLabel).
                /*           */addComponent(authorLabel)).
                /*       */addGroup(
                /*         */layout.createParallelGroup().
                /*           */addGroup(
                /*             */layout.createSequentialGroup().
                /*               */addComponent(currentFileLabel).
                /*               */addComponent(fileShowChooserButton)).
                /*           */addComponent(nameField).
                /*           */addComponent(authorField))).
                /*   */addGroup(
                /*     */layout.createSequentialGroup().
                /*       */addComponent(cancelButton).
                /*       */addComponent(confirmButton)));

        layout.setVerticalGroup(
                /* */layout.createSequentialGroup().
                /*   */addGroup(
                /*     */layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                /*       */addComponent(fileLabel).
                /*       */addComponent(currentFileLabel).
                /*       */addComponent(fileShowChooserButton)).
                /*   */addGroup(
                /*     */layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                /*       */addComponent(nameLabel).
                /*       */addComponent(nameField)).
                /*   */addGroup(
                /*     */layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                /*       */addComponent(authorLabel).
                /*       */addComponent(authorField)).
                /*   */addGroup(
                /*     */layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                /*       */addComponent(cancelButton).
                /*       */addComponent(confirmButton)));

        pack();
        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));
    }

    /**
     * Voláno z konstruktoru.
     */
    private void fillFieldValuesWithBookParameters() {
        if (editedBook == null) {
            return;
        }
        if (editedBook.getUri() != null && !editedBook.getUri().isEmpty()) {
            currentFileLabel.setText(editedBook.getUri());
        }
        nameField.setText(editedBook.getName());
        authorField.setText(editedBook.getAuthor());
    }

    /**
     * Filtr souborů určitého typu (ebooky) v dialogovém okně pro vybrání
     * souboru.
     */
    private static class BookFileFilter extends FileFilter {

        public BookFileFilter() {
        }

        @Override
        public boolean accept(File f) {
            String fileName = f.getName().toLowerCase();
            return (f.isDirectory())
                    || fileName.endsWith(".epub")
                    || fileName.endsWith(".pdf")
                    || fileName.endsWith(".mobi")
                    ? true : false;
        }

        @Override
        public String getDescription() {
            return "Ebook files";
        }
    }

    /**
     * ActionListener, který zobrazí dialog pro přidání knihy.
     */
    public static class ShowDialogActionListener implements ActionListener {

        private Model model;
        private final JFrame owner;

        ShowDialogActionListener(JFrame owner, Model model) {
            this.owner = owner;
            this.model = model;
        }

        public void actionPerformed(ActionEvent ae) {
            EditBookDialog.show(owner, model);
        }
    }

    /**
     * Po stisknutí tlačítka Cancel. Zavře okno.
     */
    private class DisposeActionListener implements ActionListener {

        public DisposeActionListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    }

    /**
     * Po stisknutí tlačítka Confirm. Provede vytvoření nové knihy a
     * zavření okna.
     */
    private class ConfirmActionListener implements ActionListener {

        private final Model model;

        public ConfirmActionListener(Model model) {
            this.model = model;
        }

        public void actionPerformed(ActionEvent ae) {
            String name = nameField.getText();
            String author = authorField.getText();
            if (editedBook == null) {
                model.addBook(new Book(name, author));
            } else {
                editedBook.setName(name);
                editedBook.setAuthor(author);
            }
            dispose();
        }
    }

    private static class ShowFileChooserActionListener implements ActionListener {

        public ShowFileChooserActionListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            
        }
    }
}
