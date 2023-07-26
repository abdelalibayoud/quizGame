package abdelali.bayoud.quizgame.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import abdelali.bayoud.quizgame.Activities.Programming.ProgramgChooseActivity;
import abdelali.bayoud.quizgame.Classes.QuizQuestions;
import abdelali.bayoud.quizgame.Data.DatabaseHelper;
import abdelali.bayoud.quizgame.MainActivity;
import abdelali.bayoud.quizgame.R;

public class LevelsActivity extends AppCompatActivity {
private DatabaseHelper db;
private Button level_1, level_2, level_3, level_4, level_5
               ,level_6,level_7,level_8,level_9,level_10;
private     TextView txt_total_score,   txt_percentge,txtView_quizName;
private   ProgressBar progressBar;
private ConstraintLayout moreDetail;
private String quizName;
private ArrayList<String> listQuizNames;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
         level_1 = findViewById(R.id.button_level_1);
         level_2 = findViewById(R.id.button_level_2);
         level_3 = findViewById(R.id.button_level_3);
         level_4 = findViewById(R.id.button_level_4);
         level_5 = findViewById(R.id.button_level_5);
        level_6 = findViewById(R.id.button_level_6);
        level_7 = findViewById(R.id.button_level_7);
        level_8 = findViewById(R.id.button_level_8);
        level_9 = findViewById(R.id.button_level_9);
        level_10 = findViewById(R.id.button_level_10);
         txt_total_score = findViewById(R.id.txtTotalScore);
       Button back_to_categories = findViewById(R.id.button_back_categories);
        progressBar = findViewById(R.id.progressBarTotalScore);
        txt_percentge = findViewById(R.id.txtView_percentage);
        moreDetail = findViewById(R.id.more_detail);
        txtView_quizName = findViewById(R.id.column_quiz_name);


        db = new DatabaseHelper(LevelsActivity.this);
        Intent intent = getIntent();
        quizName = intent.getStringExtra("quizName");
        listQuizNames = new ArrayList<>();
        listQuizNames.add(quizName);
        txtView_quizName.setText(quizName);


        //levelsUnlocked();
      levelsUnlocked();

        moreDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent1 = new Intent(LevelsActivity.this,HistoriqueActivity.class);
               intent1.putExtra("quizName",quizName);
               startActivity(intent1);
            }
        });
        back_to_categories.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ProgramgChooseActivity.class));
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
            //here i need intent to back in categories ????????

        });

        level_1.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 1;
            String levelName = "level_1";
            level01.putExtra("level",level);
            level01.putExtra("levelName",levelName);
            level01.putExtra("quizName",quizName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);

            db.deleteLevels(1,quizName);
        });
        level_2.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 2;
            String levelName = "level_2";
            level01.putExtra("level",level);
            level01.putExtra("quizName",quizName);
           // level01.putExtra("index",cu);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(2,quizName);
        });
        level_3.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 3;
            String levelName = "level_3";
            level01.putExtra("level",level);
            level01.putExtra("quizName",quizName);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(3,quizName);

        });
        level_4.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 4;
            String levelName = "level_4";
            level01.putExtra("quizName",quizName);
            level01.putExtra("level",level);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(4,quizName);
        });
        level_5.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 5;
            String levelName = "level_5";
            level01.putExtra("quizName",quizName);
            level01.putExtra("level",level);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(5,quizName);

        });
        level_6.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 6;
            String levelName = "level_6";
            level01.putExtra("level",level);
            level01.putExtra("levelName",levelName);
            level01.putExtra("quizName",quizName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(6,quizName);
        });
        level_7.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 7;
            String levelName = "level_7";
            level01.putExtra("level",level);
            level01.putExtra("quizName",quizName);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(7,quizName);
        });
        level_8.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 8;
            String levelName = "level_8";
            level01.putExtra("level",level);
            level01.putExtra("quizName",quizName);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(8,quizName);
        });
        level_9.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 9;
            String levelName = "level_9";
            level01.putExtra("quizName",quizName);
            level01.putExtra("level",level);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(9,quizName);
        });
        level_10.setOnClickListener(v -> {

            Intent level01 = new Intent(getApplicationContext(),MainActivity.class);
            int level = 10;
            String levelName = "level_10";
            level01.putExtra("level",level);
            level01.putExtra("quizName",quizName);
            level01.putExtra("levelName",levelName);
            startActivity(level01);
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
            db.deleteLevels(10,quizName);
        });
    }
    private void buttonEnabled(Button buttonLevels, boolean enabled, int background){
        buttonLevels.setEnabled(enabled);
        buttonLevels.setBackgroundResource(background);

    }
  @SuppressLint("SetTextI18n")
  private void levelsUnlocked() {
      int scoreLevel1;
      int scoreLevel2;
      int scoreLevel3;
      int scoreLevel4;
      int scoreLevel5;
      int scoreLevel6;
      int scoreLevel7;
      int scoreLevel8;
      int scoreLevel9;
      int scoreLevel10;
      int totalScore = 0;
      if (db != null) {
           scoreLevel1 = db.getScore(1, quizName);
           scoreLevel2 = db.getScore(2, quizName);
           scoreLevel3 = db.getScore(3, quizName);
           scoreLevel4 = db.getScore(4, quizName);
           scoreLevel5 = db.getScore(5, quizName);
           scoreLevel6 = db.getScore(6, quizName);
           scoreLevel7 = db.getScore(7, quizName);
           scoreLevel8 = db.getScore(8, quizName);
           scoreLevel9 = db.getScore(9, quizName);
           scoreLevel10 = db.getScore(10, quizName);
          db.unlockLevel(quizName, scoreLevel1, 1);
          totalScore = scoreLevel1 + scoreLevel2 + scoreLevel3 + scoreLevel4 + scoreLevel5
                  + scoreLevel6 + scoreLevel7 + scoreLevel8 + scoreLevel9 + scoreLevel10;
          if (db.getUnlocked(quizName, 1) == 1) {
              //open lvl 2
              buttonEnabled(level_2, true, R.drawable.levels_background);
              buttonEnabled(level_3, false, R.drawable.lock_levels);
              buttonEnabled(level_4, false, R.drawable.lock_levels);
              buttonEnabled(level_5, false, R.drawable.lock_levels);
              buttonEnabled(level_6, false, R.drawable.lock_levels);
              buttonEnabled(level_7, false, R.drawable.lock_levels);
              buttonEnabled(level_8, false, R.drawable.lock_levels);
              buttonEnabled(level_9, false, R.drawable.lock_levels);
              buttonEnabled(level_10, false, R.drawable.lock_levels);
              db.unlockLevel(quizName, scoreLevel2, 2);


              if (db.getUnlocked(quizName, 2) == 1) {
                  //open 3
                  buttonEnabled(level_3, true, R.drawable.levels_background);
                  buttonEnabled(level_4, false, R.drawable.lock_levels);
                  buttonEnabled(level_5, false, R.drawable.lock_levels);
                  buttonEnabled(level_6, false, R.drawable.lock_levels);
                  buttonEnabled(level_7, false, R.drawable.lock_levels);
                  buttonEnabled(level_8, false, R.drawable.lock_levels);
                  buttonEnabled(level_9, false, R.drawable.lock_levels);
                  buttonEnabled(level_10, false, R.drawable.lock_levels);
                  db.unlockLevel(quizName, scoreLevel3, 3);

                  if (db.getUnlocked(quizName, 3) == 1) {
                      buttonEnabled(level_4, true, R.drawable.levels_background);
                      buttonEnabled(level_5, false, R.drawable.lock_levels);
                      buttonEnabled(level_6, false, R.drawable.lock_levels);
                      buttonEnabled(level_7, false, R.drawable.lock_levels);
                      buttonEnabled(level_8, false, R.drawable.lock_levels);
                      buttonEnabled(level_9, false, R.drawable.lock_levels);
                      buttonEnabled(level_10, false, R.drawable.lock_levels);
                      db.unlockLevel(quizName, scoreLevel4, 4);

                      if (db.getUnlocked(quizName, 4) == 1) {
                          buttonEnabled(level_5, true, R.drawable.levels_background);
                          buttonEnabled(level_6, false, R.drawable.lock_levels);
                          buttonEnabled(level_7, false, R.drawable.lock_levels);
                          buttonEnabled(level_8, false, R.drawable.lock_levels);
                          buttonEnabled(level_9, false, R.drawable.lock_levels);
                          buttonEnabled(level_10, false, R.drawable.lock_levels);
                          db.unlockLevel(quizName, scoreLevel5, 5);

                          if (db.getUnlocked(quizName, 5) == 1) {
                              buttonEnabled(level_6, true, R.drawable.levels_background);
                              buttonEnabled(level_7, false, R.drawable.lock_levels);
                              buttonEnabled(level_8, false, R.drawable.lock_levels);
                              buttonEnabled(level_9, false, R.drawable.lock_levels);
                              buttonEnabled(level_10, false, R.drawable.lock_levels);
                              db.unlockLevel(quizName, scoreLevel6, 6);

                              if (db.getUnlocked(quizName, 6) == 1) {
                                  buttonEnabled(level_7, true, R.drawable.levels_background);
                                  buttonEnabled(level_8, false, R.drawable.lock_levels);
                                  buttonEnabled(level_9, false, R.drawable.lock_levels);
                                  buttonEnabled(level_10, false, R.drawable.lock_levels);
                                  db.unlockLevel(quizName, scoreLevel7, 7);

                                  if (db.getUnlocked(quizName, 7) == 1) {
                                      buttonEnabled(level_8, true, R.drawable.levels_background);
                                      buttonEnabled(level_9, false, R.drawable.lock_levels);
                                      buttonEnabled(level_10, false, R.drawable.lock_levels);
                                      db.unlockLevel(quizName, scoreLevel8, 8);

                                      if (db.getUnlocked(quizName, 8) == 1) {
                                          buttonEnabled(level_9, true, R.drawable.levels_background);
                                          buttonEnabled(level_10, false, R.drawable.lock_levels);
                                          db.unlockLevel(quizName, scoreLevel9, 9);

                                          if (db.getUnlocked(quizName, 9) == 1) {
                                              buttonEnabled(level_10, true, R.drawable.levels_background);
                                              db.unlockLevel(quizName, scoreLevel10, 10);

                                              if (db.getUnlocked(quizName, 10) == 1) {
                                                  Toast.makeText(getApplicationContext(), "you complete this quiz game :)", Toast.LENGTH_SHORT).show();
                                              } else {
                                                  Toast.makeText(getApplicationContext(), "level 10 is not complete", Toast.LENGTH_SHORT).show();
                                              }
                                          } else {
                                              buttonEnabled(level_9, true, R.drawable.levels_background);
                                              buttonEnabled(level_10, false, R.drawable.lock_levels);
                                          }
                                      } else {
                                          buttonEnabled(level_8, true, R.drawable.levels_background);
                                          buttonEnabled(level_9, false, R.drawable.lock_levels);
                                          buttonEnabled(level_10, false, R.drawable.lock_levels);
                                      }
                                  } else {
                                      buttonEnabled(level_7, true, R.drawable.levels_background);
                                      buttonEnabled(level_8, false, R.drawable.lock_levels);
                                      buttonEnabled(level_9, false, R.drawable.lock_levels);
                                      buttonEnabled(level_10, false, R.drawable.lock_levels);
                                  }
                              } else {
                                  buttonEnabled(level_6, true, R.drawable.levels_background);
                                  buttonEnabled(level_7, false, R.drawable.lock_levels);
                                  buttonEnabled(level_8, false, R.drawable.lock_levels);
                                  buttonEnabled(level_9, false, R.drawable.lock_levels);
                                  buttonEnabled(level_10, false, R.drawable.lock_levels);
                              }
                          } else {
                              buttonEnabled(level_5, true, R.drawable.levels_background);
                              buttonEnabled(level_6, false, R.drawable.lock_levels);
                              buttonEnabled(level_7, false, R.drawable.lock_levels);
                              buttonEnabled(level_8, false, R.drawable.lock_levels);
                              buttonEnabled(level_9, false, R.drawable.lock_levels);
                              buttonEnabled(level_10, false, R.drawable.lock_levels);
                          }
                      } else {
                          buttonEnabled(level_4, true, R.drawable.levels_background);
                          buttonEnabled(level_5, false, R.drawable.lock_levels);
                          buttonEnabled(level_6, false, R.drawable.lock_levels);
                          buttonEnabled(level_7, false, R.drawable.lock_levels);
                          buttonEnabled(level_8, false, R.drawable.lock_levels);
                          buttonEnabled(level_9, false, R.drawable.lock_levels);
                          buttonEnabled(level_10, false, R.drawable.lock_levels);
                      }
                  } else {
                      buttonEnabled(level_3, true, R.drawable.levels_background);
                      buttonEnabled(level_4, false, R.drawable.lock_levels);
                      buttonEnabled(level_5, false, R.drawable.lock_levels);
                      buttonEnabled(level_6, false, R.drawable.lock_levels);
                      buttonEnabled(level_7, false, R.drawable.lock_levels);
                      buttonEnabled(level_8, false, R.drawable.lock_levels);
                      buttonEnabled(level_9, false, R.drawable.lock_levels);
                      buttonEnabled(level_10, false, R.drawable.lock_levels);
                  }
              } else {
                  buttonEnabled(level_2, true, R.drawable.levels_background);
                  buttonEnabled(level_3, false, R.drawable.lock_levels);
                  buttonEnabled(level_4, false, R.drawable.lock_levels);
                  buttonEnabled(level_5, false, R.drawable.lock_levels);
                  buttonEnabled(level_6, false, R.drawable.lock_levels);
                  buttonEnabled(level_7, false, R.drawable.lock_levels);
                  buttonEnabled(level_8, false, R.drawable.lock_levels);
                  buttonEnabled(level_9, false, R.drawable.lock_levels);
                  buttonEnabled(level_10, false, R.drawable.lock_levels);
              }
          } else {
              buttonEnabled(level_1, true, R.drawable.levels_background);
              buttonEnabled(level_2, false, R.drawable.lock_levels);
              buttonEnabled(level_3, false, R.drawable.lock_levels);
              buttonEnabled(level_4, false, R.drawable.lock_levels);
              buttonEnabled(level_5, false, R.drawable.lock_levels);
              buttonEnabled(level_6, false, R.drawable.lock_levels);
              buttonEnabled(level_7, false, R.drawable.lock_levels);
              buttonEnabled(level_8, false, R.drawable.lock_levels);
              buttonEnabled(level_9, false, R.drawable.lock_levels);
              buttonEnabled(level_10, false, R.drawable.lock_levels);

          }
      }else {
          Toast.makeText(getApplicationContext(), "error laod score", Toast.LENGTH_SHORT).show();
      }
    
      int percentage = (totalScore * 100) / 1000;
      txt_total_score.setText("Total score : " + totalScore);
      progressBar.setMax(1000);
      progressBar.setProgress(totalScore);
      txt_percentge.setText(percentage + "%");



    }
    private void historique(){






    }
}