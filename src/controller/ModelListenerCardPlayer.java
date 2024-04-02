package controller;

public interface ModelListenerCardPlayer {
    public void onRequestDisplayAbilityOptions (int secondAbility);
    public int onRequestAskAbility(int secondAbility);
    public int onRequestSpecificMove(int[] moves);
    public int onRequestResolveFenceCrossing(Player player);
}