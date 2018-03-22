package pl.redblue.rob.pomodoro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static int x = 0;

    static SettingsKlasa ustawienia = new SettingsKlasa();
    Level level = new Level();
    int minutes=0;
    public int seconds = 0;
    String tekstRamka;
    String levelString;
    private CountDownTimer yourCountDownTimer;
    private int kolejka=1;
    final int mainfinal = ustawienia.getMainTime();
    final int shortfinal = ustawienia.getShortBreak();
    final int longfinal = ustawienia.getLongBreak();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(pl.redblue.rob.pomodoro.R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.example.rob.pomodoro", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        level.setLevel(sharedPreferences.getInt("LevelPoint", 1));
        level.setExp(sharedPreferences.getInt("ExpPoint", 0));
        level.setExpLimit(sharedPreferences.getInt("MaxExpPoint", 1000));
        x = sharedPreferences.getInt("xPoint", 0);
        final ImageView img = (ImageView)findViewById(pl.redblue.rob.pomodoro.R.id.imageView);
        final RelativeLayout currentLayout = (RelativeLayout) findViewById(pl.redblue.rob.pomodoro.R.id.main_layout);
        final ImageButton click = (ImageButton)findViewById(pl.redblue.rob.pomodoro.R.id.button);
        final ImageButton click2 = (ImageButton)findViewById(pl.redblue.rob.pomodoro.R.id.button2);
        click2.setEnabled(false);
        final TextView label = (TextView)findViewById(pl.redblue.rob.pomodoro.R.id.timeText);
        final TextView minuty = (TextView)findViewById(pl.redblue.rob.pomodoro.R.id.textMinutes);
        final TextView levelText = (TextView)findViewById(pl.redblue.rob.pomodoro.R.id.levelView);
        final TextView statusText = (TextView)findViewById(pl.redblue.rob.pomodoro.R.id.textStatus);
        final ProgressBar progressBar = (ProgressBar)findViewById(pl.redblue.rob.pomodoro.R.id.determinateBar);
        procentProgress(progressBar);
        levelText.setText(String.valueOf(level.getLevel()));
        TextView colon = (TextView) findViewById(pl.redblue.rob.pomodoro.R.id.textView4);
        setFont(label);
        setFont(minuty);
        setFont(colon);
        setFont(statusText);

        level.changeImage(img);

        tekstRamka = ustawienia.getMainTime()+"";
        label.setText(tekstRamka);


        click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){

                yourCountDownTimer = new CountDownTimer(86400000, 1000){

                    public void onTick(long millisOnFinished){
                        if(minutes==0 && seconds==0){
                            if(kolejka%2!=0 && kolejka%8!=0){
                                minutes = mainfinal;
                                currentLayout.setBackgroundColor(getResources().getColor(pl.redblue.rob.pomodoro.R.color.colorMain));
                                statusText.setText("Focus");
                                editor.putInt("LevelPoint", level.getLevel());
                                editor.putInt("ExpPoint", level.getExp());
                                editor.putInt("MaxExpPoint", level.getExpLimit());
                                editor.putInt("xPoint", x);
                                editor.commit();

                            }
                            if(kolejka%2==0 && kolejka%8!=0){
                                minutes = shortfinal;
                                currentLayout.setBackgroundColor(getResources().getColor(pl.redblue.rob.pomodoro.R.color.colorShort));
                                statusText.setText("Short break");
                                level.addExp();
                                level.levelUp();
                                levelText.setText(String.valueOf(level.getLevel()));
                                level.changeImage(img);
                                procentProgress(progressBar);

                            }
                            if(kolejka%8==0){
                                minutes = longfinal;
                                currentLayout.setBackgroundColor(getResources().getColor(pl.redblue.rob.pomodoro.R.color.colorLong));
                                statusText.setText("Long break");
                                level.addExp();
                                level.levelUp();
                                levelText.setText(String.valueOf(level.getLevel()));
                                level.changeImage(img);
                                procentProgress(progressBar);
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
                        label.setText("You studied 24 hours. You need rest.");
                    }
                }.start();
                click.setEnabled(false);
                click2.setEnabled(true);
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

    private void procentProgress(ProgressBar p){
        if(level.getExp()==0){
            x = 0;
        }
        if(level.getExp()!=0){
            x = x+(10/level.getLevel());
        }
        p.setProgress(x);
    }


}
