package abdelali.bayoud.quizgame.Data;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import abdelali.bayoud.quizgame.Classes.ModelQuestions;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 53;

    // Table and column names for levels
    private static final String TABLE_LEVELS = "levels";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ID1 = "_id1";
    private static final String COLUMN_ID2 = "_id2";
    private static final String COLUMN_UNLOCKED = "unlocked";
    private static final String COLUMN_SCORE = "score";

    // Table and column names for questions
    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_QUESTION_ID = "_id";
    private static final String COLUMN_QUIZ_NAME = "quiz_name";
    private static final String COLUMN_NAME_GAME = "NameGame";
    private static final String COLUMN_LEVEL_ID = "level_id";
    private static final String COLUMN_CORRECT = "correct";
    private static final String COLUMN_INCORRECT = "incorrect";
Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create levels table
        String createLevelsTableQuery = "CREATE TABLE " + TABLE_LEVELS + "(" +
                COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID + " INTEGER, " +
                COLUMN_UNLOCKED + " INTEGER, " +
                COLUMN_QUIZ_NAME + " TEXT, " +
                COLUMN_SCORE + " INTEGER" +
                ")";
        db.execSQL(createLevelsTableQuery);

        // Create questions table
        String createQuestionsTableQuery = "CREATE TABLE " + TABLE_QUESTIONS + "(" +
                 COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_LEVEL_ID+ " INTEGER, " +
                COLUMN_CORRECT + " INTEGER, " +
                COLUMN_NAME_GAME + " String, " +
                COLUMN_INCORRECT + " INTEGER" +
                ")";
        db.execSQL(createQuestionsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVELS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

        // Recreate the tables
        onCreate(db);
    }

    // Unlock a level in the database
    public void unlockLevel(String quizName, int scoreLevel, int levelName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNLOCKED, scoreLevel >= 60 ? 1 : 0);
        int result = db.update(TABLE_LEVELS, values,
                COLUMN_ID + " = ? AND " + COLUMN_QUIZ_NAME + " = ?",
                new String[]{String.valueOf(levelName), quizName});
        if (result > 0) {
            Toast.makeText(context, "Unlocked added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error unlocking level", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


    // return if level locked or unlocked
    public int getUnlocked(String quizName, int level) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_UNLOCKED + " FROM " +
                TABLE_LEVELS + " WHERE " +
                COLUMN_ID + " = ? AND " + COLUMN_QUIZ_NAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(level), quizName});
        int score = 0;
        if (cursor.moveToFirst()) {
            score = cursor.getInt(0);
        }
        cursor.close();
        return score;
    }


    // Get the score for a specific level
    @SuppressLint("Range")
    public int getScore(int level, String quizName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_SCORE + " FROM " +
                TABLE_LEVELS + " WHERE " +
                COLUMN_ID + " = ? AND " + COLUMN_QUIZ_NAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(level), quizName});
        int score = 0;
        if (cursor.moveToFirst()) {
            score = cursor.getInt(0);
        }
        cursor.close();
        return score;
    }

    // Insert a new score for a specific level
    public void insertScore(String quizName,int level,int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, level);
        values.put(COLUMN_SCORE,score);
        values.put(COLUMN_QUIZ_NAME,quizName);
        long result = db.insert(TABLE_LEVELS, null, values);
        if (result != 0){
            Toast.makeText(context, "Added succesfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "erroc add data", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    // Update the score for a specific level
    public void updateScore(String quizName, int level, int newScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, newScore);
        String whereClause = COLUMN_ID + " = ? AND " + COLUMN_QUIZ_NAME + " = ?";
        String[] whereArgs = {String.valueOf(level), quizName};
        int result = db.update(TABLE_LEVELS, values, whereClause, whereArgs);
        if (result > 0) {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error updating data", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void updateCorrectAnswers(ModelQuestions m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CORRECT, m.getCorrectAnswers());
        values.put(COLUMN_INCORRECT, m.getInCorrectAnswers());

        String whereClause = COLUMN_LEVEL_ID + " = ? AND " + COLUMN_NAME_GAME + " = ?";
        String[] whereArgs = {String.valueOf(m.getLevel()), m.getQuizName()};
        int result = db.update(TABLE_QUESTIONS, values, whereClause, whereArgs);
        if (result > 0) {
            Toast.makeText(context, "Recorded successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error recording data", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    // Record a correct answer for a specific question in a level
    public void recordCorrectAnswer(ModelQuestions modelQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEVEL_ID, modelQuestions.getLevel());
        values.put(COLUMN_CORRECT, modelQuestions.getCorrectAnswers());
        values.put(COLUMN_INCORRECT, modelQuestions.getInCorrectAnswers());
        values.put(COLUMN_NAME_GAME, modelQuestions.getQuizName());
        long result = db.insert(TABLE_QUESTIONS, null, values);
        if (result != 0){
            Toast.makeText(context, "recorded succesfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "recorded error", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    @SuppressLint("Range")
    public List<ModelQuestions> getAllHistorique(String NameGame) {
        List<ModelQuestions> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " +
                COLUMN_NAME_GAME + " = ? ";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{NameGame});

        String selectQuery2 = "SELECT * FROM " + TABLE_LEVELS + " WHERE " +
                COLUMN_QUIZ_NAME + " = ? ";
        @SuppressLint("Recycle") Cursor cursor2 = db.rawQuery(selectQuery2, new String[]{NameGame});

        if (cursor.moveToNext()&& cursor2.moveToNext()) {
            do {
                int score = cursor2.getInt(cursor2.getColumnIndex(COLUMN_SCORE));
                int level = cursor.getInt(cursor.getColumnIndex(COLUMN_LEVEL_ID));
                int correctAnswers = cursor.getInt(cursor.getColumnIndex(COLUMN_CORRECT));
                int incorrectAnswers = cursor.getInt(cursor.getColumnIndex(COLUMN_INCORRECT));
                String quizName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_GAME));
                ModelQuestions modelQuestions = new ModelQuestions(level, correctAnswers, incorrectAnswers, quizName,score);
                questionList.add(modelQuestions);
            } while (cursor.moveToNext() && cursor2.moveToNext());
        }

        cursor.close();
        db.close();

        return questionList;
    }




    // Record an incorrect answer for a specific question in a level
    public void recordIncorrectAnswer(int level, int questionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEVEL_ID, level);
        values.put(COLUMN_CORRECT, 0);
        values.put(COLUMN_INCORRECT, 1);
        values.put(COLUMN_QUESTION_ID, questionId);
        db.insert(TABLE_QUESTIONS, null, values);
    }
    // delete data if level not up
    public void deleteLevels(int id, String quizName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ? AND " + COLUMN_QUIZ_NAME + " = ?";
        String[] whereArgs = {String.valueOf(id), quizName};
        int result = db.delete(TABLE_LEVELS, whereClause, whereArgs);
        if (result > 0) {
            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error deleting data", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

}
