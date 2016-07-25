package mandelfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import mandelmodel.Area;
import mandelmodel.AreaFiller;
import mandelmodel.ColorMode;

/**
 * FXML Controller class
 *
 * @author Hendrik
 */
public class UIController implements Initializable {

    @FXML Canvas canvas;
    @FXML TextField xInput;
    @FXML TextField yInput;
    @FXML TextField sizeInput;
    @FXML ChoiceBox<ColorMode> colorChoice;

    private AreaFiller filler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colorChoice.getItems().addAll(ColorMode.values());
        filler = new AreaFiller(new Area(-2, -2, 4, 4), (int) canvas.getWidth());
        filler.fill(canvas);
    }

}
