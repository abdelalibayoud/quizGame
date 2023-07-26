package abdelali.bayoud.quizgame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import abdelali.bayoud.quizgame.Activities.Programming.ProgramgChooseActivity;
import abdelali.bayoud.quizgame.R;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout english_quiz,programming_quiz,sport_quiz,medical_quiz,islamic_quiz,science_quiz;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        english_quiz = findViewById(R.id.english_quiz);
        programming_quiz = findViewById(R.id.programming_quiz);
        sport_quiz = findViewById(R.id.sport_quiz);
        medical_quiz = findViewById(R.id.medical_quiz);
        islamic_quiz = findViewById(R.id.islamic_quiz);
        science_quiz = findViewById(R.id.science_quiz);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        english_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        programming_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), ProgramgChooseActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,
                            R.anim.slide_out_left);
            }
        });
    }
}