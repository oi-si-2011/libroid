package libroid.gui;

import java.awt.Image;
import java.awt.Toolkit;

public class Configuration {

    public static class Dimensions {

        private final int width;
        private final int height;

        public Dimensions(int w, int h) {
            this.width = w;
            this.height = h;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public Dimensions getMaxBookletDimensions() {
        return new Dimensions(100, 100);
    }

    public Image getDefaultBookletImage() {
        Image im = Toolkit.getDefaultToolkit().createImage("icons/logo.jpg");
        return im;
    }

}
