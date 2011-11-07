package libroid;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import libroid.gui.MainFrame;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            MainFrame main = new MainFrame();
            main.setVisible(true);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }


}
