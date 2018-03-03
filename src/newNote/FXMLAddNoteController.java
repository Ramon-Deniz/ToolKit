package newNote;

import ToolKitConstant.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    @FXML
    private Text newNoteNotifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newNoteNotifier.setVisible(false);
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
        if (title.getText().length() > 0 && !Constant.noteTitles.contains(title.getText())) {
            try {
                String currentTitle = title.getText();

                //Get current size of titles
                int size = Constant.noteTitles.size();

                Constant.noteTitles.add(currentTitle);
                Constant.noteContent.put(currentTitle, message.getText());
                Constant.saved.getJSONObject("notes").put(currentTitle, message.getText());
                Constant.note.updateList();

                //Display temporary message if new note is added
                if (Constant.noteTitles.size() > size) {
                    newNoteNotifier.setVisible(true);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            newNoteNotifier.setVisible(false);
                        }
                    }, 2000);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Constant.displayError("Unable to add note");
            }
        }
    }

}
