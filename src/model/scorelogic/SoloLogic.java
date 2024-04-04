package model.scorelogic;

import java.util.ArrayList;

import model.Player;
import model.exception.InvalidScoreLogicCreationException;

/**
 * Game Logic for solo
 * 
 * @author Dylan Kim
 * @version 1.0
 */
public class SoloLogic{

    private final int WAKE_UP = 0;

    private final int SCOING_RATIO = 5;

    private ArrayList<Player> players;

    public SoloLogic(ArrayList<Player> players) {
        if(players.size()!=1){
            throw new InvalidScoreLogicCreationException("Number of players doesn't match with the requirement. Expected: 1, got: " + players.size());
        } 
        this.players = players;
    }

    public void updateScore(int ) {

    }

}
