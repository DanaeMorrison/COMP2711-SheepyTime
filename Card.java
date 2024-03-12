import java.util.ArrayList;

public class Card {
    // Says whether both conditions for a card should be played
    private final boolean bothConditions;
    private final boolean isNightmare;

    private int[] moves;
    private int Ztokens;
    private int winks;
    private int jumpPos;
    private int spiderMove;

    private Card(boolean bothConditions, boolean isNightmare) {
        this.bothConditions = bothConditions;
        this.isNightmare = isNightmare;
    }

    public Card (boolean bothConditions, boolean isNightmare, int[] moves, int Ztokens, int winks, int jumpPos, int spiderMove) {
        this.bothConditions = bothConditions;
        this.isNightmare = isNightmare;
        this.moves = moves;
        this.Ztokens = Ztokens;
        this.winks = winks;
        this.jumpPos = jumpPos;
        this.spiderMove = spiderMove;
    }
    
    public void setMoves(int[] moves) {
        this.moves = moves;
    }

    public void setZtokens(int Ztokens) {
        this.Ztokens = Ztokens;
    }

    public void setWinks(int winks) {
        this.winks = winks;
    }

    public void setJumpPos(int jumpPos) {
        this.jumpPos = jumpPos;
    }

    public void setSpiderMove(int spiderMove) {
        this.spiderMove = spiderMove;
    }

    public int[] getMoves() {
        return moves;
    }

    public int getZtokens() {
        return Ztokens;
    }

    public int getWinks() {
        return winks;
    }

    public int getJumpPos() {
        return jumpPos;
    }

    public int getSpiderMove() {
        return spiderMove;
    }

    public boolean bothConditions() {
        return bothConditions;
    }

    public boolean isNightmare(){
        return isNightmare;
    }

    public static class Builder {
        private final boolean bothConditions;
        private final boolean isNightmare;

        private int[] moves;
        private int Ztokens = 0;
        private int winks = 0;
        private int jumpPos = -1;
        private int spiderMove = 0;

        public Builder (boolean bothConditions, boolean isNightmare) {
            this.bothConditions = bothConditions;
            this.isNightmare = isNightmare;
        }

        public Builder withMoves(int[] moves) {
            this.moves = moves;
            return this;
        }

        public Builder withZtokens(int Ztokens) {
            this.Ztokens = Ztokens;
            return this;
        }

        public Builder withWinks(int winks) {
            this.winks = winks;
            return this;
        }

        public Builder withJumpPos(int jumpPos) {
            this.jumpPos = jumpPos;
            return this;
        }

        public Builder withSpiderMove(int spiderMove) {
            this.spiderMove = spiderMove;
            return this;
        }

        public Card build() {
            Card card = new Card(bothConditions, isNightmare);
            card.setMoves(moves);
            card.setZtokens(Ztokens);
            card.setWinks(winks);
            card.setJumpPos(jumpPos);
            card.setSpiderMove(spiderMove);
            return card;
        }
    }
}
