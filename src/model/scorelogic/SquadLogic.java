package model.scorelogic;

import java.util.ArrayList;

import model.Player;
import model.exception.InvalidScoreLogicCreationException;
/**
 * Game Logic for 4 players
 * @author Dylan Kim
 * @version 1.0
 */
public class SquadLogic extends MultiPlayerLogic {

    private final int[] SQUAD_SCORE_LOGIC = { 10, 8, 6, 5 };

    public SquadLogic(ArrayList<Player> players) {
        super(players);
        if(players.size()!=4){
            throw new InvalidScoreLogicCreationException("Number of players doesn't match with the requirement. Expected: 4, got: " + players.size());
        } 
    }

    @Override
    public int[] getLogic() {
        return SQUAD_SCORE_LOGIC;
    }

}
