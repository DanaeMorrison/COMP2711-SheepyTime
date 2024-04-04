package model.tiles;
import model.CardPlayer;
import model.DreamTile;
import model.Player;
import model.PlayerBoard;

public class FinalSprintTile extends DreamTile{
    public FinalSprintTile(){
        super("Final Sprint", true, "If you are Scared, move forward 7 spaces.");
    }

    @Override
    public void useTile(Player player){
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
