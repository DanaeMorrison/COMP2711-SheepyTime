package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class TrioLogicFactory extends ScoreLogicFactory {

    @Override
    protected MultiPlayerLogic createLogic(ArrayList<Player> players) {
        return new TrioLogic(players);
    }

}
