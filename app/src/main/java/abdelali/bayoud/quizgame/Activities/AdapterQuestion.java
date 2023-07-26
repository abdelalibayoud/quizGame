package abdelali.bayoud.quizgame.Activities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import abdelali.bayoud.quizgame.Classes.ModelQuestions;
import abdelali.bayoud.quizgame.R;
public class AdapterQuestion extends RecyclerView.Adapter<AdapterQuestion.QuestionViewHolder> {

    private List<ModelQuestions> questionList;

    public AdapterQuestion(List<ModelQuestions> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        ModelQuestions question = questionList.get(position);
        holder.textViewLevel.setText("Level "+question.getLevel());
        holder.textViewCorrectAnswers.setText(""+question.getCorrectAnswers());
        holder.textViewIncorrectAnswers.setText(""+question.getInCorrectAnswers());
        holder.textViewQuizName.setText(""+question.getQuizName());
        holder.textViewScore.setText(""+question.getScore());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLevel;
        TextView textViewCorrectAnswers;
        TextView textViewIncorrectAnswers;
        TextView textViewQuizName;
        TextView textViewScore;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLevel = itemView.findViewById(R.id.textViewLevel);
            textViewCorrectAnswers = itemView.findViewById(R.id.textViewCorrectAnswers);
            textViewIncorrectAnswers = itemView.findViewById(R.id.textViewIncorrectAnswers);
            textViewQuizName = itemView.findViewById(R.id.textViewQuizName);
            textViewScore = itemView.findViewById(R.id.textViewScore);
        }
    }
}
