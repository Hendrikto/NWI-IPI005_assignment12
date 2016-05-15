package mandelmodel;

/**
 * A map of Mandelbrot values.
 *
 * @author Hendrik Werner // s4549775
 */
public class MandelbrotMap {

    private final int[][] values;

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

    public int getValue(int x, int y) {
        return values[x][y];
    }

}
