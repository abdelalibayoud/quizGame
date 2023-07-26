package abdelali.bayoud.quizgame.Classes;

import java.util.List;

public class QuizQuestions {
    private String question;
    private List<String> options;
    private String answer;

    public QuizQuestions(String question, List<String> options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }
    // Getter methods
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
