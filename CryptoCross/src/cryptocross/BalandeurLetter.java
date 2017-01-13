/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.Color;

//Class of a balandeur letter
public class BalandeurLetter extends Letter {
    
    //Constructors
    public BalandeurLetter(Character ch_letter) throws UknownCharacterException {
        super(ch_letter);
        assignPoints();
        setColor(Color.WHITE);
    }
    
    public BalandeurLetter(Character ch_letter, Integer int_x_coord, Integer int_y_coord) 
            throws UknownCharacterException {
        super(ch_letter, int_x_coord, int_y_coord);
        assignPoints();
        setColor(Color.WHITE);
    }
    
    @Override
    public void setPoints(Integer int_points) {
        this.int_points = int_points;
    }
    
}
