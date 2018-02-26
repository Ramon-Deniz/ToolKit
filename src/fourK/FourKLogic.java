package fourK;

import java.text.DecimalFormat;

/**
 *
 * @author ramon
 */
public class FourKLogic {

    /**
     * Calculates the future balance of a 401K plan and returns it as a String.
     *
     * @param contributeInput
     * @param currentAge
     * @param annualSalary
     * @param retirementAge
     * @param salaryIncreaseInput
     * @param currentBalance
     * @param employerMatch
     * @param inflationInput
     * @param employerMatchEnds
     * @param rateOfReturnInput
     * @return
     */
    public static String calculate(String contributeInput, String currentAge, String annualSalary, String retirementAge,
            String salaryIncreaseInput, String currentBalance, String employerMatch, String inflationInput,
            String employerMatchEnds, String rateOfReturnInput) {
        String result = "";
        try {
            int age = getAge(currentAge);
            int retire = getAge(retirementAge);
            double contribute = getNumber(contributeInput) / 100;
            double salary = getNumber(annualSalary);
            double salaryIncrease = getNumber(salaryIncreaseInput) / 100;
            double balance = getNumber(currentBalance);
            double match = getNumber(employerMatch) / 100;
            double inflation = getNumber(inflationInput) / 100;
            double matchEnd = getNumber(employerMatchEnds) / 100;
            double rateOfReturn = getNumber(rateOfReturnInput) / 100;
            retire -= age;
            if (retire < 0.0) {
                return "Invalid Input";
            }
            matchEnd *= match;
            contribute += matchEnd;
            result += calculate(contribute, retire, salary, salaryIncrease, balance, rateOfReturn, inflation);
            DecimalFormat pattern = new DecimalFormat("###,###,###.###");
            result = pattern.format(Double.parseDouble(result));
        } catch (Exception e) {
            return "Invalid Input";
        }
        return result;
    }

    /**
     * Returns a double if the String parameter is valid.
     *
     * @param input
     * @return
     * @throws Exception
     */
    private static double getNumber(String input) throws Exception {
        if (input.length() == 0) {
            return 0.0;
        }
        double number = Double.parseDouble(input);
        if (number < 0.0) {
            System.out.println("getNumber");
            throw new Exception();
        }
        return number;
    }

    /**
     * Returns an int if the String parameter is valid.
     *
     * @param input
     * @return
     * @throws Exception
     */
    private static int getAge(String input) throws Exception {
        int number = Integer.parseInt(input);
        if (number < 0) {
            System.out.println("getAge");
            throw new Exception();
        }
        return number;
    }

    /**
     * Calculates the future balance of a 401K plan and returns it as an int.
     *
     * @param contribute
     * @param years
     * @param salary
     * @param salaryIncrease
     * @param balance
     * @param rate
     * @param inflation
     * @return
     */
    private static double calculate(double contribute, int years, double salary, double salaryIncrease,
            double balance, double rate, double inflation) {
        if (years == 0) {
            return balance;
        }
        balance += (salary * contribute);
        balance *= (1.0 + rate) * (1.0 - inflation);
        salary *= (1.0 + salaryIncrease);
        return calculate(contribute, years - 1, salary, salaryIncrease, balance, rate, inflation);
    }
}
