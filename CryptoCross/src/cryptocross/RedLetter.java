/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.Color;

//Class of a single letter
public class RedLetter extends Letter {

    //Constructors
    public RedLetter(Character ch_letter) throws UknownCharacterException {
        super(ch_letter);
        assignPoints();
        //setPoints(2 * int_points); //Double points value
        setColor(Color.RED);
    }
    
    public RedLetter(Character ch_letter, Integer int_x_coord, Integer int_y_coord) 
            throws UknownCharacterException {
        super(ch_letter, int_x_coord, int_y_coord);
        assignPoints();
        setColor(Color.RED);
    }
    
    @Override
    protected void assignPoints() throws UknownCharacterException {
        super.assignPoints();
        setPoints(2 * int_points); //Double points value
    }

    @Override
    public void setPoints(Integer int_points) {
        this.int_points = int_points;
    }

}
