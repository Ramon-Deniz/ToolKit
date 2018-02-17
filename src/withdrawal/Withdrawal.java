package withdrawal;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class Withdrawal {

    public Scene scene;
    public static Parent root;

    public Withdrawal() throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLWithdrawal.fxml"));
        scene = new Scene(root);
    }
}
