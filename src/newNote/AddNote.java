package newNote;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class AddNote {

    public Scene scene;
    public static Parent root;

    public AddNote() throws IOException {

        root = FXMLLoader.load(getClass().getResource("FXMLAddNote.fxml"));
        scene = new Scene(root);
    }
}
