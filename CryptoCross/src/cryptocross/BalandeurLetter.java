/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;


//Class of a balandeur letter
public class BalandeurLetter extends Letter {
    
    //Constructors
    public BalandeurLetter(Letter.Alphabet enum_letter) {
        super(enum_letter);
        //this.ch_letter = ch_letter;
        //this.enum_letter = enum_letter;
    }

    public BalandeurLetter(Character ch_letter) {
        super(ch_letter);
    }
    
    
    
    @Override
    public void setPoints(Integer int_points) {
        this.int_points = int_points;
    }
    
}
