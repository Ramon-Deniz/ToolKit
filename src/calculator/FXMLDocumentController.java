package calculator;

import ToolKitConstant.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author ramon
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button one;
    @FXML
    private Font x1;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button zero;
    @FXML
    private Button decimal;
    @FXML
    private Button equals;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button times;
    @FXML
    private Button divide;
    @FXML
    private TextArea TextArea = new TextArea();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TextArea.setText("Type in Expression");
    }

    @FXML
    public void initialize() {
        TextArea.setText(Constant.expression);
    }

    @FXML
    private void handleOne(ActionEvent event) {
        Constant.expression += "1";
        initialize();
    }

    @FXML
    private void handleTwo(ActionEvent event) {
        Constant.expression += "2";
        initialize();
    }

    @FXML
    private void handleThree(ActionEvent event) {
        Constant.expression += "3";
        initialize();
    }

    @FXML
    private void handleFour(ActionEvent event) {
        Constant.expression += "4";
        initialize();
    }

    @FXML
    private void handleFive(ActionEvent event) {
        Constant.expression += "5";
        initialize();
    }

    @FXML
    private void handleSix(ActionEvent event) {
        Constant.expression += "6";
        initialize();
    }

    @FXML
    private void handleSeven(ActionEvent event) {
        Constant.expression += "7";
        initialize();
    }

    @FXML
    private void handleEight(ActionEvent event) {
        Constant.expression += "8";
        initialize();
    }

    @FXML
    private void handleNine(ActionEvent event) {
        Constant.expression += "9";
        initialize();
    }

    @FXML
    private void handleZero(ActionEvent event) {
        Constant.expression += "0";
        initialize();
    }

    @FXML
    private void handleDecimal(ActionEvent event) {
        Constant.expression += ".";
        initialize();
    }

    @FXML
    private void handleEquals(ActionEvent event) {
        CalculatorLogic.solve(Constant.expression);
        initialize();
    }

    @FXML
    private void handlePlus(ActionEvent event) {
        Constant.expression += "+";
        initialize();
    }

    @FXML
    private void handleMinus(ActionEvent event) {
        Constant.expression += "-";
        initialize();
    }

    @FXML
    private void handleTimes(ActionEvent event) {
        Constant.expression += "x";
        initialize();
    }

    @FXML
    private void handleDivide(ActionEvent event) {
        Constant.expression += "/";
        initialize();
    }

}
