package conversionMenu;

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
            "EUR","MXN","THB");
    public static ObservableList<String> lengthOptions = FXCollections.observableArrayList("Feet","Inches",
            "Meters","Centimeters");
    public static ObservableList<String> volumeOptions = FXCollections.observableArrayList("Gallons",
            "Liters","Pints","Cups");
    public static ObservableList<String> weightOptions = FXCollections.observableArrayList("Pounds",
            "Kilograms","Grams","Milligrams");
    
    public static ObservableList<String> getFinalUnitList(String unitUsed){
        ObservableList<String> updatedFinalList = FXCollections.observableArrayList();
        if(currencyOptions.contains(unitUsed)){
            updatedFinalList.addAll(currencyOptions);
        }
        else if(lengthOptions.contains(unitUsed)){
            updatedFinalList.addAll(lengthOptions);
        }
        else if(volumeOptions.contains(unitUsed)){
            updatedFinalList.addAll(volumeOptions);
        }
        else if(weightOptions.contains(unitUsed)){
            updatedFinalList.addAll(weightOptions);
        }
        updatedFinalList.remove(unitUsed);
        return updatedFinalList;
    }
    
    public static String convert(String unitInitial, String unitFinal, String number){
        String result = "";
        if(currencyOptions.contains(unitInitial) && currencyOptions.contains(unitFinal)){
            try{
                result=convertCurrency(unitInitial,unitFinal,number);
            }
            catch(Exception e){
                result="";
            }
        }
        DecimalFormat pattern = new DecimalFormat("###,###,###.###");
        result = pattern.format(Double.parseDouble(result));
        return result;
    }
    
    private static String convertCurrency(String currency, String convertTo, String amount) throws Exception{
        double initialRate = getCurrencyRate(Constant.apiData, currency);
        double finalRate = getCurrencyRate(Constant.apiData, convertTo);
        double money = Double.parseDouble(amount);
        finalRate/=initialRate;
        money*=finalRate;
        amount= ""+money;
        return amount;
    }
    
    private static double getCurrencyRate(String apiData, String currency) throws Exception{
        if(currency.equals("EUR")){
            return 1;
        }
        String valid = ".0123456789";
        double rate=0;
        int start = apiData.indexOf(currency)+5;
        for(int i=start;i<apiData.length();i++){
            if(valid.indexOf(apiData.charAt(i))==-1){
                return Double.parseDouble(apiData.substring(start,i));
            }
        }
        throw new Exception();
    }
    
    public static String getAbbreviation(String units){
        if(units.equals("Centimeters")){
            return "cm";
        }
        return units;
    }
}
