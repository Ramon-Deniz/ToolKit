package conversionMenu;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    private TextArea text = new TextArea();
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
        text.setPromptText("Enter " + units);
        finalUnits.setDisable(false);
        finalUnits.getItems().setAll(ConversionLogic.getFinalUnitList(units));
        initialUnitText.setText(ConversionLogic.getAbbreviation(units));
    }

    @FXML
    void handleFinalUnits(ActionEvent event) {
        String unitInitial = initialUnits.getValue().toString();
        String unitFinal = finalUnits.getValue().toString();
        resultText.setPromptText("Press Enter");
        if (!text.getText().equals("")) {
            finalUnitText.setText(ConversionLogic.getAbbreviation(unitFinal));
            resultText.setText(ConversionLogic.convert(unitInitial, unitFinal, text.getText()));
        }
    }

}
