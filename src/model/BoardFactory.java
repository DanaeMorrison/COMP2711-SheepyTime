package model;
public class BoardFactory{
    public BoardInterface createBoard(String in) throws IllegalArgumentException{
        if(in.equals("Player")){
            return new PlayerBoard();
        }
        else if(in.equals("Nightmare")){
            return new NightmareBoard();
        }
        else{
            throw new IllegalArgumentException("Not a valid board type!");
        }
    }
}