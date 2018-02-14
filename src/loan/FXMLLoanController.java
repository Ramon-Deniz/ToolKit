package loan;

import ToolKitConstant.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import withdrawal.WithdrawalLogic;

/**
 * FXML Controller class
 *
 * @author ramon
 */
public class FXMLLoanController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button drag;
    @FXML
    private TextField loanAmount;
    @FXML
    private TextField months;
    @FXML
    private TextField interest;
    @FXML
    private TextField result;
    @FXML
    private Button calculate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Constant.stage.setScene(Constant.financeScene);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void handleMinimize(ActionEvent event) {
        Constant.stage.setIconified(true);
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
    
    @FXML
    private void handleCalculate(ActionEvent event){
        result.setText(WithdrawalLogic.calculate(loanAmount.getText(), months.getText(), interest.getText()));
    }

}
