package com.example.mindexercise;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    int locCorrectAns;
    TextView showResult;
    Toast toast;
    TextView scoreTextView;
    TextView timeTextView;
    TextView quesTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView gameOverTextView;
    CountDownTimer timer;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    TextView ruleTextView;
    Button playAgain;
    Button volumeButton;
    boolean k=false;
    public void volumeControl(View view){
        if(k==false) {
            k=true;
            mediaPlayer1.pause();
            volumeButton.setBackgroundResource(R.drawable.mute);
        }
        else{
            k=false;
            mediaPlayer1.start();
            volumeButton.setBackgroundResource(R.drawable.volume);
    }}


    public void playAgain(View view){

        score=0;
        totalScore=0;
        gameOverTextView.animate().translationY(-800).setDuration(100);
        scoreTextView.setText(" "+Integer.toString(score) + "/" + Integer.toString(totalScore)+" ");
        showResult.setVisibility(View.GONE);
        playAgain.setVisibility(View.GONE);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        gameOverTextView.setVisibility(View.GONE);
        play();
    }
    public void play(){

        volumeButton.setVisibility(View.VISIBLE);
         goButton.setVisibility(View.INVISIBLE);
         ruleTextView.setVisibility(View.INVISIBLE);
         button1.setVisibility(View.VISIBLE);
         button2.setVisibility(View.VISIBLE);
         button3.setVisibility(View.VISIBLE);
         button4.setVisibility(View.VISIBLE);
         timeTextView.setVisibility(View.VISIBLE);
         scoreTextView.setVisibility(View.VISIBLE);
         quesTextView.setVisibility(View.VISIBLE);
         showResult.setVisibility(View.VISIBLE);
        showResult.setText("       ");
         timer = new CountDownTimer(30000,1000) {
             @Override
             public void onTick(long millisUntilFinished) {
                // Log.i("tag","hurry up");
                 //showTime((int)millisUntilFinished/1000);
                 timeTextView.setText(" "+Integer.toString((int)millisUntilFinished/1000)+" ");
             }

             @Override
             public void onFinish() {
                 if(score<5){
                     quesTextView.setText("         Poor");
                 }else if(score>4 && score<10){
                     quesTextView.setText("      Satisfactory");
                 }else if(score>9 & score<15){
                     quesTextView.setText("         Amazing");
                 }else if(score>14 & score<20){
                     quesTextView.setText("      Fabulous");
                 }else{
                     quesTextView.setText("        Outstanding");
                 }

                 //Log.i("tag","time up");
                 button1.setEnabled(false);
                 button2.setEnabled(false);
                 button3.setEnabled(false);
                 button4.setEnabled(false);
                 button1.setText("*");
                 button2.setText("*");
                 button3.setText("*");
                 button4.setText("*");
                 //toast.makeText(getApplicationContext(), "time over", Toast.LENGTH_LONG).show();
                 //showResult.setText(" Time up :-( ");
                 gameOverTextView.setVisibility(View.VISIBLE);
                 gameOverTextView.animate().translationY(580).setDuration(1000);
                 mediaPlayer1.pause();
                 mediaPlayer2.start();
                 playAgain.setVisibility(View.VISIBLE);
                 showResult.setVisibility(View.GONE);

             }
         }.start();
         nextQuestion();

         mediaPlayer1.start();
 }

    public void start(View view) {
        play();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void nextQuestion() {

        ArrayList<Integer> answers = new ArrayList<Integer>();
        Random rand = new Random();
        int a, b;
        boolean flag = false;
        //Random sign=new Random();
        int result = 0;

        int sign = rand.nextInt(4);
        switch (sign) {
            case 0:
                a = ThreadLocalRandom.current().nextInt(10, 50);
                b = ThreadLocalRandom.current().nextInt(10, 50);
                flag = false;
                result = a + b;
                quesTextView.setText("         "  + Integer.toString(a) + " + "  + Integer.toString(b));
                break;
            case 1:
                a = ThreadLocalRandom.current().nextInt(10, 50);
                b = ThreadLocalRandom.current().nextInt(10, 50);
                flag = false;
                if (a < b) {
                    result = b - a;
                    quesTextView.setText("         "  +Integer.toString(b) + " - " + Integer.toString(a));
                } else {
                    result = a - b;
                    quesTextView.setText( "         "  +Integer.toString(a) + " - " + Integer.toString(b));
                }
                break;
            //case 2:result=a/b;quesTextView.setText(Integer.toString(a) + "/" + Integer.toString(b));break;
            case 2:
                a = ThreadLocalRandom.current().nextInt(10, 20);
                b = ThreadLocalRandom.current().nextInt(1, 10);
                flag = true;
                result = a * b;
                quesTextView.setText("          " +Integer.toString(a) + " X " + Integer.toString(b));
                break;
            case 3:
                b=ThreadLocalRandom.current().nextInt(10, 20);
                a=b*rand.nextInt(5);
                result=a/b;
                flag=false;
                quesTextView.setText( "        " +Integer.toString(a) + " / " + Integer.toString(b));
                break;
        }

        locCorrectAns = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == locCorrectAns) {
                answers.add(result);
            } else {
                if (flag) {
                   // Toast.makeText(getApplic ationContext(), "hurray", Toast.LENGTH_LONG).show();
                    int wrongAnswer = ThreadLocalRandom.current().nextInt(result - 2, result + 5);
                   while (result == wrongAnswer) {
                        wrongAnswer = ThreadLocalRandom.current().nextInt(result - 2, result + 5);
                    }
                    answers.add(wrongAnswer);
                } else {
                   // Toast.makeText(getApplicationContext(), "bhgg", Toast.LENGTH_LONG).show();
                    int wrongAnswer = ThreadLocalRandom.current().nextInt(result-5,result+5);
                    while (result == wrongAnswer) {
                        wrongAnswer = ThreadLocalRandom.current().nextInt(result-5,result+5);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    int score = 0;
    int totalScore = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void chooseAnswer(View view) {
        showResult.setVisibility(view.INVISIBLE);
        Log.i("tag", view.getTag().toString());
        if (Integer.toString(locCorrectAns).equals(view.getTag().toString())) {
            //Log.i("answer","CORRECT ANSWER");
            score++;
            showResult.setText(" RIGHT :-)");
            showResult.setVisibility(view.VISIBLE);
        } else {
            //Log.i("answer","wrong answer");
            showResult.setText("WRONG :-(");
            showResult.setVisibility(view.VISIBLE);
        }
        totalScore++;
        scoreTextView.setText("  "+Integer.toString(score) + "/" + Integer.toString(totalScore)+" ");
        nextQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        volumeButton=findViewById(R.id.volumeButton);
        gameOverTextView = findViewById(R.id.gameOverTextView);
        gameOverTextView.animate().translationY(-800).setDuration(100);
        showResult = findViewById(R.id.showResult);
        button1 = findViewById(R.id.optionButton1);
        button2 = findViewById(R.id.optionButton2);
        button3 = findViewById(R.id.optionButton3);
        button4 = findViewById(R.id.optionButton4);
        quesTextView = findViewById(R.id.quesTextView);
        timeTextView = findViewById(R.id.timeTextView);
        mediaPlayer1 = MediaPlayer.create(this, R.raw.mind);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.buzzer);
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        timeTextView.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.GONE);
        quesTextView.setVisibility(View.GONE);
        showResult.setVisibility(View.GONE);
        goButton.setVisibility(View.VISIBLE);
        ruleTextView=findViewById(R.id.ruleTextView);
        playAgain=findViewById(R.id.playAgain);
        playAgain.setVisibility(View.GONE);
        volumeButton.setVisibility(View.GONE);
    }

}

