package withdrawal;

import java.text.DecimalFormat;

/**
 *
 * @author ramon
 */
public class WithdrawalLogic {

    //Withdrawals and loans use the same forumla
    /**
     * Calculates the monthly payment/withdrawal and returns it as a String.
     *
     * @param initialAmount
     * @param monthsAmount
     * @param rateOfReturnAmount
     * @return
     */
    public static String calculate(String initialAmount, String monthsAmount, String rateOfReturnAmount) {
        String result = "Invalid";
        try {
            double amount = getNumber(initialAmount);
            int months = getMonths(monthsAmount);
            double rateOfReturn = getNumber(rateOfReturnAmount) / 100;
            rateOfReturn /= 12.0;
            result = "" + calculate(amount, months, rateOfReturn);
        } catch (Exception e) {
            return result;
        }
        DecimalFormat pattern = new DecimalFormat("###,###,###.##");
        return pattern.format(Double.parseDouble(result));
    }

    /**
     * Calculates the monthly payment/withdrawal and returns it as an int.
     *
     * @param amount
     * @param months
     * @param rateOfReturn
     * @return
     */
    private static Double calculate(double amount, int months, double rateOfReturn) {
        rateOfReturn += rateOfReturn / (Math.pow(rateOfReturn + 1, months) - 1.0);
        return amount * rateOfReturn;
    }

    /**
     * Returns a double if the String parameter is valid.
     *
     * @param number
     * @return
     * @throws Exception
     */
    private static Double getNumber(String number) throws Exception {
        double temp = Double.parseDouble(number);
        if (temp >= 0.0) {
            return temp;
        }
        throw new Exception();
    }

    /**
     * Returns an int if the String parameter is valid.
     *
     * @param number
     * @return
     * @throws Exception
     */
    private static int getMonths(String number) throws Exception {
        int temp = Integer.parseInt(number);
        if (temp >= 0) {
            return temp;
        }
        throw new Exception();
    }

}
