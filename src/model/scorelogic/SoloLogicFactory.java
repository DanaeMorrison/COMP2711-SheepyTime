package model.scorelogic;

import java.util.ArrayList;

import model.Player;

/**
 * Subfactory class for SoloLogic
 * @author Dylan Kim
 * @version 1.0
 */
public class SoloLogicFactory extends ScoreLogicFactory {

    @Override
    protected SoloLogic createLogic(ArrayList<Player> players) {
        return new SoloLogic(players);
    }

}
