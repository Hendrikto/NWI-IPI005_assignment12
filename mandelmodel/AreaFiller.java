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
    private double centerX;
    private double centerY;
    private ColorMap colorMap;
    private MandelbrotMap mandelMap;

    /**
     * @param size the size of the area to fill
     * @param centerX the x value of the center position
     * @param centerY the y value of the center position
     * @param scale the scaling factor
     * @param iterations the maximum number of iterations per point
     * @param mode the color mode
     */
    public AreaFiller(int size, double centerX, double centerY, int scale, int iterations, ColorMode mode) {
        this.size = size;
        this.centerX = centerX;
        this.centerY = centerY;
        this.scale = scale;
        this.iterations = iterations;
        colorMap = new ColorMap(iterations, mode);
        mandelMap = new MandelbrotMap(size, centerX, centerY, scale, iterations);
    }

    /**
     * @param size the size of the area to fill
     * @param centerX the x value of the center position
     * @param centerY the y value of the center position
     * @param scale the scaling factor
     */
    public AreaFiller(int size, double centerX, double centerY, int scale) {
        this(size, centerX, centerY, scale, 20, ColorMode.ColorfulInverted);
    }

    /**
     * Fill the canvas with a pattern based on the Mandelbrot values.
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

    /**
     * Zoom to a new center by a given factor.
     *
     * @param centerX
     * @param centerY
     * @param zoomFactor
     */
    public void zoom(double centerX, double centerY, int zoomFactor) {
        this.centerX = centerX;
        this.centerY = centerY;
        iterations = iterations * zoomFactor;
        scale = scale * zoomFactor;
        mandelMap = new MandelbrotMap(size, centerX, centerY, scale, iterations);
    }

}
