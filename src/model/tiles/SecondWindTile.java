package model.tiles;
import java.util.ArrayList;
import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class SecondWindTile extends DreamTile{
    public SecondWindTile(){
        super("Second Wind", true, "Discard your hand. You may become Brave.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.setHand(new ArrayList<model.Card>());
        player.setBrave();
    }
}
