package model.scorelogic;

import model.Player;
import java.util.ArrayList;

public abstract class ScoreLogicFactory {
    public MultiPlayerLogic getScoreLogic(ArrayList<Player> players) {
        return createLogic(players);
    }

    protected abstract MultiPlayerLogic createLogic(ArrayList<Player> players);
}
