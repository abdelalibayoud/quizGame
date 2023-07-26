package abdelali.bayoud.quizgame.Classes;

public class ModelQuestions {
    private int level;
    private int correctAnswers;
    private int inCorrectAnswers;
    private  String quizName;
    private int score ;

    public ModelQuestions(int level, int correctAnswers, int inCorrectAnswers, String quizName, int score) {
        this.level = level;
        this.correctAnswers = correctAnswers;
        this.inCorrectAnswers = inCorrectAnswers;
        this.quizName = quizName;
        this.score = score;
    }

    public ModelQuestions(int level, int correctAnswers, int inCorrectAnswers, String quizName) {
        this.level = level;
        this.correctAnswers = correctAnswers;
        this.inCorrectAnswers = inCorrectAnswers;
        this.quizName = quizName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getInCorrectAnswers() {
        return inCorrectAnswers;
    }

    public void setInCorrectAnswers(int inCorrectAnswers) {
        this.inCorrectAnswers = inCorrectAnswers;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}
