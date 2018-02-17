package conversion;

import ToolKitConstant.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author ramon
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button drag;
    @FXML
    private ComboBox conversionType;
    @FXML
    private TextArea inputText = new TextArea();
    @FXML
    private ComboBox initialUnits = new ComboBox();
    @FXML
    private ComboBox finalUnits = new ComboBox();
    @FXML
    private TextArea resultText = new TextArea();
    @FXML
    private Text initialUnitText = new Text();
    @FXML
    private Text finalUnitText = new Text();

    private ObservableList<String> currencyOptions = ConversionLogic.currencyOptions;
    private ObservableList<String> lengthOptions = ConversionLogic.lengthOptions;
    private ObservableList<String> volumeOptions = ConversionLogic.volumeOptions;
    private ObservableList<String> weightOptions = ConversionLogic.weightOptions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conversionType.getItems().clear();
        conversionType.getItems().addAll("Currency", "Length", "Volume", "Weight");
        try {
            inputText.setText(Constant.saved.getJSONObject("conversionInput").getString("inputText"));
            resultText.setText(Constant.saved.getJSONObject("conversionInput").getString("resultText"));
            initialUnitText.setText(Constant.saved.getJSONObject("conversionInput").getString("initialUnitText"));
            finalUnitText.setText(Constant.saved.getJSONObject("conversionInput").getString("finalUnitText"));
        } catch (JSONException e) {
            resultText.setText("Save error");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Constant.stage.setScene(Constant.mainScene);
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
    private void handleConversionSelect(ActionEvent event) {
        String type = conversionType.getValue().toString();
        finalUnits.setDisable(true);
        initialUnits.setDisable(false);
        if (type.equals("Currency")) {
            initialUnits.getItems().setAll(currencyOptions);
        } else if (type.equals("Length")) {
            initialUnits.getItems().setAll(lengthOptions);
        } else if (type.equals("Volume")) {
            initialUnits.getItems().setAll(volumeOptions);
        } else if (type.equals("Weight")) {
            initialUnits.getItems().setAll(weightOptions);
        }
    }

    @FXML
    private void handleInitialUnits(ActionEvent event) {
        String units = initialUnits.getValue().toString();
        inputText.setPromptText("Enter " + ConversionLogic.getAbbreviation(units));
        finalUnits.setDisable(false);
        finalUnits.getItems().setAll(ConversionLogic.getFinalUnitList(units));
        initialUnitText.setText(ConversionLogic.getAbbreviation(units));
    }

    @FXML
    private void handleFinalUnits(ActionEvent event) {
        resultText.setPromptText("Press Enter");
        String unitFinal = finalUnits.getValue().toString();
        finalUnitText.setText(ConversionLogic.getAbbreviation(unitFinal));
    }

    @FXML
    private void handleKeyPress(KeyEvent key) {
        String valid = ".0123456789";
        if (!initialUnits.getSelectionModel().isEmpty() && valid.contains(key.getText())) {
            inputText.appendText(key.getText());
        }
        if (!inputText.getText().toString().equals("") && key.getCode().equals(KeyCode.BACK_SPACE)) {
            inputText.setText(inputText.getText().substring(0, inputText.getText().length() - 1));
        } else if (!inputText.toString().equals("") && !initialUnits.getSelectionModel().isEmpty()
                && !finalUnits.getSelectionModel().isEmpty() && key.getCode().equals(KeyCode.ENTER)) {
            String initial = initialUnits.getValue().toString();
            String end = finalUnits.getValue().toString();
            resultText.setText(ConversionLogic.convert(initial, end, inputText.getText()));
            updateJSON();
        }
    }

    private void updateJSON() {
        try {
            Constant.saved.getJSONObject("conversionInput").put("resultText", resultText.getText());
            Constant.saved.getJSONObject("conversionInput").put("initialUnitText", initialUnitText.getText());
            Constant.saved.getJSONObject("conversionInput").put("finalUnitText", finalUnitText.getText());
            Constant.saved.getJSONObject("conversionInput").put("inputText", inputText.getText());
        } catch (JSONException e) {
        }
    }

}
