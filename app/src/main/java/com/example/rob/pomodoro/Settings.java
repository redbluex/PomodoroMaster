package com.example.rob.pomodoro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.rob.pomodoro.MainActivity.ustawienia;

public class Settings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EditText a = (EditText)findViewById(R.id.editTextMT);
        setFont(a);
        EditText b = (EditText)findViewById(R.id.editTextSB);
        setFont(b);
        EditText c = (EditText)findViewById(R.id.editTextLB);
        setFont(c);
        TextView x = (TextView)findViewById(R.id.textMT);
        setFont(x);
        TextView y = (TextView)findViewById(R.id.textSB);
        setFont(y);
        TextView z = (TextView)findViewById(R.id.textLB);
        setFont(z);
        Button k = (Button)findViewById(R.id.buttonSave);
        setFont(k);


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
    private void setFont(EditText myTextView){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Munro.ttf");
        myTextView.setTypeface(typeface);
    }
    private void setFont(TextView myTextView){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Munro.ttf");
        myTextView.setTypeface(typeface);
    }
    private void setFont(Button myTextView){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Munro.ttf");
        myTextView.setTypeface(typeface);
    }

}
