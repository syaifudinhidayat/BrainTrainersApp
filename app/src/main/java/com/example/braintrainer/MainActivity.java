package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int rightLocation;
    int score = 0;
    int sum = 0;
    int sLeft = 30;
    TextView scoreTextView;
    TextView resultTextView;
    TextView timerTextView;
    TextView qTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    CountDownTimer timer;
    GridLayout gridLayout;

    public void playAgain(View view){
        sum = 0;
        score = 0;
        sLeft = 30;
        scoreTextView.setText("");
        resultTextView.setText("0/0");
        timerTextView.setText("30s");
        generator();
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        timer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                sLeft--;
                timerTextView.setText(Integer.toString(sLeft) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                scoreTextView.setText("Your score is " + Integer.toString(score) + "/" + Integer.toString(sum));
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void checkAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(rightLocation))){
            score++;
            scoreTextView.setText("Correct!");
        } else{
            scoreTextView.setText("Incorrect!");
        }

        sum++;
        resultTextView.setText(Integer.toString(score) + "/" + Integer.toString(sum));
        generator();
    }

    public void generator(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        qTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        answers.clear();
        rightLocation = rand.nextInt(4);
        for(int i = 0; i < 4; i++){
            if(i == rightLocation)
                answers.add(a + b);
            else{
                int incorrect = rand.nextInt(41);
                while (incorrect == (a + b))
                    incorrect = rand.nextInt(41);
                answers.add(incorrect);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        qTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        qTextView = (TextView) findViewById(R.id.qTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gridLayout = findViewById(R.id.gridLayout);
    }
}
