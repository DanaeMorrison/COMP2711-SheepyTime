package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class SquadLogic extends MultiPlayerLogic {

    private final int[] SQUAD_SCORE_LOGIC = { 10, 8, 6, 5 };

    public SquadLogic(ArrayList<Player> players) {
        super(players);
    }

    @Override
    public int[] getLogic() {
        return SQUAD_SCORE_LOGIC;
    }

}
