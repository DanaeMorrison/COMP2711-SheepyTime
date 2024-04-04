package model.tiles;
import model.DreamTile;
import model.Player;

public class ActionHeroTile extends DreamTile{
    public ActionHeroTile(){
        super("Action Hero Tile", true, "Catch 1 Zzz. You may become Brave.");
    }

    @Override
    public void useTile(Player player){
        player.setWinks(player.getWinks() + 1);
        player.setBrave();
    }
}
