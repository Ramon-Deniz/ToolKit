package Finance;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class Finance {
    public Scene scene;
    public static Parent root;
    
    public Finance() throws IOException{
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        scene = new Scene(root);
    }
}
