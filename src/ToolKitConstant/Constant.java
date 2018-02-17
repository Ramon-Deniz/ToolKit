package ToolKitConstant;

import finance.Finance;
import calculator.Calculator;
import conversion.Conversion;
import fourK.FourK;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import loan.Loan;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import withdrawal.Withdrawal;

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
    public static Scene conversionScene;
    public static Scene financeScene;
    public static Scene fourKScene;
    public static Scene withdrawScene;
    public static Scene loanScene;
    public static JSONObject apiData;
    public static JSONObject saved;

    public Constant(Stage stage, Scene scene) throws Exception {
        Constant.stage = stage;

        answers = new ArrayList<>();
        setAPI();
        setSaved();

        Calculator calc = new Calculator();
        Conversion conv = new Conversion();
        Finance fin = new Finance();
        FourK fourK = new FourK();
        Withdrawal withdraw = new Withdrawal();
        Loan loan = new Loan();

        conversionScene = conv.scene;
        calculatorScene = calc.scene;
        financeScene = fin.scene;
        fourKScene = fourK.scene;
        withdrawScene = withdraw.scene;
        loanScene = loan.scene;

        mainScene = scene;
        expression = "";
    }

    public static void updateAnswers() {
        if (!answers.contains(expression) && !expression.equals("")) {
            answers.remove(0);
            answers.add(expression);
            try {
                saved.getJSONObject("answers").put("answers1", answers.get(0));
                saved.getJSONObject("answers").put("answers2", answers.get(1));
                saved.getJSONObject("answers").put("answers3", answers.get(2));
                saved.getJSONObject("answers").put("answers4", answers.get(3));
            } catch (JSONException e) {
            }
        }
    }

    public static String getAnswers() {
        String answer = "";
        for (int i = 0; i < answers.size(); i++) {
            answer += answers.get(i) + "\n";
        }
        return answer;
    }

    private static void setAPI() throws Exception {
        URL url = new URL("https://api.fixer.io/latest");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        apiData = (JSONObject) new JSONTokener(new InputStreamReader(is, "UTF-8")).nextValue();
    }

    private static void setSaved() throws Exception {
        InputStream is = new FileInputStream(new File("src/ToolKitConstant/JSONSaved.txt"));
        saved = (JSONObject) new JSONTokener(new InputStreamReader(is, "UTF-8")).nextValue();
        answers.add(saved.getJSONObject("answers").getString("answers1"));
        answers.add(saved.getJSONObject("answers").getString("answers2"));
        answers.add(saved.getJSONObject("answers").getString("answers3"));
        answers.add(saved.getJSONObject("answers").getString("answers4"));
    }

    public static void save() {
        try {
            PrintStream output = new PrintStream(new File("src/ToolKitConstant/JSONSaved.txt"));
            output.print(saved);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Error saving to file",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
