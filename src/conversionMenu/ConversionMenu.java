package conversionMenu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class ConversionMenu {
    
    public Scene scene;
    public static Parent root;
    
    public ConversionMenu() throws IOException{
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        scene = new Scene(root);
    }
}
