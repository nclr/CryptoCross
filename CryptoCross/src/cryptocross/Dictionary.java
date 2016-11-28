/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {
    
    private     ArrayList<String>   list;
    private     ArrayList<String>   boardWords;
    private     int                 boardSize;
    private     int                 filledLetters;
    private     int                 boardLength;
    
    /*
     *  Getters.
     */
    
    public ArrayList<String> getBoardWords() {
        return this.boardWords;
    }

    public int getTotalFilledLetters() {
        return filledLetters;
    }

    public int getEmptySpaces() {
        return boardSize - filledLetters;
    }
    
    /*
     * Constuctor.
     * @param filename The name of the dictionary used for the board
     * @param boardLength The length of one dimension of the board.
     */
    public Dictionary(String filename, int boardLength) {
        boardWords = new ArrayList<>();
        list = new ArrayList<>();
        this.boardLength = boardLength;
        
        // Read the dictionary from disk to an ArrayList.
        Scanner s;
        try {
            s = new Scanner(new File(filename));
            list = new ArrayList<>();
            while (s.hasNext()) {
                String word = Capitalize(s.next());
                list.add(word);
            }
            s.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Generate the Board.
        generateBoardWords();
    }
    
    /*
     *  Method to generate brand new board words.
     *  @return Nothing.
     */
    public void generateBoardWords() {
        boardSize           =   boardLength * boardLength;
        filledLetters       =   0;
       
        int lettersToGet = lettersToGet(filledLetters);
        
        // Loop until lettersToGet() decides we should not pick
        // any other words.
        while (lettersToGet > 0) {
            
            // We request a new word.
            String nuWord = getNuWord(lettersToGet);
            
            // Add it to board list.
            boardWords.add(nuWord);
                
            // Log our changes so we won't exceed the upper limit.
            filledLetters = filledLetters + nuWord.length();
            
            // Request how many more characters we can add to 
            // the board.
            lettersToGet = lettersToGet(filledLetters);
        }
    }
    
    /*
     * Method to decide how much space the next word has to take.
     * @param filledLetters The sum of characters of already added words.
     * @return The number of letters for the next word.
     */
    private int lettersToGet(int filledLetters) {
        
        // We want our table to be filled with at least minEmptyLetters
        // and not more than maxEmptyLetters
        // so that we can add some random characters afterwards.

        // Simple function in order to have variable length of 
        // empty characters in order with the boardlength
        // (eg. the bigger the board the more spaces should be available)
        int minEmptyLetters     = (boardLength * 5) / 6;
        int maxEmptyLetters     = ((boardLength * 5) / 6) + 3;
        int minFilledLetters    = boardSize - maxEmptyLetters;
        int maxFilledLetters    = boardSize - minEmptyLetters;
        int emptyLetters        = boardSize - filledLetters;
        
        // In the case that the numbers of letters that are already filled
        // are between the max and min value of our bounds.
        if (filledLetters > minFilledLetters && filledLetters < maxFilledLetters) {
            return 0;
        }
        // In the case that is lesser than min we want to have 
        // at least some spaces free (in fact at least minEmptyLetters
        // and maxFilledLetters at most).
        else if (filledLetters + boardLength <= maxFilledLetters) {
            return boardLength;
        }
        
        // if nothing of the above worked we return 0.
        return 0;
    }
    
    /*
     *  Method to get a new random word based on the 
     *  maximum length of characters we want.
     *  @param maxSize The maximum length of characters for the 
     *  word we want to generate.
     *  @return A new word for our board.
     */
    private String getNuWord(int maxSize) {
        SecureRandom    random        = new SecureRandom();
        int             randomNumber  = 0;
        String          nuWord        = new String();
        boolean         found;
        
        // Get a new word that is not previously used.
        do {
            // Get a new word.
            found = false;
            randomNumber = random.nextInt(list.size() - 1);
            nuWord = list.get(randomNumber);
            // Check that is not previously selected.
            for (String usedWord : boardWords) {
                if (usedWord.equals(nuWord)) {
                    found = true;
                }
            }
        } while (found == true || nuWord.length() > maxSize);
        
        return nuWord;
    }
    
    /*
     *  Method that checks if the word exists 
     *  in our list.
     *  @param wordToFind The word we want to match inside the Board.
     *  @return A flag explaining if the word is in the board or not.
     */
    public Boolean containsWord(String wordToFind) {
        for (String word : list) {
            if (word.equals(wordToFind)) {
                return true;
            }
        }
        return false;
    }
    
    /*
     *  Method to capitalize a whole word.
     *  @param word The word we want to capitalize.
     *  @return The word fully capitalized.
     */
    private String Capitalize(String word) {
        // Concert all chars to uppercase
        word = word.toUpperCase();
        // replace every occurrence of toned char
        // in the Greek Alphabet and replace it
        // with the non-toned version.
        word = word.replaceAll("[Ά]", "Α");
        word = word.replaceAll("[Έ]", "Ε");
        word = word.replaceAll("[Ή]", "Η");
        word = word.replaceAll("[Ό]", "Ο");
        word = word.replaceAll("[Ί]", "Ι");
        word = word.replaceAll("[Ύ]", "Υ");
        word = word.replaceAll("[Ώ]", "Ω");
        return word;
    }

}