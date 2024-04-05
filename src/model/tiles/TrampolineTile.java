package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class TrampolineTile extends DreamTile{
    public TrampolineTile(){
        super("Trampoline", false, "Move as many spaces as you moved immediately before landing here");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        int HAS_TO_GET_DONE; //need to add an instance var to player containing last space maybe
    }
}
