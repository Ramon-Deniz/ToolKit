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
import org.json.JSONException;

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
        try {
            contribute.setText(Constant.saved.getJSONObject("fourKInput").getString("contribute"));
            currentAge.setText(Constant.saved.getJSONObject("fourKInput").getString("contribute"));
            annualSalary.setText(Constant.saved.getJSONObject("fourKInput").getString("annualSalary"));
            retirementAge.setText(Constant.saved.getJSONObject("fourKInput").getString("retirementAge"));
            salaryIncrease.setText(Constant.saved.getJSONObject("fourKInput").getString("salaryIncrease"));
            currentBalance.setText(Constant.saved.getJSONObject("fourKInput").getString("currentBalance"));
            employerMatch.setText(Constant.saved.getJSONObject("fourKInput").getString("employerMatch"));
            inflation.setText(Constant.saved.getJSONObject("fourKInput").getString("inflation"));
            employerMatchEnds.setText(Constant.saved.getJSONObject("fourKInput").getString("employerMatchEnds"));
            rateOfReturn.setText(Constant.saved.getJSONObject("fourKInput").getString("rateOfReturn"));
            result.setText(Constant.saved.getJSONObject("fourKInput").getString("result"));
        } catch (JSONException e) {
            result.setText("Save error");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Constant.stage.setScene(Constant.financeScene);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Constant.save();
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
        updateJSON();
    }

    private void updateJSON() {
        try {
            Constant.saved.getJSONObject("fourKInput").put("contribute", contribute.getText());
            Constant.saved.getJSONObject("fourKInput").put("currentAge", currentAge.getText());
            Constant.saved.getJSONObject("fourKInput").put("annualSalary", annualSalary.getText());
            Constant.saved.getJSONObject("fourKInput").put("retirementAge", retirementAge.getText());
            Constant.saved.getJSONObject("fourKInput").put("salaryIncrease", salaryIncrease.getText());
            Constant.saved.getJSONObject("fourKInput").put("currentBalance", currentBalance.getText());
            Constant.saved.getJSONObject("fourKInput").put("employerMatch", employerMatch.getText());
            Constant.saved.getJSONObject("fourKInput").put("inflation", inflation.getText());
            Constant.saved.getJSONObject("fourKInput").put("employerMatchEnds", employerMatchEnds.getText());
            Constant.saved.getJSONObject("fourKInput").put("rateOfReturn", rateOfReturn.getText());
            Constant.saved.getJSONObject("fourKInput").put("result", result.getText());
        } catch (JSONException e) {
            System.out.println("Test");
        }
    }
}
