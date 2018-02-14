package withdrawal;

import java.text.DecimalFormat;

/**
 *
 * @author ramon
 */
public class WithdrawalLogic {
    
    //
    //Withdrawals and loans work the same
    //
    
    public static String calculate(String initialAmount, String monthsAmount, String rateOfReturnAmount) {
        String result = "Invalid";
        try {
            double amount = getNumber(initialAmount);
            int months = getMonths(monthsAmount);
            double rateOfReturn = getNumber(rateOfReturnAmount) / 100 + 1;
            rateOfReturn = Math.pow(10, Math.log10(rateOfReturn) / 12.0);
            result = "" + calculate(amount, months, rateOfReturn, 0) / months;
        } catch (Exception e) {
            return result;
        }
        DecimalFormat pattern = new DecimalFormat("###,###,###.##");
        return pattern.format(Double.parseDouble(result));
    }

    private static Double calculate(double amount, int months, double rateOfReturn, double sum) {
        if (months == 0) {
            return sum;
        }
        amount *= (rateOfReturn);
        double monthlyWithdraw = (amount / months);
        return calculate(amount - monthlyWithdraw, months - 1, rateOfReturn, sum + monthlyWithdraw);
    }

    private static Double getNumber(String number) throws Exception {
        double temp = Double.parseDouble(number);
        if (temp >= 0.0) {
            return temp;
        }
        throw new Exception();
    }

    private static int getMonths(String number) throws Exception {
        int temp = Integer.parseInt(number);
        if (temp >= 0) {
            return temp;
        }
        throw new Exception();
    }

}
