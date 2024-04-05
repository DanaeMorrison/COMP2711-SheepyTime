package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class SnoozeMovesTile extends DreamTile{
    public SnoozeMovesTile(){
        super("Snooze Moves", false, "For every 2 Zzz in your supply, move 1 space.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.getBoard().advance(player.getZtokens() / 2);
    }
}
