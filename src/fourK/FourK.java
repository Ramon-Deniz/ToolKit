package fourK;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class FourK {

    public Scene scene;
    public static Parent root;

    public FourK() throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLFourK.fxml"));
        scene = new Scene(root);
    }
}
