/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

//Class for the game board
public class Board implements BoardInterface {

    //List<List<Letter>> letterBoard; //List of lists for the letters
    private Integer length;
    private Letter[][] boardArray;
    private Dictionary dict;
    private SecureRandom random;

    public Board(Integer boardLength) {
        this.length = boardLength;
        boardArray = new Letter[boardLength][boardLength];
         random = new SecureRandom();
        
        dict = new Dictionary("el-dictionary.txt", boardLength);
        
        int redCount = 0, blueCount = 0, balCount = 0;
        switch(boardLength) {
            case 5:
                redCount = 2;
                blueCount = 1;
                balCount = random.nextInt(2);
                break;
            case 8:
                redCount = 3;
                blueCount = 2;
                balCount = random.nextInt(2);
                break;
            case 10:
                redCount = 4;
                blueCount = 3;
                balCount = random.nextInt(2);
                break;
        }
        
        ArrayList<Integer> randomPositions = decideColor(redCount + blueCount + balCount);
        
        
        
        
//        for (int i = 0; i < boardArray.length; i++) {
//
//	    // Loop and display sub-arrays.
//	    int[] sub = values[i];
//	    for (int x = 0; x < sub.length; x++) {
//		System.out.print(sub[x] + " ");
//	    }

        //letterBoard = new ArrayList<List<Letter>>();
    }
    
    private ArrayList<Integer> decideColor(Integer count) {
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Integer newNumber;
            do {
                newNumber = random.nextInt(length - 1);
            } while (existInList(newNumber, result));
        }
        
        return result;
    }
    
    private Boolean existInList(int number, ArrayList<Integer> list) {
        for (Integer num : list) {
            if (number == num) {
                return true;
            }
        }
        
        return false;
    }

    public Letter[][] getBoardArray() {
        return boardArray;
    }
}
