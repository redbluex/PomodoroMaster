package com.example.rob.pomodoro;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by ROB on 12/21/2017.
 */

public class Level extends AppCompatActivity {
    private int level;
    private int exp;
    private int expLimit;

    Level(){
        level = 1;
        exp = 100;
        expLimit = 1000;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
    public int getExpLimit(){return expLimit;}

    public void setExpLimit(int expLimit){this.expLimit=expLimit;}

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void changeImage(ImageView img){
        switch (level){
            case 1: img.setImageResource(R.drawable.littlepomidorbig);
                break;
            case 2: img.setImageResource(R.drawable.midpomidorbig);
                break;
            case 3: img.setImageResource(R.drawable.midpluspomidorbig);
                break;
            case 4: img.setImageResource(R.drawable.littlepomidorbig);

        }
    }

    public void addExp(){
        exp+=100;
    }


    public void levelUp(){
        if(exp>expLimit){
            level+=1;
            exp=0;
            expLimit=1000*level;
        }
    }


}
