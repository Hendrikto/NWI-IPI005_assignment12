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
    private int scale;
    private int size;
    private double startX;
    private double startY;
    private ColorMap colorMap;
    private MandelbrotMap mandelMap;

    /**
     * @param size the size of the area to fill
     * @param startX the x value of the upper left corner
     * @param startY the y value of the upper left corner
     * @param scale the scaling factor
     * @param iterations the maximum number of iterations per point
     * @param mode the color mode
     */
    public AreaFiller(int size, double startX, double startY, int scale, int iterations, ColorMode mode) {
        this.size = size;
        this.startX = startX;
        this.startY = startY;
        this.scale = scale;
        this.iterations = iterations;
        colorMap = new ColorMap(iterations, mode);
        mandelMap = new MandelbrotMap(size, startX, startY, scale, iterations);
    }

    /**
     * @param size the size of the area to fill
     * @param startX the x value of the upper left corner
     * @param startY the y value of the upper left corner
     * @param scale the scaling factor
     */
    public AreaFiller(int size, double startX, double startY, int scale) {
        this(size, startX, startY, scale, 20, ColorMode.ColorfulInverted);
    }

    /**
     * Fill the canvas with some arbitrarily chosen pattern.
     *
     * @param canvas the canvas to fill
     */
    public void fill(Canvas canvas) {
        int imageWidth = (int) canvas.getWidth();
        int imageHeight = (int) canvas.getHeight();
        final PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                pixelWriter.setColor(i, j, colorMap.getColor(mandelMap.getValue(i, j)));
            }
        }
    }

}
