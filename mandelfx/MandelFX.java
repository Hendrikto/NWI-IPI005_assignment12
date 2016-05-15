package mandelfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mandelmodel.Area;
import mandelmodel.AreaFiller;

/**
 *
 * @author Sjaak Smetsers
 */
public class MandelFX extends Application {

    public static final int GRID_SIZE = 1000;

    private AreaFiller filler;
    private double startX;
    private double startY;

    private Group root;
    private Canvas canvas;
    private Rectangle selection;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mandelbrot");
        primaryStage.setScene(makeScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Scene makeScene() {
        canvas = new Canvas(GRID_SIZE, GRID_SIZE);
        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseReleased(this::handleMouseReleased);
        filler = new AreaFiller(new Area(-2, -2, 4, 4), GRID_SIZE);
        filler.fill(canvas);
        selection = new Rectangle();
        selection.setStroke(Color.GREENYELLOW);
        selection.setFill(Color.TRANSPARENT);
        root = new Group(canvas);
        root.setOnMouseDragged(this::handleMouseDrag);
        Scene scene = new Scene(root);
        return scene;
    }

    private void handleMousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    private void handleMouseReleased(MouseEvent e) {
        if (startX == e.getX() && startY == e.getY()) {
            filler.zoom(e.getX(), e.getY(), 2);
        } else {
            filler.zoom(
                    Math.min(startX, e.getX()),
                    Math.min(startY, e.getY()),
                    Math.abs(startX - e.getX()),
                    Math.abs(startY - e.getY())
            );
        }
        filler.fill(canvas);
        root.getChildren().remove(selection);
    }

    private void handleMouseDrag(MouseEvent e) {
        if (!root.getChildren().contains(selection)) {
            root.getChildren().add(selection);
        }
        selection.setX(Math.min(startX, e.getX()));
        selection.setY(Math.min(startY, e.getY()));
        selection.setWidth(Math.abs(startX - e.getX()));
        selection.setHeight(Math.abs(startY - e.getY()));
    }

}
