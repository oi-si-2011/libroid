package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

class bookMenu extends JPopupMenu implements MouseListener {
    private LibraryTable lt;

    public bookMenu(final LibraryTable lt){
        this.lt = lt;
        JMenuItem menuItem = new JMenuItem("Open book");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(menuItem);

        menuItem = new JMenuItem("Remove book");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lt.removeBook();
            }
        });
        add(menuItem);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            JOptionPane.showMessageDialog(this, "Not yet implemented", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isPopupTrigger()){
            int rowIndex = lt.rowAtPoint(e.getPoint());
            lt.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
            bookMenu m = new bookMenu(lt);
            m.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}