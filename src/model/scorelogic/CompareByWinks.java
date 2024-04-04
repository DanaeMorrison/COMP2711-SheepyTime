package model.scorelogic;

import java.util.Comparator;

import model.Player;

public class CompareByWinks implements Comparator<Player> {

    @Override
    public int compare(Player player1, Player player2) {
        return player1.getWinks() - player2.getWinks();
    }

}