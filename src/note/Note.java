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

    public Scene scene;
    public static Parent root;

    public Note() throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLNote.fxml"));
        scene = new Scene(root);
    }
}
