package mandelmodel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * A skeleton class illustrating the use of a pixelWriter
 *
 * @author Sjaak Smetsers
 */
public class AreaFiller {

    public static final int MAX_ITERATIONS = 20;

    private static final ColorMap COLOR_MAP = new ColorMap(MAX_ITERATIONS);

    /**
     * Fill the canvas with some arbitrarily chosen pattern.
     *
     * @param canvas the canvas to fill
     */
    public void fill(Canvas canvas) {
        int imageWith = (int) canvas.getWidth();
        int imageHeight = (int) canvas.getHeight();
        final PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (int i = 0; i < imageWith; i++) {
            for (int j = 0; j < imageHeight; j++) {
                int colorIndex = i / 5 * imageWith / 5 + j / 5;
                pixelWriter.setColor(i, j, COLOR_MAP.getColor(colorIndex));
            }
        }
    }

}
