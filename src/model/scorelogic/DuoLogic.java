package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class DuoLogic extends MultiPlayerLogic {

    private final int[] DUO_SCORING_LOGIC = { 8, 5 };

    public DuoLogic(ArrayList<Player> players) {
        super(players);
    }

    @Override
    public int[] getLogic() {
        return DUO_SCORING_LOGIC;
    }

    

}
