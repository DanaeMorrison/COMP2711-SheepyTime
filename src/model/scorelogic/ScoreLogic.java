package model.scorelogic;

/**
 * Highest Hierarchy of scoring logic
 * @author Dylan Kim
 * @version 1.0
 */
public interface ScoreLogic {
    public abstract void updateScore();

    public abstract String getWinner();
}
