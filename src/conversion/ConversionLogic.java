package conversion;

import ToolKitConstant.Constant;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ramon
 */
public class ConversionLogic {

    public static ObservableList<String> currencyOptions = FXCollections.observableArrayList("USD",
            "EUR", "MXN", "THB", "KRW");
    public static ObservableList<String> lengthOptions = FXCollections.observableArrayList("Feet", "Inches",
            "Meters", "Centimeters", "Yards","Miles");
    public static ObservableList<String> volumeOptions = FXCollections.observableArrayList("Gallons",
            "Liters", "Pints", "Cups", "Milliliters","Fluid Ounces","Quarts");
    public static ObservableList<String> weightOptions = FXCollections.observableArrayList("Pounds",
            "Kilograms", "Grams", "Milligrams", "Ounces","Tons");

    //Conversion rates
    //Values based on the ratio to the base unit
    //Base unit for length is meters
    private static String[] lengthName = {"Feet", "Inches", "Meters", "Centimeters", "Millimeters", "Yards","Miles"};
    private static double[] lengthValue = {3.28084, 39.3701, 1, 100, 1000, 1.09361,0.000621371};

    //Base unit for volume is liters
    private static String[] volumeName = {"Gallons", "Liters", "Pints", "Cups", "Milliliters","Fluid Ounces","Quarts"};
    private static double[] volumeValue = {0.264172, 1, 2.11338, 4.22675, 1000,33.814,1.05669};

    //Base unit for weight is grams
    private static String[] weightName = {"Pounds", "Kilograms", "Grams", "Milligrams", "Ounces","Tons"};
    private static double[] weightValue = {0.00220462, 0.001, 1, 1000, 0.035274,0.00000110231};

    /**
     * Returns an ObservableList of type string that excludes one element from
     * original ObservableList
     *
     * @param unitUsed
     * @return
     */
    public static ObservableList<String> getFinalUnitList(String unitUsed) {
        ObservableList<String> updatedFinalList = FXCollections.observableArrayList();
        if (currencyOptions.contains(unitUsed)) {
            updatedFinalList.addAll(currencyOptions);
        } else if (lengthOptions.contains(unitUsed)) {
            updatedFinalList.addAll(lengthOptions);
        } else if (volumeOptions.contains(unitUsed)) {
            updatedFinalList.addAll(volumeOptions);
        } else if (weightOptions.contains(unitUsed)) {
            updatedFinalList.addAll(weightOptions);
        }
        updatedFinalList.remove(unitUsed);
        return updatedFinalList;
    }

    /**
     * Converts from one unit to another and returns that value
     *
     * @param unitInitial
     * @param unitFinal
     * @param number
     * @return
     */
    public static String convert(String unitInitial, String unitFinal, String number) {
        try{
            double temp = Double.parseDouble(number);
        }
        catch(Exception e){
            return "";
        }
        String result = "";
        if (currencyOptions.contains(unitInitial) && currencyOptions.contains(unitFinal)) {
            try {
                result = convertCurrency(unitInitial, unitFinal, number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (lengthOptions.contains(unitInitial) && lengthOptions.contains(unitFinal)) {
            double finalUnit = lengthValue[getIndex(lengthName,unitFinal)];
            double initialUnit = lengthValue[getIndex(lengthName,unitInitial)];
            finalUnit/=initialUnit;
            finalUnit*=Double.parseDouble(number);
            result = ""+finalUnit;
        }
        else if (volumeOptions.contains(unitInitial) && volumeOptions.contains(unitFinal)) {
            double finalUnit = volumeValue[getIndex(volumeName,unitFinal)];
            double initialUnit = volumeValue[getIndex(volumeName,unitInitial)];
            finalUnit/=initialUnit;
            finalUnit*=Double.parseDouble(number);
            result = ""+finalUnit;
        }
        else if (weightOptions.contains(unitInitial) && weightOptions.contains(unitFinal)) {
            double finalUnit = weightValue[getIndex(weightName,unitFinal)];
            double initialUnit = weightValue[getIndex(weightName,unitInitial)];
            finalUnit/=initialUnit;
            finalUnit*=Double.parseDouble(number);
            result = ""+finalUnit;
        }
        DecimalFormat pattern = new DecimalFormat("###,###,###.###");
        result = pattern.format(Double.parseDouble(result));
        return result;
    }

    /**
     * Converts from one currency to another and returns that value
     *
     * @param currency
     * @param convertTo
     * @param amount
     * @return
     * @throws Exception
     */
    private static String convertCurrency(String currency, String convertTo, String amount) throws Exception {
        double initialRate = getCurrencyRate(Constant.apiData, currency);
        double finalRate = getCurrencyRate(Constant.apiData, convertTo);
        double money = Double.parseDouble(amount);
        finalRate /= initialRate;
        money *= finalRate;
        amount = "" + money;
        return amount;
    }

    /**
     * Returns the current rate of a specific currency
     *
     * @param apiData
     * @param currency
     * @return
     * @throws Exception
     */
    private static double getCurrencyRate(String apiData, String currency) throws Exception {
        if (currency.equals("EUR")) {
            return 1;
        }
        String valid = ".0123456789";
        double rate = 0;
        int start = apiData.indexOf(currency) + 5;
        for (int i = start; i < apiData.length(); i++) {
            if (valid.indexOf(apiData.charAt(i)) == -1) {
                return Double.parseDouble(apiData.substring(start, i));
            }
        }
        throw new Exception();
    }

    /**
     * Returns an abbreviation of the string if the units is large enough
     *
     * @param units
     * @return
     */
    public static String getAbbreviation(String units) {
        if (units.equals("Centimeters")) {
            return "cm";
        }
        else if(units.equals("Fluid Ounces")){
            return "fl oz";
        }
        return units;
    }

    private static int getIndex(String[] units, String search) {
        for (int i = 0; i < units.length; i++) {
            if (units[i].equals(search)) {
                return i;
            }
        }
        return -1;
    }
}
