package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class StepBackTile extends DreamTile{
    public StepBackTile(){
        super("Step Back", true, "Move backward 1 space. You may become brave.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.getBoard().advance(-1);
        player.setBrave();
    }
}
