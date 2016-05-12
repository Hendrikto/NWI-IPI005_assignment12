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

    /**
     * Creates and fills the array with the specified size
     *
     * @param maxColorIndex the size of the table
     * @param mode the color mode to use
     */
    public ColorMap(int maxColorIndex, ColorMode mode) {
        rgbColors = new Color[maxColorIndex];
        setColor(mode);
    }

    /**
     * Fill the color map using the color mode.
     */
    private void setColor(ColorMode mode) {
        for (int i = 0; i < rgbColors.length; i++) {
            rgbColors[i] = mode.getColor(i, rgbColors.length);
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
