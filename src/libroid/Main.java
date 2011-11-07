package libroid;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import libroid.gui.MainFrame;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        MainFrame main = new MainFrame();
        main.setVisible(true);
    }
}
