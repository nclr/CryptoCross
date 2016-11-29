/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

//Class for the player
public class Player implements PlayerInterface {

    private String str_playerName; //The players name
    
    private Integer int_playerScore; //The current total number of points the 
    //player has collected

    private Integer int_wordsCompleted; //The number of words the player has
    //completed

    //Constructor
    public Player() {
        //Starting values, initialization
        this.int_playerScore = 0;
        this.int_wordsCompleted = 0;
    }

    //Getters
    @Override
    public String getPlayerName() {
        return str_playerName;
    }
    
    @Override
    public Integer getPlayerScore() {
        return int_playerScore;
    }

    @Override
    public Integer getCompletedWordsNum() {
        return int_wordsCompleted;
    }
    
    //Setters
    @Override
    public void setPlayerName(String str_playerName) {
        this.str_playerName = str_playerName;
    }
    
    @Override
    public void setPlayerScore(Integer int_playerScore) {
        this.int_playerScore = int_playerScore;
    }
    
    @Override
    public void setCompletedWordsNum(Integer int_wordsCompleted) {
        this.int_wordsCompleted = int_wordsCompleted;
    }
    
    @Override
    public void playerCompletedAWord() {
        this.int_wordsCompleted++;
    }

}
