package controller;
// import model.Player;

public interface ModelListenerCardPlayer {
    public void onRequestDisplayAbilityOptions (int secondAbility);
    public int onRequestAskAbility(int secondAbility);
    public int onRequestRepeatAskAbility(int secondAbility);
    public int onRequestSpecificMove(int[] moves);
    public int onRequestRepeatSpecificMove(int[] moves);
    public int onRequestResolveFenceCrossing();
    public int onRequestRepeatResolveFenceCrossing();
}