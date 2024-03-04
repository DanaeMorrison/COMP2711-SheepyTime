import java.util.ArrayList;

public class BoardOld {
    private ArrayList<Character>[] board;

    public Board(){
        board = (ArrayList<Character>[]) new ArrayList[10];
    }

    public int getIndex(Character c){
        for(int i = 0; i < board.length; i++){
            ArrayList<Character> list = board[i];
            for(Character j : list){
                if (j.getName() == c.getName()){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean move(Character c, int n){
        int index = getIndex(c);
        if(index == -1){
            return false;
        }
        board[index].remove(c);
        board[(index + n) % 10].add(c);
        return true;
        //check for nightmare conflict?
    }

    public ArrayList<Character> getCharacters(int index){
        return board[index];
    }

    public boolean hasConflict(int index){
        ArrayList<Character> tile = board[index];
        if(tile.isEmpty()){
            return false;
        }

        boolean hasPlayer = false;
        boolean hasNightmare = false;
        
        for(Character c : tile){
            if(c.isNightmare()){
                hasNightmare = true;
            }
            else{
                hasPlayer = true;
            }
        }

        if(hasPlayer == true && hasNightmare == true){
            return true;
        }
        return false;
    }
}
