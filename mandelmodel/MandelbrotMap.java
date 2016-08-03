package mandelmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

/**
 * A map of Mandelbrot values.
 *
 * @author Hendrik Werner // s4549775
 */
public class MandelbrotMap {

    private final IntegerProperty iterations = new SimpleIntegerProperty(0);
    private final IntegerProperty size = new SimpleIntegerProperty(0);

    private final ObjectProperty<Area> area = new SimpleObjectProperty<>(new Area(-2, -2, 4, 4));

    private int[][] values;

    /**
     * Get the Mandelbrot value at (a, b) using the given number of iterations.
     *
     * @param a the x-coordinate
     * @param b the y-coordinate
     * @param maxIterations the number of iterations
     * @return the Mandelbrot value at (a, b)
     */
    private static int getMandelbrotValue(final double a, final double b, int maxIterations) {
        int iterations;
        double x = a;
        double y = b;
        for (iterations = 0; x * x + y * y < 4 && iterations < maxIterations; iterations++) {
            double oldX = x;
            double oldY = y;
            x = oldX * oldX - oldY * oldY + a;
            y = 2 * oldX * oldY + b;
        }
        return iterations;
    }

    public MandelbrotMap() {
        iterations.addListener((observable, o, n) -> calculateMandelbrotValues());
        area.get().upperLeftXProperty().addListener((observable, o, n) -> calculateMandelbrotValues());
        area.get().upperLeftYProperty().addListener((observable, o, n) -> calculateMandelbrotValues());
        area.get().widthProperty().addListener((observable, o, n) -> calculateMandelbrotValues());
        area.get().heightProperty().addListener((observable, o, n) -> calculateMandelbrotValues());
        area.addListener((observable, o, n) -> calculateMandelbrotValues());
        size.addListener(this::handleSizeChange);
    }

    public IntegerProperty iterationsProperty() {
        return iterations;
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public ObjectProperty<Area> areaProperty() {
        return area;
    }

    /**
     * @param x the x value
     * @param y the y value
     * @return the Mandelbrot value of the specified point
     */
    public int getValue(int x, int y) {
        return values[x][y];
    }

    /**
     * Handle a size change by resizing the value array and updating the values.
     */
    private void handleSizeChange(ObservableValue<? extends Number> observable, Number o, Number n) {
        values = new int[size.get()][size.get()];
        calculateMandelbrotValues();
    }

    /**
     * Calculate the Mandelbrot values.
     */
    private void calculateMandelbrotValues() {
        Area areaValue = area.get();
        for (int x = 0; x < size.get(); x++) {
            for (int y = 0; y < size.get(); y++) {
                values[x][y] = getMandelbrotValue(
                        areaValue.getUpperLeftX() + x * (areaValue.getWidth() / size.get()),
                        areaValue.getUpperLeftY() + y * (areaValue.getHeight() / size.get()),
                        iterations.get()
                );
            }
        }
    }

}
