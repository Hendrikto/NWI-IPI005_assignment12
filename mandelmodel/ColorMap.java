package mandelmodel;

import javafx.scene.paint.Color;

/**
 * Converts indexes (ranging from 0 to maxColorIndex) to RGB colors
 *
 * @author Sjaak Smetsers
 * @version 06-05-2016
 */
public class ColorMap {

    private Color[] rgbColors;
    private static final int MAXRGB = 256;

    /**
     * Creates and fills the array with the specified size
     *
     * @param maxColorIndex the size of the table
     */
    public ColorMap(int maxColorIndex) {
        rgbColors = new Color[maxColorIndex];
        randomColorSet();
    }

    /**
     * @return a random value between 0 and MAXRGB
     */
    private static int randomRGB() {
        return (int) (Math.random() * MAXRGB);
    }

    /**
     * fills the rgbColors array with random Color values
     */
    private void randomColorSet() {
        for (int i = 0; i < rgbColors.length; i++) {
            rgbColors[i] = Color.rgb(randomRGB(), randomRGB(), randomRGB());
        }
    }

    /**
     * Fill the color map with a grayscale.
     */
    private void grayscaleColorSet() {
        int gray;
        for (int i = 0; i < rgbColors.length; i++) {
            gray = (int) ((double) i / rgbColors.length * MAXRGB);
            rgbColors[i] = Color.rgb(gray, gray, gray);
        }
    }

    /**
     * @param colorIndex the index of the requested color
     * @return element of rgbColors at index colorIndex modulo the size of the
     * array
     */
    public Color getColor(int colorIndex) {
        return rgbColors[colorIndex % rgbColors.length];
    }

}
