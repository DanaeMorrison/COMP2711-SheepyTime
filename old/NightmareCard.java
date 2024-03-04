public class NightmareCard implements CardInterface {
    private int moves;
    // says whether or not the nightmare jumps to a given position
    private boolean jumps;

    public NightmareCard (int moves, boolean jumps) {
        this.moves = moves;
    }

    public boolean isNightmare() {
        returns True;
    }

    public boolean isJump() {
        return jumps;
    }

    public int getMoves() {
        return moves;
    }
}
