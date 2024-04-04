package model.tiles;
import model.DreamTile;
import model.Player;

public class ActionHeroTile extends DreamTile{
    public ActionHeroTile(){
        super("Action Hero", true, "Catch 1 Zzz. You may become Brave.");
    }

    @Override
    public void useTile(Player player){
        if(player.isScared() >= 1){
            player.setWinks(player.getWinks() + 3);
        }
    }
}
