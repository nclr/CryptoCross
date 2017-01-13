/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.util.ArrayList;


//Interface of the Board class
public interface BoardInterface {
    
    public Letter[][] getBoardArray();
    
    public Integer getBoardLength();
    
    public Integer getWordsNum();
    
    public void deleteRow(Integer int_row);

    public void reorderRow(Integer int_row);

    public void reorderColumn(Integer int_column);

    public void reorderBoard();
    
    public void swapLetters(Letter letter1, Letter letter2);
    
    public Boolean checkWordValidity(ArrayList<Letter> word);
        
}
