package ToolKitConstant;

import calculator.Calculator;
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
    public static String answers;
    public static Scene calculatorScene;

    public Constant(Stage stage, Scene scene) throws Exception {
        Constant.stage = stage;
        Calculator calc = new Calculator();
        calculatorScene = calc.calcScene;
        mainScene = scene;
        expression = "";
        answers = "";
    }
    
    public static void updateAnswers(){
        if ( !answers.contains(expression+" ")) {
            answers += Constant.expression + " \n";
        }
    }
}
