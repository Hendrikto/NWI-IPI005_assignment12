package mandelmodel;

/**
 * A generator of Mandelbrot values.
 *
 * @author Hendrik Werner // s4549775
 */
public class MandelbrotGenerator {

    private static final int DEFAULT_ITERATIONS = 20;

    /**
     * Get the Mandelbrot value at (a, b) using the default number of
     * iterations.
     *
     * @param a the x-coordinate
     * @param b the y-coordinate
     * @return the Mandelbrot value at (a, b)
     */
    public static int getValue(double a, double b) {
        return getValue(a, b, DEFAULT_ITERATIONS);
    }

    /**
     * Get the Mandelbrot value at (a, b) using the given number of iterations.
     *
     * @param a the x-coordinate
     * @param b the y-coordinate
     * @param maxIterations the number of iterations
     * @return the Mandelbrot value at (a, b)
     */
    public static int getValue(final double a, final double b, int maxIterations) {
        int iterations = 0;
        double x = a;
        double y = b;
        while (x * x + y * y < 4 && iterations < maxIterations) {
            double oldX = x;
            double oldY = y;
            x = oldX * oldX - oldY * oldY + a;
            y = 2 * oldX * oldY + b;
            iterations++;
        }
        return iterations;
    }

}