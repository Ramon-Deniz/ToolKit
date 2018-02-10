package fourK;

/**
 *
 * @author ramon
 */
public class FourKLogic {

    public static String calculate(String contributeInput, String currentAge, String annualSalary, String retirementAge,
            String salaryIncreaseInput,String currentBalance, String employerMatch, String inflationInput,
            String employerMatchEnds, String rateOfReturnInput) {
        String result = "";
        try {
            double contribute = getNumber(contributeInput) / 100.0;
            int age = getAge(currentAge);
            double salary = getNumber(annualSalary);
            int retire = getAge(retirementAge);
            double salaryIncrease = getNumber(salaryIncreaseInput) / 100.0;
            double balance = getNumber(currentBalance);
            double match = getNumber(employerMatch) / 100.0;
            double inflation = getNumber(inflationInput) / 100.0;
            double matchEnd = getNumber(employerMatchEnds) / 100.0;
            double rateOfReturn = getNumber(rateOfReturnInput) / 100.0;
            retire -= age;
            if (retire < 0.0) {
                return "Invalid Input";
            }
            matchEnd *= match;
            contribute += matchEnd;
            result += calculate(contribute, retire, salary, salaryIncrease, balance, rateOfReturn)*Math.pow(1.0-inflation, retire);
        } catch (Exception e) {
            return "Invalid Input";
        }
        return result;
    }

    private static double getNumber(String input) throws Exception {
        if(input.length()==0){
            return 0.0;
        }
        double number = Double.parseDouble(input);
        if (number < 0.0) {
            System.out.println("getNumber");
            throw new Exception();
        }
        return number;
    }

    private static int getAge(String input) throws Exception {
        int number = Integer.parseInt(input);
        if (number < 0) {
            System.out.println("getAge");
            throw new Exception();
        }
        return number;
    }

    private static double calculate(double contribute, int years, double salary, double salaryIncrease,
            double balance, double rate) {
        if (years == 0) {
            return balance;
        }
        balance += (salary * contribute);
        balance *= (1.0 + rate);
        salary *= (1.0 + salaryIncrease);
        return calculate(contribute, years - 1, salary, salaryIncrease, balance, rate);
    }
}
