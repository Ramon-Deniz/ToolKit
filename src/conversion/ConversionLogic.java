package conversion;

import ToolKitConstant.Constant;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ramon
 */
public class ConversionLogic {

    public static ObservableList<String> currencyOptions = FXCollections.observableArrayList("USD",
            "EUR", "MXN", "THB", "KRW","ZAR");
    public static ObservableList<String> lengthOptions = FXCollections.observableArrayList("Feet", "Inches",
            "Meters", "Centimeters", "Yards", "Miles");
    public static ObservableList<String> volumeOptions = FXCollections.observableArrayList("Gallons",
            "Liters", "Pints", "Cups", "Milliliters", "Fluid Ounces", "Quarts");
    public static ObservableList<String> weightOptions = FXCollections.observableArrayList("Pounds",
            "Kilograms", "Grams", "Milligrams", "Ounces", "Tons");

    private static final Map<String, Double> LENGTH = getLengthRates();
    private static final Map<String, Double> VOLUME = getVolumeRates();
    private static final Map<String, Double> WEIGHT = getWeightRates();

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
        try {
            double temp = Double.parseDouble(number);
        } catch (Exception e) {
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
            double finalUnit = LENGTH.get(unitFinal);
            double initialUnit = LENGTH.get(unitInitial);
            finalUnit /= initialUnit;
            finalUnit *= Double.parseDouble(number);
            result = "" + finalUnit;
        } else if (volumeOptions.contains(unitInitial) && volumeOptions.contains(unitFinal)) {
            double finalUnit = VOLUME.get(unitFinal);
            double initialUnit = VOLUME.get(unitInitial);
            finalUnit /= initialUnit;
            finalUnit *= Double.parseDouble(number);
            result = "" + finalUnit;
        } else if (weightOptions.contains(unitInitial) && weightOptions.contains(unitFinal)) {
            double finalUnit = WEIGHT.get(unitFinal);
            double initialUnit = WEIGHT.get(unitInitial);
            finalUnit /= initialUnit;
            finalUnit *= Double.parseDouble(number);
            result = "" + finalUnit;
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
        return "" + money;
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
        switch (units) {
            case "Centimeters":
                return "cm";
            case "Fluid Ounces":
                return "fl oz";
            case "Millimeters":
                return "mm";
            case "Milliliters":
                return "mL";
            default:
                break;
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

    //Base unit for length: Meters
    private static Map<String, Double> getLengthRates() {
        Map<String, Double> lengthRates = new HashMap<>();
        lengthRates.put("Feet", 3.28084);
        lengthRates.put("Inches", 39.3701);
        lengthRates.put("Meters", 1.0);
        lengthRates.put("Centimeters", 100.0);
        lengthRates.put("Millimeters", 1000.0);
        lengthRates.put("Yards", 1.09361);
        lengthRates.put("Miles", 0.000621371);
        return lengthRates;
    }

    //Base unit for volume: Liters
    private static Map<String, Double> getVolumeRates() {
        Map<String, Double> volumeRates = new HashMap<>();
        volumeRates.put("Gallons", 0.264172);
        volumeRates.put("Liters", 1.0);
        volumeRates.put("Pints", 2.11338);
        volumeRates.put("Cups", 4.22675);
        volumeRates.put("Milliliters", 1000.0);
        volumeRates.put("Fluid Ounces", 33.814);
        volumeRates.put("Quarts", 1.05669);
        return volumeRates;
    }

    //Base unit for weight: Grams
    private static Map<String, Double> getWeightRates() {
        Map<String, Double> weightRates = new HashMap<>();
        weightRates.put("Pounds", 0.00220462);
        weightRates.put("Kilograms", 0.001);
        weightRates.put("Grams", 1.0);
        weightRates.put("Milligrams", 1000.0);
        weightRates.put("Ounces", 0.035274);
        weightRates.put("Tons", 0.00000110231);
        return weightRates;
    }
}
