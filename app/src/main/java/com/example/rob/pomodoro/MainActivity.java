package com.example.rob.pomodoro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    static SettingsKlasa ustawienia = new SettingsKlasa();

    Level level = new Level();
    int minutes=0;
    public int seconds = 0;
    String tekstRamka;
    private CountDownTimer yourCountDownTimer;
    private int kolejka=1;
    final int mainfinal = ustawienia.getMainTime();
    final int shortfinal = ustawienia.getShortBreak();
    final int longfinal = ustawienia.getLongBreak();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.example.rob.pomodoro", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        level.setLevel(sharedPreferences.getInt("LevelPoint", 1));
        level.setExp(sharedPreferences.getInt("ExpPoint", 0));
        level.setExpLimit(sharedPreferences.getInt("MaxExpPoint", 1000));
        final ImageView img = (ImageView)findViewById(R.id.imageView);
        final RelativeLayout currentLayout = (RelativeLayout) findViewById(R.id.main_layout);
        final ImageButton click = (ImageButton)findViewById(R.id.button);
        final ImageButton click2 = (ImageButton)findViewById(R.id.button2);
        final TextView label = (TextView)findViewById(R.id.timeText);
        final TextView minuty = (TextView)findViewById(R.id.textMinutes);
        TextView colon = (TextView) findViewById(R.id.textView4);
        setFont(label);
        setFont(minuty);
        setFont(colon);
        level.changeImage(img);

        tekstRamka = ustawienia.getMainTime()+"";
        label.setText(tekstRamka);


        click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){

                yourCountDownTimer = new CountDownTimer(86400000, 1000){
                    public void onTick(long millisOnFinished){
                        if(minutes==0 && seconds==0){
                            if(kolejka%2!=0 && kolejka%4!=0){
                                minutes = mainfinal;
                                currentLayout.setBackgroundColor(getResources().getColor(R.color.colorMain));
                                level.addExp();
                                level.levelUp();
                                level.changeImage(img);
                                editor.putInt("LevelPoint", level.getLevel());
                                editor.putInt("ExpPoint", level.getExp());
                                editor.putInt("MaxExpPoint", level.getExpLimit());
                                editor.commit();
                            }
                            if(kolejka%2==0 && kolejka%4!=0){
                                minutes = shortfinal;
                                currentLayout.setBackgroundColor(getResources().getColor(R.color.colorShort));
                            }
                            if(kolejka%4==0){
                                minutes = longfinal;
                                currentLayout.setBackgroundColor(getResources().getColor(R.color.colorLong));
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
                        label.setText("You studied 24 hours. You need sleep.");
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

    private void setFont(TextView myTextView){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Munro.ttf");
        myTextView.setTypeface(typeface);
    }


}
