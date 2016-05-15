package mandelmodel;

/**
 * A map of Mandelbrot values.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class MandelbrotMap {

    private final int[][] values;

    /**
     * @param area the area to calculate Mandelbrot values for
     * @param size the width and height of the map
     * @param iterations the maximum number of iterations
     */
    public MandelbrotMap(Area area, int size, int iterations) {
        values = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                values[x][y] = MandelbrotGenerator.getValue(
                        area.upperLeftX + x * (area.width / size),
                        area.upperLeftY + y * (area.height / size),
                        iterations
                );
            }
        }
    }

    /**
     * @param x the x value
     * @param y the y value
     * @return the Mandelbrot value of the specified point
     */
    public int getValue(int x, int y) {
        return values[x][y];
    }

}
