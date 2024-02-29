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
}
