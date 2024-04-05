package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class RunTile extends DreamTile{
    public RunTile(){
        super("Ruuuuuun!", true, "Move forward to the space in front of the nightmare.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.getBoard().advance((player.getBoard().getIndex() - nightmare.getBoard().getIndex()) % 10);
    }
}
