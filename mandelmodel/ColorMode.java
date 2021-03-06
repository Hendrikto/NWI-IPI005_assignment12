package mandelmodel;

import javafx.scene.paint.Color;

/**
 * Different color modes for the ColorMap.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public enum ColorMode {
    BlackWhite {
        @Override
        public Color getColor(int i, int max) {
            return i % 2 == 0 ? Color.BLACK : Color.WHITE;
        }
    },
    Grayscale {
        @Override
        public Color getColor(int i, int max) {
            return Color.gray((double) i / max);
        }
    },
    Random {
        @Override
        public Color getColor(int i, int max) {
            return Color.color(Math.random(), Math.random(), Math.random());
        }
    },
    Red {
        @Override
        public Color getColor(int i, int max) {
            return Color.color((double) i / max, 0, 0);
        }
    },
    Green {
        @Override
        public Color getColor(int i, int max) {
            return Color.color(0, (double) i / max, 0);
        }
    },
    Blue {
        @Override
        public Color getColor(int i, int max) {
            return Color.color(0, 0, (double) i / max);
        }
    },
    Colorful {
        @Override
        public Color getColor(int i, int max) {
            if (i == 0) {
                return Color.BLACK;
            }
            return Color.hsb((double) i / max * 360, 1, 1);
        }
    },
    ColorfulInverted {
        @Override
        public Color getColor(int i, int max) {
            return Colorful.getColor(i, max).deriveColor(180, 1, 1, 1);
        }
    };

    /**
     * @param i the index
     * @param max the maximum index
     * @return a color based off of i and max
     */
    public abstract Color getColor(int i, int max);

}
