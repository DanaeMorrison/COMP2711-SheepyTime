package model.scorelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import model.Player;
import model.Score;

public abstract class MultiPlayerLogic {

    private ArrayList<Player> players;
    private final int WAKE_UP = 3;

    public MultiPlayerLogic(ArrayList<Player> players) {
        this.players = players;
    }

    protected Score getScore(int index) {
        return players.get(index).getScoreboard();
    }

    protected void getPillowPoints(int index, int score) {
        getScore(index).setPillowPos(getScore(index).getPillowPos() - score);
    }

    protected void sortPlayersbyWinks() {
        Collections.sort(players, new CompareByWinks());
    }

    public void updateScore() {
        sortPlayersbyWinks();
        int[] scoreAmount = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isScared() == 2) {
                scoreAmount[i] = WAKE_UP;
            } else if (i == 0) {
                scoreAmount[i] = getLogic()[i];
            } else if (getScore(i) == getScore(i - 1)) {
                scoreAmount[i] = scoreAmount[i - 1];
            }
        }
        updateScore(scoreAmount);
    }

    private void updateScore(int[] scoreAmount) {
        for (int i = 0; i < scoreAmount.length; i++) {
            getPillowPoints(i, scoreAmount[i]);
        }
    }

    // -1, 0, 2, 0

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
