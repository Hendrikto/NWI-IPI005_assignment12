package mandelmodel;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;

/**
 * Fill a canvas with a representation of the Mandelbrot set.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class AreaFiller {

    private int iterations;
    private ColorMap colorMap;

    /**
     * @param iterations the maximum number of iterations per point
     * @param mode the color mode
     */
    public AreaFiller(int iterations, ColorMode mode) {
        this.iterations = iterations;
        colorMap = new ColorMap(iterations, ColorMode.ColorfulInverted);
    }

    /**
     * @param iterations the maximum number of iterations per point
     */
    public AreaFiller(int iterations) {
        this(iterations, ColorMode.ColorfulInverted);
    }

    public AreaFiller() {
        this(20);
    }

    /**
     * Fill the canvas with some arbitrarily chosen pattern.
     *
     * @param canvas the canvas to fill
     * @param x staring position on the x-axis
     * @param y starting position on the y-axis
     * @param scale the scaling factor
     */
    public void fill(Canvas canvas, double x, double y, int scale) {
        int imageWith = (int) canvas.getWidth();
        int imageHeight = (int) canvas.getHeight();
        final PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (int i = 0; i < imageWith; i++) {
            for (int j = 0; j < imageHeight; j++) {
                int colorIndex = MandelbrotGenerator.getValue(
                        x + (double) i / scale,
                        y + (double) j / scale,
                        iterations
                );
                pixelWriter.setColor(i, j, colorMap.getColor(colorIndex));
            }
        }
    }

}
