package mandelmodel;

import javafx.scene.paint.Color;

/**
 * Different color modes for the ColorMap.
 *
 * @author Hendrik Werner // s4549775
 */
public enum ColorMode {
    Grayscale {
        @Override
        public Color getColor(int i, int max) {
            int gray = (int) ((double) i / max * MAX_RGB);
            return Color.rgb(gray, gray, gray);
        }
    },
    Random {
        @Override
        public Color getColor(int i, int max) {
            return Color.rgb(randomRGB(), randomRGB(), randomRGB());
        }
        private int randomRGB() {
            return (int) (Math.random() * MAX_RGB);
        }
    };

    private static final int MAX_RGB = 255;

    public abstract Color getColor(int i, int max);

}
