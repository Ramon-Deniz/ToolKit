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
import org.json.JSONException;
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
        try {
            loanAmount.setText(Constant.saved.getJSONObject("loanInput").getString("loanAmount"));
            months.setText(Constant.saved.getJSONObject("loanInput").getString("months"));
            interest.setText(Constant.saved.getJSONObject("loanInput").getString("interest"));
            result.setText(Constant.saved.getJSONObject("loanInput").getString("result"));
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
        result.setText(WithdrawalLogic.calculate(loanAmount.getText(), months.getText(), interest.getText()));
        updateJSON();
    }

    private void updateJSON() {
        try {
            Constant.saved.getJSONObject("loanInput").put("loanAmount", loanAmount.getText());
            Constant.saved.getJSONObject("loanInput").put("months", months.getText());
            Constant.saved.getJSONObject("loanInput").put("interest", interest.getText());
            Constant.saved.getJSONObject("loanInput").put("result", result.getText());
        } catch (JSONException e) {
        }
    }

}
