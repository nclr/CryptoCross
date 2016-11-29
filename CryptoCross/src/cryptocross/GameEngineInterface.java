/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.util.ArrayList;


//Interface of the GameEngine class
public interface GameEngineInterface {
    
    public void initializeGame();
    
    public Integer getBoardSize();
    
    public void play();
    
    public Integer checkAndGradeWord(ArrayList al_word);
    
}
