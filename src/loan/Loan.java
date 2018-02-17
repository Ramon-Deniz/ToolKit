package loan;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ramon
 */
public class Loan {

    public Scene scene;
    public static Parent root;

    public Loan() throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLLoan.fxml"));
        scene = new Scene(root);
    }
}
