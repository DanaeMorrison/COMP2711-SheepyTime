package model.tiles;
import java.util.ArrayList;

import model.CardPlayer;
import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;
import model.PlayerBoard;

public class FinalSprintTile extends DreamTile{
    public FinalSprintTile(){
        super("Final Sprint", false, "If you are Scared, move forward 7 spaces.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        if(player.isScared() >= 1){
            PlayerBoard board = player.getBoard();
            if(board.isCrossing(7)){
                new CardPlayer().resolveFenceCrossing(player);
                board.advance(7);
            }
            else{
                board.advance(7);
            }
        }
    }
}
