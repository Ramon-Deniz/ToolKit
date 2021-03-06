package ToolKitConstant;

import finance.Finance;
import calculator.Calculator;
import conversion.Conversion;
import fourK.FourK;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import loan.Loan;
import newNote.AddNote;
import note.Note;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import withdrawal.Withdrawal;

/**
 *
 * @author ramon
 */
public class Constant {

    //JSON File Location
    private final static String FILEPATH = "SavedInput/JSONSaved.json";

    public static Stage stage;
    public static Scene mainScene;
    public static Scene calculatorScene;
    public static Scene conversionScene;
    public static Scene financeScene;
    public static Scene fourKScene;
    public static Scene withdrawScene;
    public static Scene loanScene;
    public static Scene noteScene;
    public static Scene addNoteScene;
    public static Scene solverScene;
    public static String expression;
    public static List<String> answers;
    public static List<String> noteTitles;
    public static Map<String, String> noteContent;
    public static JSONObject saved;
    public static Note note;

    public Constant(Stage stage, Scene scene) throws Exception {

        Constant.stage = stage;

        answers = new ArrayList<>();
        noteTitles = new ArrayList<>();
        noteContent = new HashMap<>();
        setSaved();
        setAPI();

        Calculator calc = new Calculator();
        Conversion conv = new Conversion();
        Finance fin = new Finance();
        FourK fourK = new FourK();
        Withdrawal withdraw = new Withdrawal();
        Loan loan = new Loan();
        AddNote addNote = new AddNote();
        note = new Note();

        conversionScene = conv.scene;
        calculatorScene = calc.scene;
        financeScene = fin.scene;
        fourKScene = fourK.scene;
        withdrawScene = withdraw.scene;
        loanScene = loan.scene;
        noteScene = note.scene;
        addNoteScene = addNote.scene;

        mainScene = scene;
        expression = "";
    }

    /**
     * Removes the first answer from List and adds a new answer to the end of.
     * the List
     */
    public static void updateAnswers() {
        if (!answers.contains(expression) && !expression.equals("")) {
            answers.remove(0);
            answers.add(expression);
            try {
                saved.getJSONObject("answers").put("answers1", answers.get(0));
                saved.getJSONObject("answers").put("answers2", answers.get(1));
                saved.getJSONObject("answers").put("answers3", answers.get(2));
                saved.getJSONObject("answers").put("answers4", answers.get(3));
            } catch (JSONException e) {
                displayError("Unable to update answers");
            }
        }
    }

    /**
     * Returns the answers as a String.
     *
     * @return
     */
    public static String getAnswers() {
        String answer = "";
        for (int i = 0; i < answers.size(); i++) {
            answer += answers.get(i) + "\n";
        }
        return answer;
    }

    /**
     * Gathers currency rates from API as a JSONObject.
     */
    private static void setAPI() {
        try {
            URL url = new URL("https://api.fixer.io/latest");
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            JSONObject apiData = (JSONObject) new JSONTokener(new InputStreamReader(is, "UTF-8")).nextValue();
            apiData = apiData.getJSONObject("rates");
            if (apiData.has("rates")) {
                displayError("Warning:\nInaccurate currency Rates");
            } else {
                saved.put("rates", apiData);
            }
        } catch (Exception e) {
            displayError("Warning:\nInaccurate currency Rates");
        }
    }

    /**
     * Retrieves saved input from file and loads into corresponding TextFields.
     */
    private static void setSaved() {
        try {
            InputStream is = new FileInputStream(new File(FILEPATH));
            saved = (JSONObject) new JSONTokener(new InputStreamReader(is, "UTF-8")).nextValue();
            answers.add(saved.getJSONObject("answers").getString("answers1"));
            answers.add(saved.getJSONObject("answers").getString("answers2"));
            answers.add(saved.getJSONObject("answers").getString("answers3"));
            answers.add(saved.getJSONObject("answers").getString("answers4"));
            setNotes();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            displayErrorAndExit("Error retrieving saved input");
        }
    }

    private static void setNotes() {
        try {
            JSONArray titles = saved.getJSONObject("notes").names();
            if (titles == null) {
                return;
            }
            for (int i = 0; i < titles.length(); i++) {
                String temp = titles.getString(i);
                noteTitles.add(temp);
                noteContent.put(temp, saved.getJSONObject("notes").getString(temp));
            }
        } catch (JSONException e) {
            Constant.displayErrorAndExit("Problems occurred with Notes");
        }
    }

    /**
     * Saves JSONObject into file.
     */
    public static void save() {
        try {
            PrintStream output = new PrintStream(new File(FILEPATH));
            output.print(saved);
        } catch (FileNotFoundException e) {
            displayErrorAndExit("Error saving to file");
        }
    }

    /**
     * Display error message as JOptionPane and exits program.
     *
     * @param message
     */
    public static void displayErrorAndExit(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void displayError(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
}
