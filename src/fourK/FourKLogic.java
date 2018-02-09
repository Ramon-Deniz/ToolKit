package fourK;

/**
 *
 * @author ramon
 */
public class FourKLogic {

    public static String calculate() {
        String result = "";
        try {
            double contribute = getNumber(FXMLFourKController.contribute.getText()) / 100.0;
            double age = getAge(FXMLFourKController.currentAge.getText());
            double salary = getNumber(FXMLFourKController.annualSalary.getText());
            double retire = getAge(FXMLFourKController.retirementAge.getText());
            double salaryIncrease = getNumber(FXMLFourKController.salaryIncrease.getText()) / 100.0;
            double balance = getNumber(FXMLFourKController.currentBalance.getText());
            double match = getNumber(FXMLFourKController.employerMatch.getText()) / 100.0;
            double inflation = getNumber(FXMLFourKController.inflation.getText()) / 100.0;
            double matchEnd = getNumber(FXMLFourKController.emplyerMatchEnds.getText()) / 100.0;
            double rateOfReturn = getNumber(FXMLFourKController.rateOfReturn.getText()) / 100.0;
            retire-=age;
            if(retire<0.0){
                return "Invalid Input";
            }
            matchEnd*=match;
            rateOfReturn-=inflation;
            result+=calculate(contribute,retire,salary,salaryIncrease,balance,matchEnd,rateOfReturn);
        } catch (Exception e) {
            return "Invalid Input";
        }
        return result;
    }

    private static double getNumber(String input) throws Exception {
        double number = Double.parseDouble(input);
        if (number < 0.0) {
            throw new Exception();
        }
        return number;
    }
    
    private static int getAge(String input) throws Exception{
        int number = Integer.parseInt(input);
        if(number<0){
            throw new Exception();
        }
        return number;
    }
    
    private static double calculate(double contribute, double years, double salary, double salaryIncrease,
            double balance, double match, double rate){
        
        return 0.0;
    }
}
