package libroid.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JComponent;

public class GUIUtil {

    static Point getLocationForScreenCenter(Dimension size) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((d.width - size.width) / 2, (d.height - size.height) / 2);
    }

    static JComponent withBoldFont(JComponent c) {
        c.setFont(c.getFont().deriveFont(Font.BOLD));
        return c;
    }
}
