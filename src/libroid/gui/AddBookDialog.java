package libroid.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import libroid.model.Book;
import libroid.model.Model;

/**
 * Dialogové okno pro přidání knížky.
 */
class AddBookDialog extends JFrame implements ActionListener {

    private JFileChooser fileChooser = new JFileChooser();
    private File file;
    private Book book;
    private Model model;
    private String uri;
    private JButton confirm = new JButton("Confirm");
    private JTextField name = new JTextField(12);
    private JTextField author = new JTextField(12);
    private JTextField genre = new JTextField(12);
    private JTextField isbn = new JTextField(12);

    public AddBookDialog(Model m) {
        this.model = m;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Name"));
        c.gridx = 0;
        c.gridy = 0;
        add(panel, c);

        panel = new JPanel();
        panel.add(name);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        add(panel, c);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Author"));
        c.gridx = 0;
        c.gridy = 1;
        add(panel, c);

        panel = new JPanel();
        panel.add(author);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        add(panel, c);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Genre"));
        c.gridx = 0;
        c.gridy = 2;
        add(panel, c);

        panel = new JPanel();
        panel.add(genre);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        add(panel, c);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("ISBN"));
        c.gridx = 0;
        c.gridy = 3;
        add(panel, c);

        panel = new JPanel();
        panel.add(isbn);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        add(panel, c);

        panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(confirm);
        c.gridx = 2;
        c.gridy = 4;
        add(panel, c);

        pack();
        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));

        confirm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                uri = file.getAbsolutePath();
                int isbnInt = 0;
                try {
                    isbnInt = Integer.valueOf(isbn.getText());
                } catch (Exception ex) {
                }
                book = new Book(name.getText(), author.getText(), uri, genre.getText(), isbnInt);
                model.addBook(book);
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        fileChooser.setFileFilter(new BookFileFilter());
        fileChooser.showOpenDialog(this);
        file = fileChooser.getSelectedFile();
        if (file != null) {
            setVisible(true);
        }
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
}
