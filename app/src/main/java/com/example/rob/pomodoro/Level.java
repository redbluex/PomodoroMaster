package com.example.rob.pomodoro;

import android.widget.ImageView;

/**
 * Created by ROB on 12/21/2017.
 */

public class Level {
    private int level;
    private int exp;
    private int expLimit;

    Level(){
        level = 1;
        exp = level * 100;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

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
