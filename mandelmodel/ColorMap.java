package mandelmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

/**
 * Map Mandelbrot values to colors.
 *
 * @author Hendrik Werner // s4549775
 */
public class ColorMap {

    private final IntegerProperty iterations = new SimpleIntegerProperty();
    private final ObjectProperty<ColorMode> mode = new SimpleObjectProperty<>();

    private Color[] rgbColors;

    public ColorMap() {
        mode.addListener((observable, o, n) -> calculateValues());
        iterations.addListener(this::handleIterationsChange);
    }

    public IntegerProperty iterationsProperty() {
        return iterations;
    }

    public ObjectProperty<ColorMode> modeProperty() {
        return mode;
    }

    /**
     * @param mandelValue the Mandelbrot value
     * @return element of rgbColors at index mandelValue modulo the size of the array
     */
    public Color getColor(int mandelValue) {
        return rgbColors[mandelValue % rgbColors.length];
    }

    /**
     * Handle an iterations change by resizing and updating the color array.
     */
    private void handleIterationsChange(ObservableValue<? extends Number> observable, Number o, Number n) {
        rgbColors = new Color[iterations.get()];
        calculateValues();
    }

    /**
     * Update the color array.
     */
    private void calculateValues() {
        for (int i = 0; i < rgbColors.length; i++) {
            rgbColors[i] = mode.get().getColor(i, rgbColors.length);
        }
    }

}
