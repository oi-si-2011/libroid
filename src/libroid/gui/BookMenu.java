package libroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

class BookMenu extends JPopupMenu implements MouseListener {
    private LibraryTable lt;
    private BookDescription description = new BookDescription();

    public BookMenu(final LibraryTable lt){
        this.lt = lt;

        if(lt.getSelectedRowCount() == 1){
            JMenuItem menuItem = new JMenuItem("Open book");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                }
            });
            add(menuItem);

            menuItem = new JMenuItem("Remove book");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lt.removeSelectedBooks();
                }
            });
            add(menuItem);

        }else{
            JMenuItem menuItem = new JMenuItem("Create new list");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //lt.createNewList();
                }
            });
            add(menuItem);

            menuItem = new JMenuItem("Remove books");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lt.removeSelectedBooks();
                }
            });
            add(menuItem);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
            if(lt.getSelectedRowCount() == 1){
                description.setOriginPoint(e.getXOnScreen(), e.getYOnScreen());
                description.setBook(lt.getSelectedBooks().get(0));
                description.showPanel();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()){
            if(lt.getSelectedRowCount() <= 1){
                int rowIndex = lt.rowAtPoint(e.getPoint());
                lt.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
            }
            BookMenu m = new BookMenu(lt);
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