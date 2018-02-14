package ToolKitConstant;

import finance.Finance;
import calculator.Calculator;
import conversion.Conversion;
import fourK.FourK;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loan.Loan;
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
    public static String apiData;

    public Constant(Stage stage, Scene scene) throws Exception {
        Constant.stage = stage;
        
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
