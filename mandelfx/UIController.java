package mandelfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import mandelmodel.Area;
import mandelmodel.AreaFiller;
import mandelmodel.ColorMode;

/**
 * FXML Controller class
 *
 * @author Hendrik
 */
public class UIController implements Initializable {

    private static final double ZOOM_IN = 2;
    private static final double ZOOM_OUT = .5;

    @FXML Canvas canvas;
    @FXML Rectangle selection;
    @FXML TextField xInput;
    @FXML TextField yInput;
    @FXML TextField sizeInput;
    @FXML TextField iterationsInput;
    @FXML Button drawButton;
    @FXML ChoiceBox<ColorMode> colorChoice;

    private AreaFiller filler;
    private boolean mouseDragged;
    private double startX;
    private double startY;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colorChoice.getItems().addAll(ColorMode.values());
        colorChoice.setValue(ColorMode.ColorfulInverted);
        colorChoice.setOnAction(this::handleUpdateColorMode);
        filler = new AreaFiller(new Area(-2, -2, 4, 4), (int) canvas.getWidth());
        filler.fill(canvas);
    }

    /**
     * Handle a mouse button press.
     *
     * @param e the mouse event
     */
    @FXML
    private void handleMousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    /**
     * Handle a mouse button release.
     *
     * @param e the mouse event
     */
    @FXML
    private void handleMouseReleased(MouseEvent e) {
        if (mouseDragged) {
            filler.zoom(
                    Math.min(startX, e.getX()),
                    Math.min(startY, e.getY()),
                    Math.abs(startX - e.getX()),
                    Math.abs(startY - e.getY())
            );
        } else {
            filler.zoom(e.getX(), e.getY(), e.isShiftDown() ? ZOOM_OUT : ZOOM_IN);
        }
        mouseDragged = false;
        filler.fill(canvas);
        selection.setVisible(false);
    }

    /**
     * Handle dragging the mouse.
     *
     * @param e the mouse event
     */
    @FXML
    private void handleMouseDragged(MouseEvent e) {
        mouseDragged = true;
        selection.setVisible(true);
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
    @FXML
    private void handleDraw(ActionEvent e) {
        double size = Double.parseDouble(sizeInput.getText());
        filler.setArea(new Area(
                Double.parseDouble(xInput.getText()) - size / 2,
                Double.parseDouble(yInput.getText()) - size / 2,
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
    @FXML
    private void handleUpdateIterations(ActionEvent e) {
        filler.setIterations(Integer.parseInt(iterationsInput.getText()));
        filler.fill(canvas);
    }

    /**
     * Update the color mode with data from the ui.
     *
     * @param e the action event
     */
    @FXML
    private void handleUpdateColorMode(ActionEvent e) {
        filler.setColorMode(colorChoice.getValue());
        filler.fill(canvas);
    }

}
