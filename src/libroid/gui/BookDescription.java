package libroid.gui;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import libroid.model.Book;

public class BookDescription extends JFrame {
    private Book book;
    private Point origin;
    private String title = "";
    JLabel label = new JLabel(title);

    public BookDescription(){
        setSize(200, 200);
        setLayout(new FlowLayout());
        setUndecorated(true);

        JButton button = new JButton("Hide");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(button);
        add(label);

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
        title = b.getAuthor();
        label.setText(title);
    }
}
