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
            int gray = fraction(i, max);
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
    },
    Red {
        @Override
        public Color getColor(int i, int max) {
            return Color.rgb(fraction(i, max), 0, 0);
        }
    },
    Green {
        @Override
        public Color getColor(int i, int max) {
            return Color.rgb(0, fraction(i, max), 0);
        }
    },
    Blue {
        @Override
        public Color getColor(int i, int max) {
            return Color.rgb(0, 0, fraction(i, max));
        }
    };

    private static final int MAX_RGB = 255;

    public abstract Color getColor(int i, int max);

    private static int fraction(int i, int max) {
        return (int) ((double) i / max * MAX_RGB);
    }

}
