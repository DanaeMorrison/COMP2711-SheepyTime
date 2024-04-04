package model.scorelogic;

import model.Player;
import java.util.ArrayList;

public abstract class ScoreLogicFactory {
    public ScoreLogic getScoreLogic(ArrayList<Player> players){
        return createLogic(players);
    }

    protected abstract ScoreLogic createLogic(ArrayList<Player> players);
}
