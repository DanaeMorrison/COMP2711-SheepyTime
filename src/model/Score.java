package model;

public class Score {
    private int winkPos;
    private int pillowPos;
    private int pillowMod;

    public Score() {
        winkPos = 0;
        pillowPos = 40;
        pillowMod = 0;
    }

    public int getDistance() {
        return pillowPos-winkPos;
    }

    public void setWinkPos(int pos) {
        winkPos = pos;
    }

    public void setPillowPos(int pos) {
        pillowPos = pos;
    }

    public void setPillowMod(int mod) {
        pillowMod = mod;
    }

    public int getWinkPos() {
        return winkPos;
    }

    public int getPillowPos() {
        return pillowPos;
    }

    public int getPillowMod() {
        return pillowMod;
    }
}
