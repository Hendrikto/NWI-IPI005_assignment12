package mandelmodel;

/**
 * A map of Mandelbrot values.
 *
 * @author Hendrik Werner // s4549775
 */
public class MandelbrotMap {

    private final int[][] values;

    public MandelbrotMap(int size, double centerX, double centerY, int scale, int iterations) {
        double startX = centerX - size / (scale * 2);
        double startY = centerY - size / (scale * 2);
        values = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                values[x][y] = MandelbrotGenerator.getValue(
                        startX + (double) x / scale,
                        startY + (double) y / scale,
                        iterations
                );
            }
        }
    }

    public int getValue(int x, int y) {
        return values[x][y];
    }

}
