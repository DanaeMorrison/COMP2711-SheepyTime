package model.scorelogic;

import model.Player;
import java.util.ArrayList;

/**
 * Superfactory of all Multi-player scoring logic Factories
 * @author Dylan Kim
 * @version 1.0
 */
public abstract class MultiPlayerLogicFactory {
    public MultiPlayerLogic getScoreLogic(ArrayList<Player> players) {
        return createLogic(players);
    }

    //Factory Method
    protected abstract MultiPlayerLogic createLogic(ArrayList<Player> players);
}
