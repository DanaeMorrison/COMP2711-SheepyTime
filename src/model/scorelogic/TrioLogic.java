package model.scorelogic;

import java.util.ArrayList;

import model.Player;
import model.exception.InvalidScoreLogicCreationException;

/**
 * Game Logic for 3 players
 * @author Dylan Kim
 * @version 1.0
 */
public class TrioLogic extends MultiPlayerLogic {

    private final int[] TRIO_SCORING_LOGIC = { 10, 7, 5 };

    public TrioLogic(ArrayList<Player> players) {
        super(players);
        if(players.size()!=3){
            throw new InvalidScoreLogicCreationException("Number of players doesn't match with the requirement. Expected: 3, got: " + players.size());
        } 
    }

    @Override
    public int[] getLogic() {
        return TRIO_SCORING_LOGIC;
    }

}
