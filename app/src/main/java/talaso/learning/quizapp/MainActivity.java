package talaso.learning.quizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    Button True, False;
    TextView Questions,mScoreTextView;
    int QuestionTrackerIndex;
    int question;
    ProgressBar mProgressBar;
    int mScore;



    // TODO: Uncomment to create question bank
    private TrueOrFalse[] mQuestionBank = new TrueOrFalse[] {
            new TrueOrFalse(R.string.question_1, true),
            new TrueOrFalse(R.string.question_2, true),
            new TrueOrFalse(R.string.question_3, true),
            new TrueOrFalse(R.string.question_4, true),
            new TrueOrFalse(R.string.question_5, true),
            new TrueOrFalse(R.string.question_6, false),
            new TrueOrFalse(R.string.question_7, true),
            new TrueOrFalse(R.string.question_8, false),
            new TrueOrFalse(R.string.question_9, true),
            new TrueOrFalse(R.string.question_10, true),
            new TrueOrFalse(R.string.question_11, false),
            new TrueOrFalse(R.string.question_12, false),
            new TrueOrFalse(R.string.question_13,true)
    };

    final int Progress_Bar_Increment = (int) Math.ceil(100.0 /mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        False = findViewById(R.id.false_button);
        True = findViewById(R.id.true_button);
        Questions = findViewById(R.id.question_text_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mScoreTextView = findViewById(R.id.score);

        question = mQuestionBank[QuestionTrackerIndex].getQuestionID();
        Questions.setText(question);
        True.setOnClickListener(v -> {
            checkAnswer(true);
            updateQuetion();
        });
        False.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuetion();
            }
        });
    }
    public void restart(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finishAffinity();
    }
    private void updateQuetion(){
        QuestionTrackerIndex = (QuestionTrackerIndex +1) % mQuestionBank.length;
        question = mQuestionBank[QuestionTrackerIndex].getQuestionID();
        Questions.setText(question);
        mProgressBar.incrementProgressBy(Progress_Bar_Increment);
        String score = String.valueOf(mScore);
        mScoreTextView.setText(score+ "/" +mQuestionBank.length);

        if(QuestionTrackerIndex == 0){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Score" + mScore + "/" +mQuestionBank.length);
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Cancle",
                    (dialog, id) -> finish());
            builder1.setNegativeButton(
                    "Restart",
                    (dialog, id) -> restart());
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
    private void checkAnswer (boolean userSelection){
        boolean correctAnswer = mQuestionBank[QuestionTrackerIndex].isAnswer();
        if(userSelection == correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mScore = mScore +1;
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}