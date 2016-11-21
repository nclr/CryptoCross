/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

//Class of a single letter
public class Letter {

    //private final Character ch_letter; //the character/letter it represents
    private final Alphabet enum_letter; //the character/letter it represents
    private Integer int_x_coord; //the x coordinate of the letter on the board
    private Integer int_y_coord; //the y coordinate of the letter on the board
    private Boolean tf_belongsToWord; //flag: belongs to a marked word
    private Integer int_points; //points value of the letter

    private enum Alphabet {
        Α, Β, Γ, Δ, Ε, Ζ, Η, Θ, Ι, Κ, Λ, Μ, Ν, Ξ, Ο, Π, Ρ, Σ, Τ, Υ, Φ, Χ, Ψ, Ω
    }

    //constructor
    public Letter(Alphabet enum_letter) {
        //this.ch_letter = ch_letter;
        this.enum_letter = enum_letter;
    }
    
    private void assignPoints() {
        switch (enum_letter) {
            case Α: case Ε: case Η: case Ι: case Ν: case Ο: case Σ: case Τ:
                this.int_points = 1;
                break;
                    
            case Β: case Ζ: case Θ: case Φ:
                this.int_points = 8;
                break;
                         
            case Γ: case Δ:
                this.int_points = 4;
                break;
                
            //TO-DO: complete with the rest of the letters... 
                
            default:
                
                break;
        }
    }
    
    public void setCoords(Integer int_x_coord, Integer int_y_coord) {
        this.int_x_coord = int_x_coord;
        this.int_y_coord = int_y_coord;
    }
}
