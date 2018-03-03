package note;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class Note {

    private FXMLLoader loader;
    public Scene scene;
    public static Parent root;

    public Note() throws IOException {
        loader = new FXMLLoader(getClass().getResource("FXMLNote.fxml"));
        root = loader.load();
        scene = new Scene(root);
    }

    /**
     * Updates the options displayed in the drop down menu
     */
    public void updateList() {
        loader.<FXMLNoteController>getController().updateList();
    }

}
