package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class TrioLogic extends MultiPlayerLogic {

    private final int[] TRIO_SCORING_LOGIC = { 10, 7, 5 };

    public TrioLogic(ArrayList<Player> players) {
        super(players);
    }

    @Override
    public int[] getLogic() {
        return TRIO_SCORING_LOGIC;
    }

}
