/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

//Class that checks if a letter can be selected as next
public class WordPilot {
    private Integer xPosition;
    private Integer yPosition;
    private Integer xNew;
    private Integer yNew;
    private Letter[][] ar_letters;
    
    //Constructor
    public WordPilot(Letter[][] ar_letters) {
        this.ar_letters = ar_letters;
    }
    
    //Getters
    public Integer getXposition() {
        return xPosition;
    }
    
    public Integer getYposition() {
        return yPosition;
    }
    
    //Setters
    public void setLetterArray(Letter[][] ar_letters) {
        this.ar_letters = ar_letters;
    }
    
    public void setXposition(Integer xPosition) {
        this.xPosition = xPosition;
    }
    
    public void setYposition(Integer yPosition) {
        this.xPosition = yPosition;
    }
    
    public void setNewX(Integer xNew) {
        this.xNew = xNew;
    }
    
    public void setNewY(Integer yNew) {
        this.yNew = yNew;
    }
    
    public Boolean isNeighbour(Integer xPosition, Integer yPosition, Integer xNew, Integer yNews) {
        if ((xNew - xPosition == 0 || xNew - xPosition == 1) 
                && (yNew - yPosition == 0 || yNew - yPosition == 1)) {
            return true;
        } else {
            return false;
        }
    }
}
