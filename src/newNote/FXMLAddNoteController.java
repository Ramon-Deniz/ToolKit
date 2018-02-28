package newNote;

import ToolKitConstant.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author ramon
 */
public class FXMLAddNoteController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button drag;
    @FXML
    private TextArea title = new TextArea();
    @FXML
    private TextArea message = new TextArea();
    @FXML
    private Button addNote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Constant.stage.setScene(Constant.noteScene);
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
    private void handleAddNote(ActionEvent event) {
        if (title.getText().length() > 0) {
            try {
                String currentTitle = title.getText();
                Constant.noteTitles.add(currentTitle);
                Constant.noteContent.put(currentTitle, message.getText());
                Constant.saved.getJSONObject("notes").put(currentTitle, message.getText());
            } catch (JSONException e) {
                Constant.displayError("Unable to add note");
            }
        }
    }

}
