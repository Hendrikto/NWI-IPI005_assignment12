package mandelfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mandelmodel.Area;
import mandelmodel.AreaFiller;

/**
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class MandelFX extends Application {

    public static final int GRID_SIZE = 1000;
    private static final int SPACING = 10;

    private AreaFiller filler;
    private double startX;
    private double startY;

    /**
     * JavaFX
     */
    private Group canvasContainer;
    private Canvas canvas;
    private Rectangle selection;
    private TextField inputCenterX;
    private TextField inputCenterY;
    private TextField inputScale;
    private Button btnDraw;

    /**
     * Start the app.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mandelbrot");
        primaryStage.setScene(makeScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Create the scene.
     *
     * @return the scene
     */
    private Scene makeScene() {
        canvas = new Canvas(GRID_SIZE, GRID_SIZE);
        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseReleased(this::handleMouseReleased);
        filler = new AreaFiller(new Area(-2, -2, 4, 4), GRID_SIZE);
        filler.fill(canvas);
        selection = new Rectangle();
        selection.setStroke(Color.GREENYELLOW);
        selection.setFill(Color.TRANSPARENT);
        canvasContainer = new Group(canvas);
        canvasContainer.setOnMouseDragged(this::handleMouseDragged);
        HBox root = new HBox(canvasContainer, makeControls());
        Scene scene = new Scene(root);
        return scene;
    }

    private Pane makeControls() {
        inputCenterX = new TextField("0");
        inputCenterY = new TextField("0");
        inputScale = new TextField("250");
        btnDraw = new Button("Draw");
        btnDraw.setOnAction(this::handleDrawButtonAction);
        GridPane grid = new GridPane();
        grid.setHgap(SPACING);
        grid.setVgap(SPACING);
        grid.add(new Label("center x:"), 0, 0);
        grid.add(inputCenterX, 1, 0);
        grid.add(new Label("center y:"), 0, 1);
        grid.add(inputCenterY, 1, 1);
        grid.add(new Label("scaling factor:"), 0, 2);
        grid.add(inputScale, 1, 2);
        grid.add(btnDraw, 1, 3);
        VBox controls = new VBox(grid);
        controls.setPadding(new Insets(SPACING));
        return controls;
    }

    /**
     * Handle a mouse button press.
     *
     * @param e the mouse event
     */
    private void handleMousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    /**
     * Handle a mouse button release.
     *
     * @param e the mouse event
     */
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
        canvasContainer.getChildren().remove(selection);
    }

    /**
     * Handle dragging the mouse.
     *
     * @param e the mouse event
     */
    private void handleMouseDragged(MouseEvent e) {
        if (!canvasContainer.getChildren().contains(selection)) {
            canvasContainer.getChildren().add(selection);
        }
        selection.setX(Math.min(startX, e.getX()));
        selection.setY(Math.min(startY, e.getY()));
        selection.setWidth(Math.abs(startX - e.getX()));
        selection.setHeight(Math.abs(startY - e.getY()));
    }

    private void handleDrawButtonAction(ActionEvent e) {
        double size = GRID_SIZE / Double.parseDouble(inputScale.getText());
        System.out.println(size);
        filler.setArea(new Area(
                Double.parseDouble(inputCenterX.getText()) - size / 2,
                Double.parseDouble(inputCenterY.getText()) - size / 2,
                size,
                size
        ));
        filler.fill(canvas);
    }

}
