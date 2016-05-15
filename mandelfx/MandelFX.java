package mandelfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
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

    private Canvas canvas;

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
        canvas.setOnMouseClicked(this::handleMouseClick);
        filler = new AreaFiller(new Area(-2, -2, 4, 4), GRID_SIZE);
        filler.fill(canvas);
        Group root = new Group(canvas);
        Scene scene = new Scene(root);
        return scene;
    }

    private void handleMouseClick(MouseEvent e) {
        System.out.printf("x: %f y: %f\n", e.getX(), e.getY());
    }

}
