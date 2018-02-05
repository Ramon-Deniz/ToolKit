package calculator;

import java.util.List;
import ToolKitConstant.InvalidExpressionException;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class CalculatorLogic {

    //Valid input
    private static final String NUMBERS = "0123456789.";
    private static final String OPERANDS = "(^+-*/%!)";

    /**
     * Solves a valid expression and returns it as a string.
     *
     * @param stringExpression
     * @return String
     */
    public static String solve(String stringExpression) {
        stringExpression = stringExpression.replaceAll("\\s", "");
        stringExpression = stringExpression.replace("x", "*");
        try {
            List<String> expression = stringToList(stringExpression);
            format(expression);
            solve(expression);
            stringExpression = expression.get(0);
        } catch (InvalidExpressionException e) {
            stringExpression = "";
        }
        return stringExpression;
    }

    private static void format(List<String> expression) {
        if (expression.get(0).equals("-")) {
            expression.remove(0);
            expression.set(0, "-" + expression.get(0));
        }
        for (int i = 0; i < expression.size() - 1; i++) {
            if (NUMBERS.indexOf(expression.get(i).charAt(0)) != -1 && expression.get(i + 1).contains("(")) {
                expression.add(i + 1, "*");
            } else if (NUMBERS.indexOf(expression.get(i).charAt(0)) != -1 && expression.get(i + 1).equals("%")) {
                double num = Double.parseDouble(expression.get(i)) / 100;
                expression.set(i, "" + num);
                expression.remove(i + 1);
            } else if (NUMBERS.indexOf(expression.get(i + 1).charAt(0)) != -1 && expression.get(i).contains("-")
                    && i - 1 >= 0 && NUMBERS.indexOf(expression.get(i - 1).charAt(0)) == -1) {
                expression.remove(i);
                expression.set(i, "-" + expression.get(i));
            }
        }
    }

    private static List<String> solve(List<String> expression) throws InvalidExpressionException {
        solvePar(expression);
        solveFactorial(expression);
        solveOperand(expression, "^");
        
        int multiplyInstance = firstInstance(expression, "*", 0);
        int divideInstance = firstInstance(expression, "/", 0);
        if (multiplyInstance < divideInstance) {
            solveOperand(expression, "*");
            solveOperand(expression, "/");
        } else {
            solveOperand(expression, "/");
            solveOperand(expression, "*");
        }
        
        solveOperand(expression, "+");
        solveOperand(expression, "-");
        return expression;
    }

    private static List<String> stringToList(String expression) {
        List<String> expressionList = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (NUMBERS.indexOf(expression.charAt(i)) != -1) {
                expressionList.add("");
                while (i < expression.length() && NUMBERS.indexOf(expression.charAt(i)) != -1) {
                    expressionList.set(index, expressionList.get(index) + expression.charAt(i++));
                }
                index++;
            }
            if (i < expression.length() && OPERANDS.indexOf(expression.charAt(i)) != -1) {
                expressionList.add("" + expression.charAt(i));
                index++;
            }

        }
        return expressionList;
    }

    private static void solvePar(List<String> expression) throws InvalidExpressionException {
        int index = firstInstance(expression, "(", 0);
        if (index == -1) {
            return;
        }
        int indexRight = firstInstance(expression, ")", index);
        if (indexRight == -1) {
            throw new InvalidExpressionException();
        }
        List<String> subExpression = new ArrayList<>(expression.subList(index + 1, indexRight));
        solve(subExpression);
        expression.set(index, subExpression.get(0));
        remove(expression, index + 1, indexRight);
        solve(expression);
    }

    private static void solveFactorial(List<String> expression) throws InvalidExpressionException {
        int index = firstInstance(expression, "!", 0);
        if (index == -1) {
            return;
        }
        double num;
        try {
            num = Double.parseDouble(expression.get(index - 1));
            if ((int) num != num) {
                throw new InvalidExpressionException();
            }
            double num2 = 1;
            for (int i = 2; i <= num; i++) {
                num2 *= i;
            }
            num = num2;
        } catch (Exception e) {
            throw new InvalidExpressionException();
        }
        expression.set(index - 1, "" + num);
        expression.remove(index);
        solve(expression);
    }

    private static void solveOperand(List<String> expression, String operand) throws InvalidExpressionException {
        int index = firstInstance(expression, operand, 0);
        if (index == -1) {
            return;
        }
        double firstNum, secondNum;
        try {
            firstNum = Double.parseDouble(expression.get(index - 1));
            secondNum = Double.parseDouble(expression.get(index + 1));
        } catch (Exception e) {
            throw new InvalidExpressionException();
        }
        if (operand.equals("^")) {
            firstNum = Math.pow(firstNum, secondNum);
        } else if (operand.equals("*")) {
            firstNum *= secondNum;
        } else if (operand.equals("/")) {
            if (secondNum == 0.0) {
                throw new InvalidExpressionException();
            }
            firstNum /= secondNum;
        } else if (operand.equals("+")) {
            firstNum += secondNum;
        } else if (operand.equals("-")) {
            firstNum -= secondNum;
        }
        expression.set(index - 1, "" + firstNum);
        remove(expression, index, index + 1);
        solve(expression);
    }

    private static void remove(List<String> expression, int fromIndex, int toIndex) {
        for (int i = toIndex; i >= fromIndex; i--) {
            expression.remove(i);
        }
    }

    private static int firstInstance(List<String> expression, String search, int index) {
        for (int i = index; i < expression.size(); i++) {
            if (expression.get(i).equals(search)) {
                return i;
            }
        }
        return -1;
    }

}
