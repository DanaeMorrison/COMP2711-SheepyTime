package model.tiles;
import model.DreamTile;
import model.Player;

public class PerfectLandingTile extends DreamTile{
    public PerfectLandingTile(){
        super("Perfect Landing", true, "Catch 1 Zzz. You may become Brave.");
    }

    @Override
    public void useTile(Player player){
        if(player.justCrossed()){
            player.setWinks(player.getWinks() + player.getBoard().getIndex() + 1);
        }
    }
}
