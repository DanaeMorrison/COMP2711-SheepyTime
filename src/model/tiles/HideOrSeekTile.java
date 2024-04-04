package model.tiles;
import model.DreamTile;
import model.Player;

public class HideOrSeekTile extends DreamTile{
    public HideOrSeekTile(){
        super("Hide or Seek", true, "If the nightmare is on an odd-numbered space, gain 2 winks.\n If it's on an even-numbered space, move 2 spaces.");
    }

    @Override
    public void useTile(Player player){
        int HAS_TO_GET_DONE; //need to know nightmare position
    }
}
