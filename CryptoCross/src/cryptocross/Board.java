/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.util.ArrayList;
import java.util.List;

//Class for the game board
public class Board implements BoardInterface {

    //List<List<Letter>> letterBoard; //List of lists for the letters
    private Letter[][] boardArray;

    public Board(Integer boardSize) {
        switch (boardSize) {
            case 25:
                boardArray = new Letter[5][5];
                break;
            case 64:
                boardArray = new Letter[8][8];
                break;
            case 100:
                boardArray = new Letter[10][10];
                break;
        }
        
//        for (int i = 0; i < boardArray.length; i++) {
//
//	    // Loop and display sub-arrays.
//	    int[] sub = values[i];
//	    for (int x = 0; x < sub.length; x++) {
//		System.out.print(sub[x] + " ");
//	    }

        //letterBoard = new ArrayList<List<Letter>>();
    }

    public Letter[][] getBoardArray() {
        return boardArray;
    }
}
