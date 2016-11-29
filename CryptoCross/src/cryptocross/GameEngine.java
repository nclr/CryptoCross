/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.util.ArrayList;


//Class for the game logic
public class GameEngine implements GameEngineInterface {
    
    //Dictionary dict;
    private Board   gameBoard; //The game Board
    private Integer int_boardSize; //The game Board size
    private Player  player; //The Player of the game
    
    private Integer int_pointGoal; //The target/goal number of points to be attained
    //by the player.
    
    private Integer int_maxAllowedWords; //The max number of words the player is
    //allowed to complete
    
    
    //Constructor
    public GameEngine() {
         //dict = new Dictionary();
         //gameBoard = new Board();
         //player = new Player();
         
         initializeGame();
    }
    
    public void initializeGame() {
        player = new Player();
    }
    
    //Getters
    public Integer getBoardSize() {
        return int_boardSize;
    }
    
    
    //Setters
//    public void setBoardSize(Integer int_boardSize) {
//        this.int_boardSize = int_boardSize;
//    }
    
    //Function for the flow loop of the game
    public void play() {
        
    }
    
    //Check if a word is valid and grade it
    //@returns the word's points if valid or
    //-1 if word doesn't exist
    public Integer checkAndGradeWord(ArrayList al_word) {
        return gameBoard.checkAndGradeWord(al_word);
    }
}
