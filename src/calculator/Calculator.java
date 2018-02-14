package calculator;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class Calculator {

    public static Scene scene;
    public static Parent root;

    public Calculator() throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        scene = new Scene(root);
    }

}

