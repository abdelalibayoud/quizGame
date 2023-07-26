package abdelali.bayoud.quizgame.Activities.Programming;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import abdelali.bayoud.quizgame.Activities.HomeActivity;
import abdelali.bayoud.quizgame.Activities.LevelsActivity;
import abdelali.bayoud.quizgame.MainActivity;
import abdelali.bayoud.quizgame.R;

public class ProgramgChooseActivity extends AppCompatActivity {
    private LinearLayout java_quiz, php_quiz, javaScript_quiz, flutter_quiz,kotlin_quiz,dart_quiz;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programg_choose);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        java_quiz = findViewById(R.id.java_quiz);
        php_quiz = findViewById(R.id.php_quiz);
        javaScript_quiz = findViewById(R.id.java_script_quiz);
        kotlin_quiz = findViewById(R.id.kotlin_quiz);
        flutter_quiz = findViewById(R.id.flutter_quiz);
        dart_quiz = findViewById(R.id.dart_quiz);
       Button back_home = findViewById(R.id.button_back_Home);

        back_home.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        });


        java_quiz.setOnClickListener(v -> {
           Intent intent = new Intent(ProgramgChooseActivity.this,LevelsActivity.class);
           String quizName = "Java_Quiz";
           intent.putExtra("quizName",quizName);
           startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        });
        php_quiz.setOnClickListener(v -> {
            Intent intent = new Intent(ProgramgChooseActivity.this,LevelsActivity.class);
            String quizName = "Php_Quiz";
            intent.putExtra("quizName",quizName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        });
        dart_quiz.setOnClickListener(v -> {
            Intent intent = new Intent(ProgramgChooseActivity.this,LevelsActivity.class);
            String quizName = "Dart_Quiz";
            intent.putExtra("quizName",quizName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        });
        flutter_quiz.setOnClickListener(v -> {
            Intent intent = new Intent(ProgramgChooseActivity.this,LevelsActivity.class);
            String quizName = "Flutter_Quiz";
            intent.putExtra("quizName",quizName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        });
        kotlin_quiz.setOnClickListener(v -> {
            Intent intent = new Intent(ProgramgChooseActivity.this,LevelsActivity.class);
            String quizName = "Kotlin_Quiz";
            intent.putExtra("quizName",quizName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        });
        javaScript_quiz.setOnClickListener(v -> {
            Intent intent = new Intent(ProgramgChooseActivity.this,LevelsActivity.class);
            String quizName = "JavaScript_Quiz";
            intent.putExtra("quizName",quizName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        });
    }
}