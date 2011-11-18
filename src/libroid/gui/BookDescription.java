package libroid.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JPanel;
import libroid.model.Book;

public class BookDescription extends JPanel {
    private Book b;

    public BookDescription(){
        setPreferredSize(new Dimension(1, 300));

        JButton button = new JButton("Hide");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel(false);
            }
        });
        add(button);
    }

    public void showPanel(Boolean show){
        if(show){
            final Timer t = new Timer();
            TimerTask opening = new TimerTask() {
            @Override
            public void run() {
                int x = getWidth();
                setPreferredSize(new Dimension(x+2, 300));
                if(x>200){
                    t.cancel();
                }
            }
            };
            t.schedule(opening, new Date(), 5);
        }else{
            final Timer t = new Timer();
            TimerTask opening = new TimerTask() {
            @Override
            public void run() {
                int x = getWidth();
                setPreferredSize(new Dimension(x-2, 300));
                if(x == 2){
                    t.cancel();
                }
            }
            };
            t.schedule(opening, new Date(), 5);
        }
    }
}
