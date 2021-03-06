package conversion;

import ToolKitConstant.Constant;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONException;

/**
 *
 * @author ramon
 */
public class ConversionLogic {

    //Names used for drop down menu
    public static ObservableList<String> currencyOptions = FXCollections.observableArrayList("USD",
            "EUR", "GBP", "AUD", "MXN", "CAD", "CNY", "JPY", "THB", "CHF", "KRW", "ZAR", "RUB");
    public static ObservableList<String> lengthOptions = FXCollections.observableArrayList("Feet", "Inches",
            "Meters", "Centimeters", "Millimeters", "Yards", "Miles");
    public static ObservableList<String> volumeOptions = FXCollections.observableArrayList("Gallons",
            "Liters", "Pints", "Cups", "Milliliters", "Fluid Ounces", "Quarts");
    public static ObservableList<String> weightOptions = FXCollections.observableArrayList("Pounds",
            "Kilograms", "Grams", "Milligrams", "Ounces", "Tons");

    private static final Map<String, Double> LENGTH = getLengthRates();
    private static final Map<String, Double> VOLUME = getVolumeRates();
    private static final Map<String, Double> WEIGHT = getWeightRates();

    private static final Map<String, String> ABBREVIATIONS = getAbbreviations();

    /**
     * Returns an ObservableList of type String that excludes one element from
     * original ObservableList. The element removed is based on which unit was
     * picked in the first drop down menu.
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
     * Converts from one unit to another and returns that value as a String
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
        double finalUnit = 0;
        double initialUnit = 0;
        String result;
        if (currencyOptions.contains(unitInitial) && currencyOptions.contains(unitFinal)) {
            try {
                finalUnit = getCurrencyRate(unitFinal);
                initialUnit = getCurrencyRate(unitInitial);
            } catch (Exception e) {
                return "Error with API";
            }
        } else if (lengthOptions.contains(unitInitial) && lengthOptions.contains(unitFinal)) {
            finalUnit = LENGTH.get(unitFinal);
            initialUnit = LENGTH.get(unitInitial);
        } else if (volumeOptions.contains(unitInitial) && volumeOptions.contains(unitFinal)) {
            finalUnit = VOLUME.get(unitFinal);
            initialUnit = VOLUME.get(unitInitial);
        } else if (weightOptions.contains(unitInitial) && weightOptions.contains(unitFinal)) {
            finalUnit = WEIGHT.get(unitFinal);
            initialUnit = WEIGHT.get(unitInitial);
        }
        finalUnit /= initialUnit;
        finalUnit *= Double.parseDouble(number);
        result = "" + finalUnit;
        DecimalFormat pattern = new DecimalFormat("###,###,###.####");
        return pattern.format(Double.parseDouble(result));
    }

    /**
     * Returns the current rate of a specific currency
     *
     * @param currency
     * @return
     * @throws JSONException
     */
    private static double getCurrencyRate(String currency) throws JSONException {
        if (currency.equals("EUR")) {
            return 1;
        }
        return Constant.saved.getJSONObject("rates").getDouble(currency);
    }

    /**
     * Returns an abbreviation of the String if the unit exceeds a certain
     * length.
     *
     * @param units
     * @return
     */
    public static String getAbbreviation(String units) {
        if (ABBREVIATIONS.containsKey(units)) {
            return ABBREVIATIONS.get(units);
        }
        return units;
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

    private static Map<String, String> getAbbreviations() {
        Map<String, String> abr = new HashMap<>();
        abr.put("Centimeters", "cm");
        abr.put("Fluid Ounces", "fl oz");
        abr.put("Millimeters", "mm");
        abr.put("Milliliters", "mL");
        abr.put("Kilograms", "Kg");
        return abr;
    }
}
