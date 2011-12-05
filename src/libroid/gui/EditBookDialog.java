package libroid.gui;

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

    private JFileChooser fileChooser = new JFileChooser();
    private File file;
    private Book book;
    private Model model;
    private String uri;
    private JButton confirm = new JButton("Confirm");
    private JTextField nameField = new JTextField(20);
    private JTextField authorField = new JTextField(20);
    private JTextField genre = new JTextField(12);
    private JTextField isbn = new JTextField(12);

    private EditBookDialog(JFrame owner, final Model model) {
        super(owner);
        this.model = model;

        setTitle("Add Book");

        GroupLayout layout = new GroupLayout(this.getContentPane());
        getContentPane().setLayout(layout);

        // pro vice informaci o GroupLayout:
        // http://docs.oracle.com/javase/tutorial/uiswing/layout/group.html

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        final JLabel nameLabel = new JLabel("Name:");
        final JLabel authorLabel = new JLabel("Author:");

        final JButton cancelButton = new JButton("Cancel");
        final JButton confirmButton = new JButton("Confirm");

        cancelButton.addActionListener(new DisposeActionListener());
        confirmButton.addActionListener(new ConfirmActionListener(model));

        layout.setHorizontalGroup(
                /* */layout.createParallelGroup(GroupLayout.Alignment.TRAILING).
                /*   */addGroup(
                /*     */layout.createSequentialGroup().
                /*       */addGroup(
                /*         */layout.createParallelGroup().
                /*           */addComponent(nameLabel).
                /*           */addComponent(authorLabel)).
                /*       */addGroup(
                /*         */layout.createParallelGroup().
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
            EditBookDialog d = new EditBookDialog(owner, model);
            d.setModal(true);
            d.setVisible(true);
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
            model.addBook(new Book(name, author));
            dispose();
        }
    }
}
