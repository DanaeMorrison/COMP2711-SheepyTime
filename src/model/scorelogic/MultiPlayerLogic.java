package model.scorelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import model.Player;
import model.Score;

/**
 * Abtract Class contains scoring logic only for Multiplayer
 * @author Dylan Kim
 * @version 1.0
 */
public abstract class MultiPlayerLogic{

    private ArrayList<Player> players;
    private final int WAKE_UP = 3;

    public MultiPlayerLogic(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Method that determines amount of pillowPoint that each player will get, and then call helper method to actually add them
     */
    public void updateScore() {
        sortPlayersbyWinks();
        int[] pillowPoints = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isScared() == 2) {
                pillowPoints[i] = WAKE_UP;
            } else if (i == 0) {
                pillowPoints[i] = getLogic()[i];
            } else if (getScore(i) == getScore(i - 1)) {
                pillowPoints[i] = pillowPoints[i - 1];
            }
        }
        updateScore(pillowPoints);
    }
    
    /**
     * Helper method that adds pillowPoints to each pillow
     * @param pillowPoints array of pillowPoints
     */
    private void updateScore(int[] pillowPoints) {
        for (int i = 0; i < pillowPoints.length; i++) {
            getPillowPoints(i, pillowPoints[i]);
        }
    }

    /**
     * method that returns Score object of certain player
     * @param index index of player in players
     * @return their Score object
     */
    protected Score getScore(int index) {
        return players.get(index).getScoreboard();
    }

    /**
     * helper method that substract the pillowPoint from current position of Pillow Token
     * @param index index of player
     * @param pillowPoint amount of pillowPoint
     */
    protected void getPillowPoints(int index, int pillowPoint) {
        getScore(index).setPillowPos(getScore(index).getPillowPos() - pillowPoint);
    }

    /**
     * Method that sort the list<Player> by player's position of Wink Token
     */
    protected void sortPlayersbyWinks() {
        Collections.sort(players, new CompareByWinks());
    }



    /**
     * Method that returns the list of winner 
     * @return the list of winner, if there is winner. otherwise empty list
     */
    public List<Player> getWinner() {
        ArrayList<Player> winners = new ArrayList<>();
        int winningPoints = 0;
        for (int i = 0; i < players.size(); i++) {
            if (getScore(i).getDistance() > winningPoints) {
                continue;
            }

            if (winners.size() == 0) {
                winningPoints = addWinner(winners, players.get(i));
            } else if (newWinningPoints(i, winningPoints)) {
                winningPoints = newWinner(winners, players.get(i));
            } else if (higherWinksPosition(winners, i)) {
                newWinner(winners, players.get(i));

            } else if (sameWinksPosition(winners, i)) {
                addWinner(winners, players.get(i));
            }
        }

        return winners;
    }

    private boolean newWinningPoints(int index, int winningPoints) {
        return getScore(index).getDistance() < winningPoints;
    }

    private boolean sameWinksPosition(ArrayList<Player> winners, int index) {
        return winners.get(0).getScoreboard().getWinkPos() == getScore(index).getWinkPos();
    }

    private boolean higherWinksPosition(ArrayList<Player> winners, int index) {
        return winners.get(0).getScoreboard().getWinkPos() < getScore(index).getWinkPos();
    }

    private int addWinner(ArrayList<Player> winners, Player player) {
        winners.add(player);
        return player.getScoreboard().getDistance();
    }

    private int newWinner(ArrayList<Player> winners, Player player) {
        winners.clear();
        return addWinner(winners, player);
    }

    public abstract int[] getLogic();

}
