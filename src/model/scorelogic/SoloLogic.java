package model.scorelogic;

import java.util.ArrayList;

import model.Player;
import model.exception.InvalidScoreLogicCreationException;

/**
 * Game Logic for solo
 * @author Dylan Kim
 * @version 1.0
 */
public class SoloLogic implements ScoreLogic {

    public SoloLogic(ArrayList<Player> players) {
        if(players.size()!=1){
            throw new InvalidScoreLogicCreationException("Number of players doesn't match with the requirement. Expected: 1, got: " + players.size());
        } 
    }

    @Override
    public void updateScore() {
        
    }

}
