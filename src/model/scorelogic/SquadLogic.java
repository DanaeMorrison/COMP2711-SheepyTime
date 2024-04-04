package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public class SquadLogic implements ScoreLogic {

    private ArrayList<Player> players;

    public SquadLogic(ArrayList<Player> players){
        this.players = players;
    }

    @Override
    public ArrayList<Player> getOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player getWinner() {
        // TODO Auto-generated method stub
        return null;
    }

}
