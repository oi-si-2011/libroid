package libroid.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class GUIUtil {

    static Point getLocationForScreenCenter(Dimension size) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((d.width - size.width) / 2, (d.height - size.height) / 2);
    }
}
