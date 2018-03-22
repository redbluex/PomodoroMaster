package pl.redblue.rob.pomodoro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static pl.redblue.rob.pomodoro.MainActivity.ustawienia;

public class Settings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(pl.redblue.rob.pomodoro.R.layout.activity_settings);
        EditText a = (EditText)findViewById(pl.redblue.rob.pomodoro.R.id.editTextMT);
        setFont(a);
        EditText b = (EditText)findViewById(pl.redblue.rob.pomodoro.R.id.editTextSB);
        setFont(b);
        EditText c = (EditText)findViewById(pl.redblue.rob.pomodoro.R.id.editTextLB);
        setFont(c);
        TextView x = (TextView)findViewById(pl.redblue.rob.pomodoro.R.id.textMT);
        setFont(x);
        TextView y = (TextView)findViewById(pl.redblue.rob.pomodoro.R.id.textSB);
        setFont(y);
        TextView z = (TextView)findViewById(pl.redblue.rob.pomodoro.R.id.textLB);
        setFont(z);
        Button k = (Button)findViewById(pl.redblue.rob.pomodoro.R.id.buttonSave);
        setFont(k);


    }


    public void SaveSettings(View v){
        int x,y,z;
        EditText tekstMT = (EditText)findViewById(pl.redblue.rob.pomodoro.R.id.editTextMT);
        EditText tekstSB = (EditText)findViewById(pl.redblue.rob.pomodoro.R.id.editTextSB);
        EditText tekstLB = (EditText)findViewById(pl.redblue.rob.pomodoro.R.id.editTextLB);

        try{
            x = Integer.parseInt(tekstMT.getText().toString());
            y = Integer.parseInt(tekstSB.getText().toString());
            z = Integer.parseInt(tekstLB.getText().toString());
            ustawienia.setMainTime(x);
            ustawienia.setShortBreak(y);
            ustawienia.setLongBreak(z);
            if(x>0 && y>0 && z>0) {
                Intent i = new Intent(Settings.this, MainActivity.class);
                startActivity(i);
            }
        }
        catch(NumberFormatException e){
            Toast.makeText(this, "All fields cant be empty", Toast.LENGTH_SHORT).show();
            return;
        }

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
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

}
