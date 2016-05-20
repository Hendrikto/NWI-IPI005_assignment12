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

    /**
     * @param area the area to calculate Mandelbrot values for
     * @param size the width and height of the map
     * @param iterations the maximum number of iterations
     */
    public MandelbrotMap(Area area, int size, int iterations) {
        values = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                values[x][y] = getMandelbrotValue(
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
