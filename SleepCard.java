public class SleepCard implements CardInterface {
    
    private int moves;
    private int Ztokens;

    // Says whether both conditions for a card should be played
    private boolean bothConditions;
    // Constructor
    public SleepCard (int moves, int Ztokens, boolean bothConditions) {
        this.moves = moves;
        this.Ztokens = Ztokens;
        this.bothConditions = bothConditions;
    }
    public boolean isNightmare() {
        return False;
    }

    public int getMoves() {
        return moves;
    }

    public int getZtokens() {
        return Ztokens;
    }

    public boolean getConditions() {
        return bothConditions;
    }
}
