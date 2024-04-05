package model.scorelogic;

import java.util.ArrayList;

import model.Player;

/**
 * Subfactory class for TrioLogic
 * @author Dylan Kim
 * @version 1.0
 */
public class TrioLogicFactory extends MultiPlayerLogicFactory {

    @Override
    protected MultiPlayerLogic createLogic(ArrayList<Player> players) {
        return new TrioLogic(players);
    }

}
