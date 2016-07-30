package mandelmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A two dimensional area.
 *
 * @author Hendrik Werner // s4549775
 */
public class Area {

    private final DoubleProperty upperLeftX;
    private final DoubleProperty upperLeftY;
    private final DoubleProperty width;
    private final DoubleProperty height;

    /**
     * @param upperLeftX the initial x value of the upper left corner of the area
     * @param upperLeftY the initial y value of the upper left corner of the area
     * @param width the initial width of the area
     * @param height the initial height of the area
     */
    public Area(double upperLeftX, double upperLeftY, double width, double height) {
        this.upperLeftX = new SimpleDoubleProperty(upperLeftX);
        this.upperLeftY = new SimpleDoubleProperty(upperLeftY);
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
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

}
