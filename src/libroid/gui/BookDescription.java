package libroid.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import libroid.model.Book;

public class BookDescription extends JFrame {
    private Book book;
    private Point origin;
    private boolean editMode = true;

    private String title = "";
    JLabel titleLabel = new JLabel();
    JTextField titleText = new JTextField(12);
    private String author = "";
    JLabel authorLabel = new JLabel();
    JTextField authorText = new JTextField(12);
    private String genre = "";
    JLabel genreLabel = new JLabel();
    JTextField genreText = new JTextField(12);
    private String isbn;
    JLabel isbnLabel = new JLabel();
    JTextField isbnText = new JTextField(12);

    JPanel content = new JPanel(new CardLayout());

    public BookDescription(){
        setSize(200, 200);
        setLayout(new BorderLayout());
        setUndecorated(true);

        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JToggleButton editButton = new JToggleButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) content.getLayout();
                cl.next(content);
            }
        });
        toolBar.add(editButton);

        JButton hideButton = new JButton("Hide");
        hideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        toolBar.add(hideButton);

        add(toolBar, BorderLayout.NORTH);

        JPanel view = new JPanel();
            view.add(titleLabel);
            view.add(authorLabel);
            view.add(genreLabel);
            view.add(isbnLabel);

        JPanel edit = new JPanel();
            edit.add(titleText);
            edit.add(authorText);
            edit.add(genreText);
            edit.add(isbnText);

        content.add(view, "");
        content.add(edit, "");

        add(content, BorderLayout.CENTER);

        addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent e) {}
            public void windowLostFocus(WindowEvent e) {
                if(isVisible()){
                   dispose();
                }
            }
        });
    }

    public void showPanel(){
        requestFocusInWindow();
        setLocation(origin.x, origin.y);
        setVisible(true);
    }

    void setOriginPoint(int x, int y) {
        origin = new Point(x, y);
    }

    void setHeight(int height) {
        setSize(getSize().width, height);
    }

    void setBook(Book b){
        this.book = b;
        title = b.getName();
        titleText.setText(title);
        titleLabel.setText(title);
        author = b.getAuthor();
        authorText.setText(author);
        authorLabel.setText(author);
        genre = b.getGenre();
        genreLabel.setText(genre);
        genreText.setText(genre);
        isbn = ""+b.getIsbn();
        isbnLabel.setText(isbn);
        isbnText.setText(isbn);
    }
}
