package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekbar;                //1 second = 1000 milisecond;
    boolean counterafterbuttonpush = false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("0:30");            //texts resets
        seekbar.setProgress(30);              // seekbar goes as it was
        seekbar.setEnabled(true);       //it is avaialle to use
        countDownTimer.cancel();          // counter stops
        button.setText("GO");            //button display changes
        counterafterbuttonpush=false;              //so you can enter else and work with it again
    }

    public void click(View view) {
        if (counterafterbuttonpush){

            resetTimer();


        }
        else{
        counterafterbuttonpush = true;
        seekbar.setEnabled(false);//counter would be there to be used
        button.setText("STOP!");


        countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {//+100 thora upar nicha ko handle karna ka liya its done

            @Override
            public void onTick(long millisUntilFinished) {

                updatetime((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
                resetTimer();                // when timer hits 0:0 its all back to as it was

            }
        }.start();

    }}

    public void updatetime(int progress) {
        int minutes = progress / 60;          //wasy to find minutes from second
        int second = progress - (minutes * 60);// way to find seconds from left over seconds (after fuctioning minutes)
        String seconds = Integer.toString(second);

        if (second <= 9) {
            seconds = "0" + seconds;
        }
        textView.setText(Integer.toString(minutes) + ":" + seconds);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        seekbar.setMax(600);          //max  would be 10 mins , so in second it is 60*10
        seekbar.setProgress(30);//30  seconds

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}