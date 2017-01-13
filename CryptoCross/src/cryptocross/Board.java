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
    
     /*
     * Constuctor.
     * @param boardLength The length of the board
     */
    public Board(Integer boardLength) throws UknownCharacterException {
        this.boardLength = boardLength;
        boardArray = new Letter[boardLength][boardLength];
        random = new SecureRandom();
        
        dict = new Dictionary("el-dictionary.txt", boardLength);
        
        wordsNum = 0;
        
        generateBoard();
    }
    
    /*
     * Method to generate a new board based on boardLength.
     * @return nothing.
     */
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
        
        /* We determine what colors should be placed in each letter. */
        
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
                boardArray[i][j] = let;
                j++;
            }
            j=0;
            i++;
        }
        
        /* We shuffle the board using Fisher-Yates algorithm. */
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

    /*
     * Method to decide color for a letter
     * @param x letter's row on board.
     * @param y letter's column on board.
     * @param c the character for which we decide the color.
     * @return Letter.
     */
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
    
    /*
     * Method to return if a letter is colord.
     * @return true if colored or false for the opposite.
     */
    private Boolean isColored(int x, int y) {
        for (int i = 0; i < coloredLettersCount; i++) {
            if (coloredX[i] == x && coloredY[i] == y) {
                return true;
            }
        }
        return false;
    }
    
    /*
     * Method to generate a new random array.
     * @param size The size of the array.
     * @return Array of Integers.
     */
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
    
    /*
     * Method to find if a certain number exists in an array.
     * @param number The number to find.
     * @param array The array.
     * @return True if exists or false if the opposite.
     */
    private Boolean existInArray(int number, int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (number == array[i]) {
                return true;
            }
        }
        
        return false;
    }

    /*
     * Getters.
     */
    
    public Letter[][] getBoardArray() {
        return boardArray;
    }
    
    public Integer getBoardLength() {
        return boardLength;
    }
    
    public Integer getWordsNum() {
        return wordsNum;
    }
    
     /*
     * Method to generate a new random (Hellenic) character.
     * @return The character
     */   
    private Character getRandomChar() {
        final String alphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        final int N = alphabet.length();

        return alphabet.charAt(random.nextInt(N));
    }
    
     /*
     * Debug method to print the array.
     * @return nothing.
     */     
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
}