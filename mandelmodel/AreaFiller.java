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
    private int size;
    private ColorMap colorMap;
    private MandelbrotMap mandelMap;
    private Area area;

    /**
     * @param area the area to draw
     * @param size the size of the canvas
     * @param iterations the maximum number of iterations per point
     * @param mode the color mode
     */
    public AreaFiller(Area area, int size, int iterations, ColorMode mode) {
        this.size = size;
        this.iterations = iterations;
        this.area = area;
        colorMap = new ColorMap(iterations, mode);
        mandelMap = new MandelbrotMap(area, size, iterations);
    }

    /**
     * @param area the area to draw
     * @param size the size of the canvas
     */
    public AreaFiller(Area area, int size) {
        this(area, size, 20, ColorMode.ColorfulInverted);
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
        iterations = iterations * zoomFactor;
        scale = scale * zoomFactor;
        mandelMap = new MandelbrotMap(size, centerX, centerY, scale, iterations);
    }

}
