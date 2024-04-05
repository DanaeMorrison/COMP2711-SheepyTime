package model.scorelogic;

import java.util.ArrayList;

import model.Player;
import model.exception.InvalidScoreLogicCreationException;

/**
 * Game Logic for 2 players
 * @author Dylan Kim
 * @version 1.0
 */
public class DuoLogic extends MultiPlayerLogic {

    private final int[] DUO_SCORING_LOGIC = { 8, 5 };

    public DuoLogic(ArrayList<Player> players) {
        super(players);
        if(players.size()!=2){
            throw new InvalidScoreLogicCreationException("Number of players doesn't match with the requirement. Expected: 2, got: " + players.size());
        }   
    }

    @Override
    public int[] getLogic() {
        return DUO_SCORING_LOGIC;
    }

    

}
