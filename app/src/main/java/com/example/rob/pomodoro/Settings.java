package com.example.rob.pomodoro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.rob.pomodoro.MainActivity.ustawienia;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void SaveSettings(View v){
        int x,y,z;
        EditText tekstMT = (EditText)findViewById(R.id.editTextMT);
        EditText tekstSB = (EditText)findViewById(R.id.editTextSB);
        EditText tekstLB = (EditText)findViewById(R.id.editTextLB);
        x = Integer.parseInt(tekstMT.getText().toString());
        y = Integer.parseInt(tekstSB.getText().toString());
        z = Integer.parseInt(tekstLB.getText().toString());
        ustawienia.setMainTime(x);
        ustawienia.setShortBreak(y);
        ustawienia.setLongBreak(z);
        Intent i = new Intent(Settings.this, MainActivity.class);
        startActivity(i);
    }
}
