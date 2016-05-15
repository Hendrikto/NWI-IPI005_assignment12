package mandelmodel;

/**
 * A two dimensional area.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class Area {

    public final double upperLeftX;
    public final double upperLeftY;
    public final double width;
    public final double height;

    /**
     * @param upperLeftX the x value of the upper left corner of the area
     * @param upperLeftY the y value of the upper left corner of the area
     * @param width the width of the area
     * @param height the height of the area
     */
    public Area(double upperLeftX, double upperLeftY, double width, double height) {
        this.upperLeftX = upperLeftX;
        this.upperLeftY = upperLeftY;
        this.width = width;
        this.height = height;
    }

}
