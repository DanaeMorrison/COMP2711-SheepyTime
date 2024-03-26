package view;
import java.util.ArrayList;

import model.Nightmare;
import model.Player;

public class BoardViewer{
    public static void showBoard(ArrayList<Player> players, Nightmare nightmare){
        for(int i = 0; i < 10; i++){
            System.out.println("Space " + i);
            for(Player p : players){
                if(p.getBoard().occupied(i)){
                    System.out.println(p.getName());
                }
            }
            if(nightmare.getBoard().occupied(i)){
                System.out.println(nightmare.getName());
            }
            //add dream tile functionality
        }
    }
}