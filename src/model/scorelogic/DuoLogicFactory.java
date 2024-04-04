package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class DuoLogicFactory extends ScoreLogicFactory {

    @Override
    protected MultiPlayerLogic createLogic(ArrayList<Player> players) {
        return new DuoLogic(players);
    }

}
