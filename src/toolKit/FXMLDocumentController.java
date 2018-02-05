
package toolKit;

import ToolKitConstant.Constant;
import calculator.Calculator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 *
 * @author ramon
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button calculator;
    @FXML
    private Font x1;
    @FXML
    private Button close;
    @FXML
    private Button drag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCalculatorAction(ActionEvent event) throws IOException {
        Constant.stage.setScene(Constant.calculatorScene);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void handleDragPress(MouseEvent event) {
        double xOffset = Constant.stage.getX() - event.getScreenX();
        double yOffset = Constant.stage.getY() - event.getScreenY();
        drag.setOnMouseDragged((MouseEvent mouseEvent) -> {
            Constant.stage.setX(mouseEvent.getScreenX() + xOffset);
            Constant.stage.setY(mouseEvent.getScreenY() + yOffset);
        });
    }
}
