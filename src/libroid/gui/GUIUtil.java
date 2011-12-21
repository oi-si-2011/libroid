package libroid.gui;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import libroid.gui.Configuration.Dimensions;
import libroid.model.Book;

public class GUIUtil {

    private static final Logger logger = Logger.getLogger(Book.class.getName());

    static Point getLocationForScreenCenter(Dimension size) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((d.width - size.width) / 2, (d.height - size.height) / 2);
    }

    static JComponent withBoldFont(JComponent c) {
        c.setFont(c.getFont().deriveFont(Font.BOLD));
        return c;
    }

    /**
     * Otevře knihu v externí aplikaci.
     * Je použito java.awt.Desktop.open().
     */
    static void openBook(Book book, JComponent dialogOwner) {
        File f = book.getFile();
        if (f == null) {
            JOptionPane.showMessageDialog(dialogOwner,
                    "This book has no file associated with it.",
                    "An error has occured",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        logger.log(Level.INFO, "Opening book {0} with file {1}", new Object[]{book, f});
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(f);
            } else {
                throw new IOException("Desktop not supported");
            }
        } catch (Exception ex) {
            logger.log(Level.INFO, "Caught exception {0}", ex);
            JOptionPane.showMessageDialog(dialogOwner,
                    "Couldn't open the book. Eithter the source file wasn't "
                    + "found or the filetype isn't supported.",
                    "An error has occured",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    static Image scaleImage(Image im, Dimensions maxDimensions) {
        return scaleImage(im, maxDimensions.getWidth(), maxDimensions.getHeight());
    }

    private static Image scaleImage(Image im, int maxWidth, int maxHeight) {
        letImageLoad(im);
        int w = im.getWidth(null);
        int h = im.getHeight(null);
        if (w < 0 || h < 0) {
            throw new RuntimeException("Invalid dimensions: " + w + "x" + h + " image: " + im);
        }
        if (w > maxWidth) {
            h = h * maxWidth / w;
            w = maxWidth;
        }
        if (h > maxHeight) {
            w = w * maxHeight / h;
            h = maxHeight;
        }
        return im.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }

    private static void letImageLoad(Image im) {
        Component component = new Component() {};
        MediaTracker tracker = new MediaTracker(component);
        tracker.addImage(im, 1);
        try {
            tracker.waitForID(1);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
