package mandelfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mandelmodel.Area;
import mandelmodel.AreaFiller;
import mandelmodel.ColorMode;

/**
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class MandelFX extends Application {

    private static final int GRID_SIZE = 1000;
    private static final int SPACING = 10;
    private static final double ZOOM_IN = 2;
    private static final double ZOOM_OUT = .5;

    private AreaFiller filler;
    private boolean mouseDragged;
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
    private TextField inputSize;
    private TextField inputIterations;
    private Button btnDraw;
    private Button btnUpdateIterations;
    private ChoiceBox<ColorMode> colorChoice;

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

    /**
     * @return the control panel
     */
    private Pane makeControls() {
        instantiateControls();
        Label areaLabel = new Label("Area");
        areaLabel.setFont(Font.font(20));
        Label detailsLabel = new Label("Details");
        detailsLabel.setFont(Font.font(20));
        VBox controls = new VBox(
                areaLabel,
                makeAreaControls(),
                detailsLabel,
                makeDetailControls()
        );
        controls.setPadding(new Insets(SPACING));
        controls.setSpacing(SPACING * 2);
        return controls;
    }

    /**
     * @return the area controls
     */
    private Pane makeAreaControls() {
        GridPane grid = new GridPane();
        grid.setHgap(SPACING);
        grid.setVgap(SPACING);
        grid.add(new Label("center x:"), 0, 0);
        grid.add(inputCenterX, 1, 0);
        grid.add(new Label("center y:"), 0, 1);
        grid.add(inputCenterY, 1, 1);
        grid.add(new Label("size:"), 0, 2);
        grid.add(inputSize, 1, 2);
        grid.add(btnDraw, 1, 3);
        return grid;
    }

    /**
     * @return the detail controls
     */
    private Pane makeDetailControls() {
        GridPane grid = new GridPane();
        grid.setHgap(SPACING);
        grid.setVgap(SPACING);
        grid.add(new Label("Iterations:"), 0, 0);
        grid.add(inputIterations, 1, 0);
        grid.add(btnUpdateIterations, 2, 0);
        grid.add(new Label("Color:"), 0, 1);
        grid.add(colorChoice, 1, 1);
        return grid;
    }

    /**
     * Instantiate control elements.
     */
    private void instantiateControls() {
        inputCenterX = new TextField("0");
        inputCenterY = new TextField("0");
        inputSize = new TextField("4");
        inputIterations = new TextField("20");
        btnDraw = new Button("Draw");
        btnDraw.setOnAction(this::handleDrawButtonAction);
        btnUpdateIterations = new Button("Update");
        btnUpdateIterations.setOnAction(this::handleUpdateIterations);
        colorChoice = new ChoiceBox<>();
        colorChoice.getItems().addAll(ColorMode.values());
        colorChoice.setValue(ColorMode.ColorfulInverted);
        colorChoice.setOnAction(this::handleUpdateColorMode);
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
        if (!mouseDragged) {
            double zoom;
            if (e.isShiftDown()) {
                zoom = ZOOM_OUT;
            } else {
                zoom = ZOOM_IN;
            }
            filler.zoom(e.getX(), e.getY(), zoom);
        } else {
            filler.zoom(
                    Math.min(startX, e.getX()),
                    Math.min(startY, e.getY()),
                    Math.abs(startX - e.getX()),
                    Math.abs(startY - e.getY())
            );
        }
        mouseDragged = false;
        filler.fill(canvas);
        canvasContainer.getChildren().remove(selection);
    }

    /**
     * Handle dragging the mouse.
     *
     * @param e the mouse event
     */
    private void handleMouseDragged(MouseEvent e) {
        mouseDragged = true;
        if (!canvasContainer.getChildren().contains(selection)) {
            canvasContainer.getChildren().add(selection);
        }
        selection.setX(Math.min(startX, e.getX()));
        selection.setY(Math.min(startY, e.getY()));
        selection.setWidth(Math.abs(startX - e.getX()));
        selection.setHeight(Math.abs(startY - e.getY()));
    }

    /**
     * Handle the draw button.
     *
     * @param e the action event
     */
    private void handleDrawButtonAction(ActionEvent e) {
        double size = Double.parseDouble(inputSize.getText());
        filler.setArea(new Area(
                Double.parseDouble(inputCenterX.getText()) - size / 2,
                Double.parseDouble(inputCenterY.getText()) - size / 2,
                size,
                size
        ));
        filler.fill(canvas);
    }

    /**
     * Update the iterations with data from the ui.
     *
     * @param e the action event
     */
    private void handleUpdateIterations(ActionEvent e) {
        filler.setIterations(Integer.parseInt(inputIterations.getText()));
        filler.fill(canvas);
    }

    /**
     * Update the color mode with data from the ui.
     *
     * @param e the action event
     */
    private void handleUpdateColorMode(ActionEvent e) {
        filler.setColorMode(colorChoice.getValue());
        filler.fill(canvas);
    }

}
