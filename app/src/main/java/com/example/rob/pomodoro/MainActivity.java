package com.example.rob.pomodoro;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int counter = 0;
    String tekstRamka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click = (Button)findViewById(R.id.button);
        Button clickset = (Button)findViewById(R.id.buttonAccept);
        final TextView label = (TextView)findViewById(R.id.timeText);

        clickset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText editRamka = (EditText)findViewById(R.id.setTime);
                tekstRamka = editRamka.getText().toString();
                label.setText(tekstRamka);
            }
        });

        click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new CountDownTimer(10000, 1000){
                    public void onTick(long millisOnFinished){
                        label.setText(String.valueOf(Integer.parseInt(tekstRamka)-counter));
                        counter++;
                    }

                    @Override
                    public void onFinish() {
                        label.setText("Koniec czasu");
                    }
                }.start();
            }
        });

    }

    public void goToSettings(View v){
        Intent i = new Intent(MainActivity.this, Settings.class);
        startActivity(i);
    }


}
