package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class SquadLogicFactory extends ScoreLogicFactory {

    @Override
    protected ScoreLogic createLogic(ArrayList<Player> players) {
        return new SquadLogic(players);
    }

}
