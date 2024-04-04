package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class SquadLogicFactory extends ScoreLogicFactory {

    @Override
    protected MultiPlayerLogic createLogic(ArrayList<Player> players) {
        return new SquadLogic(players);
    }

}
