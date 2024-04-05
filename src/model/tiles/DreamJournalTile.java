package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class DreamJournalTile extends DreamTile{
    public DreamJournalTile(){
        super("Dream Journal", false, "Become Scared. Replace one of your Zzz on a single dream tile with an infinite Zzz.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.setScaredStatus(1);
        int HAS_TO_GET_DONE;
    }
}
