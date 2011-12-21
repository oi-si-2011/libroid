package libroid.gui;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Podrobnosti např. o maximálním rozměru obrázku knihy.
 */
public class Configuration {

    /**
     * Rozměry - šířka, výška.
     */
    public static class Dimensions {

        private final int width;
        private final int height;

        public Dimensions(int w, int h) {
            this.width = w;
            this.height = h;
        }

        /**
         * Vrátí šírku.
         */
        public int getWidth() {
            return width;
        }

        /**
         * Vrátí výšku.
         */
        public int getHeight() {
            return height;
        }
    }

    /**
     * Maximální rozměry obrázku knihy.
     */
    public Dimensions getMaxBookletDimensions() {
        return new Dimensions(100, 100);
    }

    /**
     * Výchozí obrázek, pokud kniha žádný obrázek nemá.
     */
    public Image getDefaultBookletImage() {
        Image im = Toolkit.getDefaultToolkit().createImage("icons/logo.jpg");
        return im;
    }

}
