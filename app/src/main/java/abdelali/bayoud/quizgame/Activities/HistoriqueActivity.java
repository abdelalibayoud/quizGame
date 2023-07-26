package abdelali.bayoud.quizgame.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import abdelali.bayoud.quizgame.Classes.ModelQuestions;
import abdelali.bayoud.quizgame.Data.DatabaseHelper;
import abdelali.bayoud.quizgame.MainActivity;
import abdelali.bayoud.quizgame.R;

public class HistoriqueActivity extends AppCompatActivity {
private DatabaseHelper db;
private List<ModelQuestions> listHistorique;
private ModelQuestions modelQuestions;
private Button back;
private  String quizName;
private ImageView imageView;
    private List<Integer> listImages;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        back = findViewById(R.id.button_back_historique_level);
        imageView = findViewById(R.id.imageView_QuizName);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoriqueActivity.this, LevelsActivity.class);
                intent.putExtra("quizName", quizName);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);

            }
        });
        db = new DatabaseHelper(HistoriqueActivity.this);

            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            listHistorique = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
            quizName = intent.getStringExtra("quizName");
            listHistorique = db.getAllHistorique(quizName);
            changeImageView(quizName);
        Toast.makeText(getApplicationContext(), ""+quizName, Toast.LENGTH_SHORT).show();
            if (listHistorique.isEmpty()){
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }else {
                AdapterQuestion adapter = new AdapterQuestion(listHistorique);
                recyclerView.setAdapter(adapter);


      //  loadHistorique();
    }
}
    private void changeImageView(String nameQuiz){
        switch (nameQuiz) {
            case "Java_Quiz":
                imageView.setImageResource(R.drawable.java_background);
                Toast.makeText(getApplicationContext(), "java", Toast.LENGTH_SHORT).show();
                break;
            case "Php_Quiz":
                imageView.setImageResource(R.drawable.php_background);
                Toast.makeText(getApplicationContext(), "php", Toast.LENGTH_SHORT).show();

                break;
            case "Dart_Quiz":
                imageView.setImageResource(R.drawable.dart_background);
                break;
            case "Flutter_Quiz":
                imageView.setImageResource(R.drawable.flutter_background);
                break;
            case "Kotlin_Quiz":
                imageView.setImageResource(R.drawable.kotlin_background);
                break;
            default:
                imageView.setImageResource(R.drawable.javascript_background);
                break;
        }
    }
}