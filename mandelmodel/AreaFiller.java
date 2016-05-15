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
    private ColorMode colorMode;
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
        colorMode = mode;
        updateMandelMap();
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
     * @param x the center x value
     * @param y the center y value
     * @param zoomFactor the factor to zoom by
     */
    public void zoom(double x, double y, double zoomFactor) {
        double width = area.width / zoomFactor;
        double height = area.height / zoomFactor;
        area = new Area(
                area.upperLeftX + (x / size) * area.width - width / 2,
                area.upperLeftY + (y / size) * area.height - height / 2,
                width,
                height
        );
        updateMandelMap();
    }

    /**
     * Zoom in on an area.
     *
     * @param startX the x value of the upper left corner of the area
     * @param startY the y value of the upper left corner of the area
     * @param widthOnCanvas the width of the area on the canvas
     * @param heightOnCanvas the height of the area on the canvas
     */
    public void zoom(double startX, double startY, double widthOnCanvas, double heightOnCanvas) {
        area = new Area(
                area.upperLeftX + (startX / size) * area.width,
                area.upperLeftY + (startY / size) * area.height,
                (widthOnCanvas / size) * area.width,
                (heightOnCanvas / size) * area.height
        );
        updateMandelMap();
    }

    /**
     * @param area the area to set
     */
    public void setArea(Area area) {
        this.area = area;
        updateMandelMap();
    }

    /**
     * @param iterations the iterations to set
     */
    public void setIterations(int iterations) {
        this.iterations = iterations;
        updateColorMap();
        updateMandelMap();
    }

    /**
     * Update the map of Mandelbrot values.
     */
    private void updateMandelMap() {
        mandelMap = new MandelbrotMap(area, size, iterations);
    }

    /**
     * Update the color map.
     */
    private void updateColorMap() {
        colorMap = new ColorMap(iterations, colorMode);
    }

}
