package model.scorelogic;

import java.util.ArrayList;

import model.Player;

public interface ScoreLogic {


    // private final int[] FOUR_PLAYERS_LOGIC = {10, 8, 6, 5};
    // private final int[] THREE_PLAYERS_LOGIC = {10, 7, 5};
    // private final int[] TWO_PLAYERS_LOGIC = {8, 5};
    // private final int MULTI_WAKE_UP = 3;
    // private final int SINGLE_WAKE_UP = 0;

    // private ArrayList<Player> players;

    // public ScoreLogic(ArrayList<Player> players) {
    //     this.players = players;
    // }

    public ArrayList<Player> getOrder();

    public Player getWinner();

    // private void calculate
}
