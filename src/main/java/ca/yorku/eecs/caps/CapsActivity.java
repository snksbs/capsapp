package ca.yorku.eecs.caps;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CapsActivity extends AppCompatActivity
{

    private Game game;
    private String question;
    private String answer;
    private int score;
    private int qNum;
    private String log;
    ToneGenerator tg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);

      this.game = new Game();
        this.question = "";
        this.answer = "";
        this.log = "";
        this.score = 0;
        this.qNum = 1;
        ((TextView)findViewById(R.id.score)).setText("Score=" + score);
        ((TextView)findViewById(R.id.qNum)).setText("Q#=" + qNum);
        ask();
        this.tg = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

    }
    private void ask(){
        String questionAnswer = game.qa();
        question = questionAnswer.split("\\\n")[0];
        answer = questionAnswer.split("\\\n")[1];
        ((TextView)findViewById(R.id.question)).setText(question);

    }
    public void onDone (View v){

        if (this.qNum == 10) {
            finish();
        }
        EditText answerView = (EditText)findViewById(R.id.answer);
        String userAnswer = answerView.getText().toString();
        answerView.setText("");

        if (userAnswer.equalsIgnoreCase(this.answer)){
            this.score++;
            tg.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

        } else{

            tg.startTone(ToneGenerator.TONE_DTMF_1, 200);

        }
        this.log = "Q#" + this.qNum + ":" + this.question + "\nYour answer: " + userAnswer.toUpperCase() + "\nCorrect Answer: " + this.answer + "\n\n" + this.log;
        ((TextView)findViewById(R.id.log)).setText(this.log);
        this.qNum++;
        ((TextView)findViewById(R.id.qNum)).setText("Q#=" + this.qNum);
        ((TextView)findViewById(R.id.score)).setText("Score=" + this.score);
        ask ();
        if (this.qNum == 10){
            ((TextView)findViewById(R.id.qNum)).setText("Game Over");
            Button done = (Button) findViewById(R.id.done);
            done.setEnabled(false);
        }
    }

}
