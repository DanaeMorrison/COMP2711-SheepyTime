package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class SoloLogic implements ScoreLogic {

    private ArrayList<Player> players;

    public SoloLogic(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public ArrayList<Player> getOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
    }

    @Override
    public Player getWinner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWinner'");
    }

}
