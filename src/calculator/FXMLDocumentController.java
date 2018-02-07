package calculator;

import ToolKitConstant.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Button leftParenthesis;
    @FXML
    private Button rightParenthesis;
    @FXML
    private Button power;
    @FXML
    private Button deleteCharacter;
    @FXML
    private Button clear;
    @FXML
    private Button percent;
    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button drag;
    @FXML
    private TextArea TextArea = new TextArea();
    @FXML
    private TextArea TextArea2 = new TextArea();
    @FXML
    private AnchorPane anchor = new AnchorPane();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TextArea.setText("Type in Expression");
        TextArea2.setText(" ");
    }

    @FXML
    public void initialize() {
        TextArea.setText(Constant.expression);
        TextArea2.setText(Constant.getAnswers());
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
        if (Constant.expression.length() == 0) {
            return;
        }
        Constant.expression = CalculatorLogic.solve(Constant.expression);
        Constant.updateAnswers();
        initialize();
    }

    @FXML
    private void handleKeyPress(KeyEvent key) {
        String valid = "(^*/+-0123456789.)";
        if (key.getCode().equals(KeyCode.ENTER) && Constant.expression.length() > 0) {
            Constant.expression = CalculatorLogic.solve(Constant.expression);
            Constant.updateAnswers();
        } else if (key.getCode().equals(KeyCode.BACK_SPACE) && Constant.expression.length() != 0) {
            Constant.expression = Constant.expression.substring(0, Constant.expression.length() - 1);
        } else if (valid.indexOf(key.getText().charAt(0)) != -1) {
            Constant.expression += key.getText();
        }
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

    @FXML
    private void handleLeftParenthesis(ActionEvent event) {
        Constant.expression += "(";
        initialize();
    }

    @FXML
    private void handleRightParenthesis(ActionEvent event) {
        Constant.expression += ")";
        initialize();
    }

    @FXML
    private void handlePower(ActionEvent event) {
        Constant.expression += "^";
        initialize();
    }

    @FXML
    private void handleFactorial(ActionEvent event) {
        Constant.expression += "!";
        initialize();
    }

    @FXML
    private void handleDeleteCharacter(ActionEvent event) {
        if (Constant.expression.length() != 0) {
            Constant.expression = Constant.expression.substring(0, Constant.expression.length() - 1);
        }
        initialize();
    }

    @FXML
    private void handleClear(ActionEvent event) {
        Constant.expression = "";
        initialize();
    }

    @FXML
    private void handlePercent(ActionEvent event) {
        Constant.expression = Constant.expression += "%";
        initialize();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Constant.stage.setScene(Constant.mainScene);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void handleMinimize(ActionEvent event){
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

}
