package model.scorelogic;

import java.util.Comparator;

import model.Player;

/**
 * Class that defines the priorities of Player in terms of position of Wink Token
 * @author Dylan Kim
 * @version 1.0
 */
public class CompareByWinks implements Comparator<Player> {


    /**
     * Returns the priorities of Player in terms of position of Wink Token. Higher the Wink Token locates, higher the priority
     * @return 1: player1 > player2 / 0: player1 == player2 / -1: player1 < player2
     */
    @Override
    public int compare(Player player1, Player player2) {
        return player1.getWinks() - player2.getWinks();
    }

}