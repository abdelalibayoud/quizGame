package abdelali.bayoud.quizgame;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import abdelali.bayoud.quizgame.Activities.LevelsActivity;
import abdelali.bayoud.quizgame.Activities.Programming.ProgramgChooseActivity;
import abdelali.bayoud.quizgame.Classes.DataFromJson;
import abdelali.bayoud.quizgame.Classes.ModelQuestions;
import abdelali.bayoud.quizgame.Classes.QuizQuestions;
import abdelali.bayoud.quizgame.Data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView, number_question,txtView_score;
    private Button back_to_levels;
    private Button optionButtonA;
    private Button optionButtonB;
    private Button optionButtonC;
    private Button optionButtonD;
    private DataFromJson quizData;
    private List<QuizQuestions> quizQuestions;
    private ImageView success_emoji,failed_emoji;
    private CountDownTimer countDownTimer;
    private ProgressBar progBar;
    private DatabaseHelper db;
    private  ModelQuestions model;
    private int indexScore,indexCorrect,indexIncoorrect;
     private  int levels;
    private  int count ;
    private int currentQuestionIndex;
    private  String quizName;
    private LinearLayout optionCantainer,linearLayout;
    @SuppressLint({"MissingInflatedId", "WrongViewCast", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        questionTextView = findViewById(R.id.text_quiz_java);
        optionButtonA = findViewById(R.id.text_optionA_java);
        optionButtonB = findViewById(R.id.text_optionB_java);
        optionButtonC = findViewById(R.id.text_optionC_java);
        optionButtonD = findViewById(R.id.text_optionD_java);
        back_to_levels = findViewById(R.id.button_back_levels);
        number_question = findViewById(R.id.numberQuestion);
        success_emoji = findViewById(R.id.success_emoji);
        failed_emoji = findViewById(R.id.failed_emoji);
        txtView_score = findViewById(R.id.txt_score);
        progBar = findViewById(R.id.progressBar);
        optionCantainer = findViewById(R.id.optionContainer);
        linearLayout = findViewById(R.id.linearLayout_quiz_java);

        optionButtonA.setBackgroundResource(R.drawable.background_textview);
        optionButtonB.setBackgroundResource(R.drawable.background_textview);
        optionButtonC.setBackgroundResource(R.drawable.background_textview);
        optionButtonD.setBackgroundResource(R.drawable.background_textview);

        //load the json file
        quizData = new DataFromJson();
        InputStream inputStream = getResources().openRawResource(R.raw.quiz_java);
        setOptionButtonClickListeners();

        db = new DatabaseHelper(MainActivity.this);

         //intent from levels Acitivity
        Intent intent = getIntent();
         levels = intent.getIntExtra("level",0);
         quizName = intent.getStringExtra("quizName");
        String levelsNames = intent.getStringExtra("levelName");
        db.insertScore(quizName,levels,0);


        try {
            //initialize the data for recorderAnswers
            ModelQuestions m = new ModelQuestions(levels,0,0,quizName);
            db.recordCorrectAnswer(m);
            //load json array quiz game
            quizData.loadQuizQuestions(inputStream,levels,levelsNames,quizName);
            Toast.makeText(getApplicationContext(), "Quiz name is "+quizName, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
        }
        //load questions
        quizQuestions = quizData.getQuizQuestions();
        displayQuestion();

        back_to_levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                    intent.putExtra("quizName", quizName);
                    startActivity(intent);
                    countDownTimer.cancel();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                   countDownTimer.cancel();
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void displayQuestion() {
            QuizQuestions currentQuestion = quizQuestions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestion());
            questionTextView.setTextSize(17);
            List<String> options = currentQuestion.getOptions();
            optionButtonA.setTextColor(Color.BLACK);
            optionButtonA.setTextSize(16);
            optionButtonA.setText(options.get(0));
            optionButtonB.setTextSize(16);
            optionButtonB.setTextColor(Color.BLACK);
            optionButtonB.setText(options.get(1));
            optionButtonC.setTextSize(16);
            optionButtonC.setTextColor(Color.BLACK);
            optionButtonC.setText(options.get(2));
            optionButtonD.setTextSize(16);
            optionButtonD.setTextColor(Color.BLACK);
            optionButtonD.setText(options.get(3));
            Timer();
            int x = currentQuestionIndex+1;
            number_question.setText("Question "+x+" /10");
    }
    private void setOptionButtonClickListeners() {
        optionButtonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkAnswer(optionButtonA);
            }
        });
        optionButtonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionButtonB);
            }
        });
        optionButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionButtonC);
            }
        });
        optionButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionButtonD);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void checkAnswer(@NonNull TextView textView) {
                    QuizQuestions currentQuestion = quizQuestions.get(currentQuestionIndex);
                    String text_option = textView.getText().toString();
        if (text_option.equals(currentQuestion.getAnswer())) {
                    textView.setBackgroundResource(R.drawable.true_quiz);
                    NextQuizTimer();
                    setButtonOptionEnabled(false);
                    success_emoji.setVisibility(View.VISIBLE);
                    indexScore+=10;
                    indexCorrect+=1;
                    model = new ModelQuestions(levels,indexCorrect,indexIncoorrect,quizName);
                    txtView_score.setText("Score :  "+ indexScore);
                    Animation text_view = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                    textView.startAnimation(text_view);
        } else {
                    textView.setBackgroundResource(R.drawable.false_quiz);
                    NextQuizTimer();
                    setButtonOptionEnabled(false);
                    failed_emoji.setVisibility(View.VISIBLE);
                    indexIncoorrect+=1;
                    model = new ModelQuestions(levels,indexCorrect,indexIncoorrect,quizName);
            if (optionButtonA.getText().toString().contentEquals(currentQuestion.getAnswer())) {
                    optionButtonA.setBackgroundResource(R.drawable.true_quiz);
                    NextQuizTimer();
                    setButtonOptionEnabled(false);
                    Animation animationA = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                    optionButtonA.startAnimation(animationA);
            } else if (optionButtonB.getText().toString().contentEquals(currentQuestion.getAnswer())) {
                    optionButtonB.setBackgroundResource(R.drawable.true_quiz);
                    NextQuizTimer();
                    setButtonOptionEnabled(false);
                    Animation animationB = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                    optionButtonB.startAnimation(animationB);
            } else if (optionButtonC.getText().toString().contentEquals(currentQuestion.getAnswer())) {
                    optionButtonC.setBackgroundResource(R.drawable.true_quiz);
                    NextQuizTimer();
                    setButtonOptionEnabled(false);
                    Animation animationC = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                    optionButtonC.startAnimation(animationC);
            } else {
                    optionButtonD.setBackgroundResource(R.drawable.true_quiz);
                    NextQuizTimer();
                    setButtonOptionEnabled(false);
                    Animation animationD = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                    optionButtonD.startAnimation(animationD);
            }
        }
                db.updateCorrectAnswers(model);
                db.updateScore(quizName,levels,indexScore);
    }

    private void NextQuizTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();}
        countDownTimer = new CountDownTimer(2000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                currentQuestionIndex++;
                if (currentQuestionIndex<10){
                    optionButtonA.setBackgroundResource(R.drawable.background_textview);
                    optionButtonB.setBackgroundResource(R.drawable.background_textview);
                    optionButtonC.setBackgroundResource(R.drawable.background_textview);
                    optionButtonD.setBackgroundResource(R.drawable.background_textview);
                    displayQuestion();
                    setButtonOptionEnabled(true);
                    success_emoji.setVisibility(View.GONE);
                    failed_emoji.setVisibility(View.GONE);
                count = 0;
                playAnimmation(linearLayout,0);
                }else {
                    showSuccessDialog();
                }
            }
        }.start();
    }
    // if time down and no answers  questions move next
    @SuppressLint("ResourceAsColor")
    private void Timer() {
        progBar.setMax(20000);

        if (countDownTimer != null) {
            countDownTimer.cancel();}
        countDownTimer = new CountDownTimer(20000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
              //  textViewTime.setText(String.valueOf(millisUntilFinished /1000));
                progBar.setProgress((int)millisUntilFinished);
            }
            @Override
            public void onFinish() {
                currentQuestionIndex++;
                if (currentQuestionIndex<10){
                    optionButtonA.setBackgroundResource(R.drawable.background_textview);
                    optionButtonB.setBackgroundResource(R.drawable.background_textview);
                    optionButtonC.setBackgroundResource(R.drawable.background_textview);
                    optionButtonD.setBackgroundResource(R.drawable.background_textview);
                    displayQuestion();
                    success_emoji.setVisibility(View.GONE);
                    failed_emoji.setVisibility(View.GONE);
                    count = 0;
                    playAnimmation(linearLayout,0);
                }else {
                    showSuccessDialog();
                }
            }
        }.start();
    }

    @SuppressLint({"SetTextI18n", "MissingInflatedId", "ResourceType"})
    private void showSuccessDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_success_quiz,
                (LinearLayout)findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
          if (indexScore>=60){
                ((TextView) view.findViewById(R.id.textTitle)).setText("You wins ");
             ((TextView) view.findViewById(R.id.textMessage)).setText("Excellent you get " + indexScore);
                ((ImageView) view.findViewById(R.id.img_score)).setVisibility(View.VISIBLE);
           }else {
             ((TextView) view.findViewById(R.id.textTitle)).setText("You lost ");
                ((TextView) view.findViewById(R.id.textTitle)).setBackgroundResource(R.drawable.text_failed_backround);
                ((LinearLayout) view.findViewById(R.id.layoutDialogContainer)).setBackgroundResource(R.drawable.layout_failed_background);
                ((Button) view.findViewById(R.id.buttonAction)).setBackgroundResource(R.drawable.btn_next_failed);
                ((Button) view.findViewById(R.id.buttonActionHome)).setBackgroundResource(R.drawable.btn_back_failed);
                ((ImageView) view.findViewById(R.id.img_score)).setVisibility(View.GONE);
                ((ImageView) view.findViewById(R.id.img_score_lost)).setVisibility(View.VISIBLE);
               ((TextView) view.findViewById(R.id.textMessage)).setText("Soory you just get " + indexScore );
              ((ConstraintLayout) view.findViewById(R.id.consstrantlayout_button)).setVisibility(View.GONE);
              ((Button) view.findViewById(R.id.button_repeat)).setVisibility(View.VISIBLE);


            }

        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
              intent.putExtra("quizName", quizName);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
        view.findViewById(R.id.buttonActionHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                Intent intent = new Intent(MainActivity.this, ProgramgChooseActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
        view.findViewById(R.id.button_repeat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                intent.putExtra("quizName", quizName);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

private void setButtonOptionEnabled(boolean chouice){
        optionButtonA.setEnabled(chouice);
        optionButtonB.setEnabled(chouice);
        optionButtonC.setEnabled(chouice);
        optionButtonD.setEnabled(chouice);
}
private void playAnimmation(final View view,final int value){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                        if (value == 0 && count <4){
                            playAnimmation(optionCantainer.getChildAt(count),0);
                            count++;
                        }
                    }
                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        if (value == 0){
                            playAnimmation(view,1);
                        }
                    }
                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {}
                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {}
                });
}


}