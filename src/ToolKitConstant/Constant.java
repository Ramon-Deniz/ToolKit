package ToolKitConstant;

import calculator.Calculator;
import conversionMenu.ConversionMenu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ramon
 */
public class Constant {

    public static Stage stage;
    public static Scene mainScene;
    public static String expression;
    public static List<String> answers;
    public static Scene calculatorScene;
    public static Scene conversionMenuScene;
    public static String apiData;

    public Constant(Stage stage, Scene scene) throws Exception {
        Constant.stage = stage;
        Calculator calc = new Calculator();
        ConversionMenu cMenu = new ConversionMenu();
        conversionMenuScene = cMenu.scene;
        calculatorScene = calc.calcScene;
        mainScene = scene;
        expression = "";
        answers = new ArrayList<>();
        answers.add("");
        answers.add("");
        answers.add("");
        answers.add("");
        setAPI();
    }

    public static void updateAnswers() {
        if (!answers.contains(expression) && !expression.equals("")) {
            answers.remove(0);
            answers.add(expression);
        }
    }

    public static String getAnswers() {
        String answer = "";
        for (int i = 0; i < answers.size(); i++) {
            answer += answers.get(i) + "\n";
        }
        return answer;
    }

    private static void setAPI() throws Exception{
        URL url = new URL("https://api.fixer.io/latest");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(is));
        apiData = "";
        while (read.ready()) {
            apiData += read.readLine();
        }
    }
}
