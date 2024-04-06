package model.scorelogic;

import java.util.ArrayList;

import model.Player;

/**
 * Subfactory class for DuoLogic
 * @author Dylan Kim
 * @version 1.0
 */
public class DuoLogicFactory extends MultiPlayerLogicFactory {

    @Override
    protected DuoLogic createLogic(ArrayList<Player> players) {
        return new DuoLogic(players);
    }

}
