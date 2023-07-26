package abdelali.bayoud.quizgame.Classes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DataFromJson {
    private List<QuizQuestions> quizQuestions;

    public DataFromJson() {
        quizQuestions = new ArrayList<>();
    }

    public void loadQuizQuestions(InputStream inputStream,int levelsObject, String levelsName,String quizName) {
        String json = convertStreamToString(inputStream);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray javaQuizArray = jsonObject.getJSONArray(quizName);

                JSONObject levelObject = javaQuizArray.getJSONObject(levelsObject);
                JSONArray levelArray = levelObject.getJSONArray(levelsName);
                for (int j = 0; j < levelArray.length(); j++) {
                    JSONObject questionObject = levelArray.getJSONObject(j);
                    String question = questionObject.getString("question");
                    List<String> options = new ArrayList<>();
                    JSONArray optionsArray = questionObject.getJSONArray("options");
                    for (int k = 0; k < optionsArray.length(); k++) {
                        options.add(optionsArray.getString(k));
                    }
                    String answer = questionObject.getString("answer");
                    QuizQuestions quizQuestion = new QuizQuestions(question, options, answer);
                    quizQuestions.add(quizQuestion);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<QuizQuestions> getQuizQuestions() {
        return quizQuestions;
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
