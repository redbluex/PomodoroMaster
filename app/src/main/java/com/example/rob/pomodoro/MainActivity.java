package com.example.rob.pomodoro;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static SettingsKlasa ustawienia = new SettingsKlasa();
    int minutes=0;
    public int seconds = 0;
    String tekstRamka;
    private CountDownTimer yourCountDownTimer;
    private int kolejka=1;
    private int maxKolejka = 10;
    final int mainfinal = ustawienia.getMainTime();
    final int shortfinal = ustawienia.getShortBreak();
    final int longfinal = ustawienia.getLongBreak();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button click = (Button)findViewById(R.id.button);
        final Button click2 = (Button)findViewById(R.id.button2);
        final TextView label = (TextView)findViewById(R.id.timeText);
        final TextView minuty = (TextView)findViewById(R.id.textMinutes);
        tekstRamka = ustawienia.getMainTime()+"";
        label.setText(tekstRamka);

        click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                yourCountDownTimer = new CountDownTimer((ustawienia.getMainTime()*60000*maxKolejka), 1000){
                    public void onTick(long millisOnFinished){
                        if(minutes==0 && seconds==0){
                            if(kolejka%2!=0 && kolejka%4!=0){
                                minutes = mainfinal;

                            }
                            if(kolejka%2==0 && kolejka%4!=0){
                                minutes = shortfinal;

                            }
                            if(kolejka%4==0){
                                minutes = longfinal;
                            }
                            kolejka++;
                        }

                        if(seconds==0){
                            seconds=59;
                            minutes-=1;
                            label.setText(String.valueOf(minutes));
                        }
                        minuty.setText(String.valueOf(seconds));
                        seconds--;

                    }



                    @Override
                    public void onFinish() {
                        label.setText("Koniec czasu");
                    }
                }.start();
                click.setEnabled(false);
            }

        });

        click2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                yourCountDownTimer.cancel();
                click.setEnabled(true);
            }
        });

    }

    public void goToSettings(View v){
        Intent i = new Intent(MainActivity.this, Settings.class);
        startActivity(i);
    }

}
