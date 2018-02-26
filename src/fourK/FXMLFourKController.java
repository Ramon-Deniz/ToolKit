package fourK;

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
import org.json.JSONException;
import org.json.JSONObject;

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
            JSONObject fourKJSON = Constant.saved.getJSONObject("fourKInput");
            contribute.setText(fourKJSON.getString("contribute"));
            currentAge.setText(fourKJSON.getString("currentAge"));
            annualSalary.setText(fourKJSON.getString("annualSalary"));
            retirementAge.setText(fourKJSON.getString("retirementAge"));
            salaryIncrease.setText(fourKJSON.getString("salaryIncrease"));
            currentBalance.setText(fourKJSON.getString("currentBalance"));
            employerMatch.setText(fourKJSON.getString("employerMatch"));
            inflation.setText(fourKJSON.getString("inflation"));
            employerMatchEnds.setText(fourKJSON.getString("employerMatchEnds"));
            rateOfReturn.setText(fourKJSON.getString("rateOfReturn"));
            result.setText(fourKJSON.getString("result"));
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
        result.setText(FourKLogic.calculate(contribute.getText(), currentAge.getText(), annualSalary.getText(),
                retirementAge.getText(), salaryIncrease.getText(), currentBalance.getText(), employerMatch.getText(),
                inflation.getText(), employerMatchEnds.getText(), rateOfReturn.getText()));
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
            Constant.displayErrorAndExit("Trouble with saving.");
        }
    }
}
