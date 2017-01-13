/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Class for the game board
public class Board implements BoardInterface {

    private Integer boardLength;
    private Letter[][] boardArray;
    private Dictionary dict;
    private SecureRandom random;
    private Integer wordsNum;
    
    private int coloredX[];
    private int coloredY[];
    private int coloredLettersCount;

    private int redCount = 0, blueCount = 0, balCount = 0;
    
    public Board(Integer boardLength) throws UknownCharacterException {
        this.boardLength = boardLength;
        boardArray = new Letter[boardLength][boardLength];
        random = new SecureRandom();
        
        dict = new Dictionary("el-dictionary.txt", boardLength);
        
        wordsNum = 0;
        
        generateBoard();
    }
    
    private void generateBoard() throws UknownCharacterException {
        
        switch (boardLength) {
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
        
        coloredLettersCount = redCount + blueCount + balCount;

        coloredX = randomArrayGen(coloredLettersCount);
        coloredY = randomArrayGen(coloredLettersCount);
        
        int i = 0, j = 0;
        for (String word : dict.getBoardWords()) {
            wordsNum++;
            for (char c : word.toCharArray()) {
                Letter let = decideColor(i,j,c);
                boardArray[i][j] = let;
                
                if (j + 1 == boardLength) {
                    j = 0;
                    i++;
                } else {
                    j++;
                }
            }
            
        }
        
        while (i < boardLength) {
            while (j < boardLength) {
                Character c = getRandomChar();
                Letter let = decideColor(i, j, c);
                //let.assignPoints();
                boardArray[i][j] = let;
                j++;
            }
            j=0;
            i++;
        }
        
        show();
        shuffle(boardArray);
        show();
    }
    
    /* Fisher- Yates Shuffle */
    private void shuffle(Letter[][] a) {

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                Letter temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }

    
    private Letter decideColor(int x, int y, char c) throws UknownCharacterException {
        if (isColored(x,y)) {
            if (redCount > 0) {
                redCount--;
                return new RedLetter(c); // red
            }
            else if (blueCount > 0){
                blueCount--;
                return new BlueLetter(c);
            }
            else {
                return new BalandeurLetter('?');
            }
        }
        
        return new WhiteLetter(c);
    }
    
    private Boolean isColored(int x, int y) {
        for (int i = 0; i < coloredLettersCount; i++) {
            if (coloredX[i] == x && coloredY[i] == y) {
                return true;
            }
        }
        return false;
    }
    
    private int[] randomArrayGen(Integer size) {
        
        int result[] = new int[size];
        for (int i = 0; i < size; i++) {
            Integer newNumber = 0;
            if (i > 0) {
                do {
                    newNumber = random.nextInt(boardLength - 1);
                } while (existInArray(newNumber, result));
            }

            result[i] = newNumber;
        }
        
        return result;
    }
    
    private Boolean existInArray(int number, int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (number == array[i]) {
                return true;
            }
        }
        
        return false;
    }

    public Letter[][] getBoardArray() {
        return boardArray;
    }
    
    public Integer getBoardLength() {
        return boardLength;
    }
    
    public Integer getWordsNum() {
        return wordsNum;
    }
    
    private Character getRandomChar() {
        final String alphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        final int N = alphabet.length();

        return alphabet.charAt(random.nextInt(N));
    }
    
    public void show() {
        System.out.println("------------------------");
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                System.out.print(boardArray[i][j].getLetterChar() + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }
    
    //Delete a row from the board
    public void deleteRow(Integer int_row) {
        
    }
    
    //Reorder a row from the board
    public void reorderRow(Integer int_row) {
        
    }
    
    //Reorder a column from the board
    public void reorderColumn(Integer int_column) {
        
    }
    
    //Reorder the board
    public void reorderBoard() {
        shuffle(boardArray);
    }
    
    //Swap 2 Letters
    public void swapLetters(Letter letter1, Letter letter2) {
        
    }
}