package view;

/**
 * @author Danae Morrison
 */

public class DreamTileViewer {



    public void printDreamTile(String tileName, String tileDescription){
        System.out.println("\nDREAM TILE\n"+tileName+": "+ tileDescription);
    }

    public void printMarket(String[] names, String[] Rules){
        System.out.println("This is the current market:");
        for(int i=0;i<names.length;i++){
            System.out.println("<Option "+ (i+1)+">");
            System.out.println(names[i]);
            System.out.println("\t- "+Rules[i]);

        }
    }
}
