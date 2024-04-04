package model.scorelogic;

import model.Player;
import java.util.ArrayList;

/**
 * Superfactory of all LoficFactories
 * @author Dylan Kim
 * @version 1.0
 */
public abstract class ScoreLogicFactory {
    public ScoreLogic getScoreLogic(ArrayList<Player> players) {
        return createLogic(players);
    }

    //Factory Method
    protected abstract ScoreLogic createLogic(ArrayList<Player> players);
}
