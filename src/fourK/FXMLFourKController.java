package fourK;

import ToolKitConstant.Constant;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ramon
 */
public class FXMLFourKController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button drag;
    @FXML
    private TextField contribute;
    @FXML
    private TextField currentAge;
    @FXML
    private TextField annualSalary;
    @FXML
    private TextField retirementAge;
    @FXML
    private TextField salaryIncrease;
    @FXML
    private TextField currentBalance;
    @FXML
    private TextField employerMatch;
    @FXML
    private TextField inflation;
    @FXML
    private TextField employerMatchEnds;
    @FXML
    private TextField rateOfReturn;
    @FXML
    private Button calculate;
    @FXML
    private TextField result;

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
    private void handleCalculate(ActionEvent event) {
        String amount = FourKLogic.calculate(contribute.getText(), currentAge.getText(), annualSalary.getText(),
                retirementAge.getText(), salaryIncrease.getText(), currentBalance.getText(), employerMatch.getText(),
                inflation.getText(), employerMatchEnds.getText(), rateOfReturn.getText());
        DecimalFormat pattern = new DecimalFormat("###,###,###.###");
        result.setText(pattern.format(Double.parseDouble(amount)));
    }

}
