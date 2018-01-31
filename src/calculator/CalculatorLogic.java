package calculator;

import ToolKitConstant.Constant;

/**
 *
 * @author ramon
 */
public class CalculatorLogic {

    public static void solve(String expression) {

    }

    private static void solveParantheses(String expression) {
        int left = findLeft(expression);
        int right = findRight(expression);
        if(left>=right){
            expression="Error";
        }
        else if(right-left>1){
            expression = expression.substring(0,left)+expression.substring(right+1);
        }
    }

    private static int findLeft(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                return i;
            }
        }
        return -1;
    }

    private static int findRight(String expression) {
        for (int i = expression.length() - 1; i >= 0; i--) {
            if (expression.charAt(i) == ')') {
                return i;
            }
        }
        return -1;
    }

}
