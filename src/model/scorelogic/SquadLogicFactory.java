package model.scorelogic;

import java.util.ArrayList;

import model.Player;

/**
 * Subfactory class for SquadLogic
 * @author Dylan Kim
 * @version 1.0
 */
public class SquadLogicFactory extends ScoreLogicFactory {

    @Override
    protected MultiPlayerLogic createLogic(ArrayList<Player> players) {
        return new SquadLogic(players);
    }

}
