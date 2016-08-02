package mandelmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A two dimensional area.
 *
 * @author Hendrik Werner // s4549775
 */
public class Area {

    private final DoubleProperty upperLeftX = new SimpleDoubleProperty();
    private final DoubleProperty upperLeftY = new SimpleDoubleProperty();
    private final DoubleProperty width = new SimpleDoubleProperty();
    private final DoubleProperty height = new SimpleDoubleProperty();

    /**
     * @param upperLeftX the initial x value of the upper left corner of the area
     * @param upperLeftY the initial y value of the upper left corner of the area
     * @param width the initial width of the area
     * @param height the initial height of the area
     */
    public Area(double upperLeftX, double upperLeftY, double width, double height) {
        this.upperLeftX.set(upperLeftX);
        this.upperLeftY.set(upperLeftY);
        this.width.set(width);
        this.height.set(height);
    }

    /**
     * @return the upper left corner on the x-axis
     */
    public DoubleProperty upperLeftXProperty() {
        return upperLeftX;
    }

    /**
     * @return the upper left corner on the y-axis
     */
    public DoubleProperty upperLeftYProperty() {
        return upperLeftY;
    }

    /**
     * @return the width
     */
    public DoubleProperty widthProperty() {
        return width;
    }

    /**
     * @return the height
     */
    public DoubleProperty heightProperty() {
        return height;
    }

    /**
     * @return the upper left corner on the x-axis
     */
    public double getUpperLeftX() {
        return upperLeftX.get();
    }

    /**
     * @return the upper left corner on the y-axis
     */
    public double getUpperLeftY() {
        return upperLeftY.get();
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width.get();
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height.get();
    }

}
