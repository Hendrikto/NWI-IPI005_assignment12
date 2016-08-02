package mandelmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 * Map Mandelbrot values to colors.
 *
 * @author Hendrik Werner // s4549775
 */
public class ColorMap {

    private final IntegerProperty iterations = new SimpleIntegerProperty();
    private final ObjectProperty<ColorMode> mode = new SimpleObjectProperty<>();
    private final ObservableList<Color> colors = FXCollections.observableArrayList();

    public ColorMap() {
        mode.addListener((observable, o, n) -> calculateValues());
        iterations.addListener((observable, o, n) -> calculateValues());
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
        return colors.get(mandelValue % iterations.get());
    }

    /**
     * Update the color list.
     */
    private void calculateValues() {
        ColorMode colorMode = mode.get();
        int iterationsValue = iterations.get();
        Color[] rgbColors = new Color[iterationsValue];
        for (int i = 0; i < iterationsValue; i++) {
            rgbColors[i] = colorMode.getColor(i, iterationsValue);
        }
        colors.setAll(rgbColors);
    }

}
