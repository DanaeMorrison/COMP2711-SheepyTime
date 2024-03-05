public class Score {
    private Player player;
    private int playerPos;
    private int pillowPos;
    private int pillowMod;

    public Score(Player player){
        this.player = player;
        playerPos = 0;
        pillowPos = 0;
        pillowMod = 0;
    }

    public boolean won(){
        return (playerPos >= pillowPos);
    }

    public void setPlayerPos(int pos){
        playerPos = pos;
    }
    
    public void setPillowPos(int pos){
        pillowPos = pos;
    }

    public void setPillowMod(int mod){
        pillowMod = mod;
    }

    public int getPlayerPos(){
        return playerPos;
    }

    public int getPillowPos(){
        return pillowPos;
    }

    public int getPillowMod(){
        return pillowMod;
    }
}
