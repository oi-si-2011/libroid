package libroid;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import libroid.gui.MainFrame;
import libroid.model.Model;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Program entry point.
     */
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {

        final Model model = Model.createSampleModel();

        SwingUtilities.invokeAndWait(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
                MainFrame main = new MainFrame(model);
                main.setVisible(true);
            }
        });
    }
}
