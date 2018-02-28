package note;

import ToolKitConstant.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author ramon
 */
public class FXMLNoteController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button drag;
    @FXML
    private Button newNote;
    @FXML
    private Button save;
    @FXML
    private Button delete;
    @FXML
    private ComboBox titles = new ComboBox();
    @FXML
    private TextArea messages = new TextArea();
    @FXML
    private TextArea titleTextArea = new TextArea();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titles.getItems().clear();
        titles.getItems().addAll(updateList(titles));
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
    private void handleNoteList(ActionEvent event) {
        if (!titles.getSelectionModel().isEmpty()) {
            messages.setText(Constant.noteContent.get(titles.getValue().toString()));
            titleTextArea.setText(titles.getValue().toString());
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            //Remove note from list of notes
            if(titles.getSelectionModel().isEmpty()){
                return;
            }
            String currentNote = ""+titles.getValue().toString();
            Constant.noteTitles.remove(currentNote);
            Constant.noteContent.remove(currentNote);
            Constant.saved.getJSONObject("notes").remove(currentNote);
            titles.getItems().clear();

            //Update list of notes with new addition of note
            String currentNoteTitle = titleTextArea.getText();
            Constant.noteTitles.add(currentNoteTitle);
            Constant.noteContent.put(currentNoteTitle, messages.getText());
            Constant.saved.getJSONObject("notes").put(currentNoteTitle, messages.getText());
            titles.getItems().addAll(updateList(titles));
        } catch (JSONException e) {
            Constant.displayError("Unable to save note changes");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            if(titles.getSelectionModel().isEmpty()){
                return;
            }
            String currentNote = titles.getValue().toString();
            Constant.noteTitles.remove(currentNote);
            Constant.noteContent.remove(currentNote);
            Constant.saved.getJSONObject("notes").remove(currentNote);
            titles.getItems().clear();
            titles.getItems().addAll(updateList(titles));
            titleTextArea.clear();
            messages.clear();
        } catch (JSONException e) {
            Constant.displayError("Unable to delete note");
        }
    }

    @FXML
    private void handleNewNote(ActionEvent event) {
        Constant.stage.setScene(Constant.addNoteScene);
    }

    private static ObservableList<String> updateList(ComboBox titles) {
        ObservableList<String> noteTitles = FXCollections.observableArrayList(Constant.noteTitles);
        return noteTitles;
    }

}
