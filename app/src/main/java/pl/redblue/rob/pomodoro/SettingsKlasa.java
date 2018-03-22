package pl.redblue.rob.pomodoro;

/**
 * Created by ROB on 12/14/2017.
 */

public class SettingsKlasa {

    public int mainTime;
    public int shortBreak;
    public int longBreak;

    SettingsKlasa(){
        mainTime = 25;
        shortBreak = 5;
        longBreak = 20;
    }

    public int getShortBreak() {
        return shortBreak;
    }

    public int getMainTime() {
        return mainTime;
    }

    public int getLongBreak() {
        return longBreak;
    }

    public void setLongBreak(int longBreak) {
        this.longBreak = longBreak;
    }

    public void setShortBreak(int shortBreak) {
        this.shortBreak = shortBreak;
    }

    public void setMainTime(int mainTime) {
        this.mainTime = mainTime;
    }
}
