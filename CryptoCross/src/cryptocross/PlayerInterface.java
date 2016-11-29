/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;


//Interface for the Player class
public interface PlayerInterface {
    
    public String getPlayerName();
    
    public Integer getPlayerScore();
    
    public Integer getCompletedWordsNum();
    
    public void setPlayerName(String str_playerName);
    
    public void setPlayerScore(Integer int_playerScore);
    
    public void setCompletedWordsNum(Integer int_wordsCompleted);
    
    public void playerCompletedAWord();
}
