package com.example.anuj.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    Button button;
    TextView textView;
    MediaPlayer mp;
    CountDownTimer countDownTimer;
    long k=30000;
    public void startTimer(View view){

        String text=button.getText().toString();
        if(text=="GO!"){
            button.setText("STOP");
            seekBar.setEnabled(false);
            countDownTimer=new CountDownTimer(k,1000){
                @Override
                public void onTick(long l) {
                    long i=l/1000;
                    String min=String.valueOf(i/60);
                    String sec=String.valueOf(i%60);
                    String time=min+":"+sec;
                    textView.setText(time);
                }
                public void onFinish(){
                    textView.setText("0:30");
                    button.setText("GO!");
                    k=30000;
                    mp=MediaPlayer.create(getApplicationContext(),R.raw.horn);
                    mp.start();
                    seekBar.setEnabled(true);
                }
            }.start();
        }
        else{
            if(countDownTimer!=null){
                countDownTimer.cancel();
                countDownTimer=null;
            }
            button.setText("GO!");
            textView.setText("0:30");
            k=30000;
            seekBar.setEnabled(true);
            seekBar.setProgress(30);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        button.setText("GO!");
        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String min=String.valueOf(i/60);
                String sec=String.valueOf(i%60);
                String time=min+":"+sec;
                textView.setText(time);
                k=i*1000;
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
