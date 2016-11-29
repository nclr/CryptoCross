/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.Color;

//Class of a single letter
public abstract class Letter {

    protected Character ch_letter; //The character/letter it represents
    protected Alphabet enum_letter; //The character/letter it represents
    protected Integer int_x_coord; //The x coordinate of the letter on the board
    protected Integer int_y_coord; //The y coordinate of the letter on the board
    protected Boolean tf_belongsToWord; //Flag: belongs to a marked word
    protected Integer int_points; //Points value of the letter
    protected Color col_color; //Color group of letter, each color represents a different
    //points value

    protected enum Alphabet {
        Α, Β, Γ, Δ, Ε, Ζ, Η, Θ, Ι, Κ, Λ, Μ, Ν, Ξ, Ο, Π, Ρ, Σ, Τ, Υ, Φ, Χ, Ψ, Ω
    }

    //Constructor
    public Letter(Alphabet enum_letter) {
        //this.ch_letter = ch_letter;
        this.enum_letter = enum_letter;
    }

    public Letter(Character ch_letter) {
        this.ch_letter = ch_letter;
    }

    //Getters
    public Character getLetterChar() {
        return ch_letter;
    }

    public Integer getPoints() {
        return int_points;
    }

    public Color getColor() {
        return col_color;
    }

    public Integer getXcoord() {
        return int_x_coord;
    }

    public Integer getYcoord() {
        return int_y_coord;
    }

    public Boolean getBelongsToWord() {
        return tf_belongsToWord;
    }

    //Setters
    public void setXcoord(Integer int_x_coord) {
        this.int_x_coord = int_x_coord;
    }

    public void setYcoord(Integer int_y_coord) {
        this.int_y_coord = int_y_coord;
    }

    abstract void setPoints(Integer int_points);

    //Assign points according to letter
    protected void assignPoints() {
        switch (enum_letter) {
            case Α:
            case Ε:
            case Η:
            case Ι:
            case Ν:
            case Ο:
            case Σ:
            case Τ:
                this.int_points = 1;
                break;

            case Β:
            case Ζ:
            case Θ:
            case Φ:
                this.int_points = 8;
                break;

            case Γ:
            case Δ:
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
