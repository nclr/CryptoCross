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
    //protected Alphabet enum_letter; //The character/letter it represents
    protected Integer int_x_coord; //The x coordinate of the letter on the board
    protected Integer int_y_coord; //The y coordinate of the letter on the board
    protected Boolean tf_belongsToWord; //Flag: belongs to a marked word
    protected Integer int_points; //Points value of the letter
    protected Color col_color; //Color group of letter, each color represents a different
    //points value

    //Constructor
    public Letter(Character ch_letter) {
        this.ch_letter = ch_letter;
    }

    public Letter(Character ch_letter, Integer int_x_coord, Integer int_y_coord) {
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

    public void setColor(Color col_color) {
        this.col_color = col_color;
    }

    //Assign points according to letter
    protected void assignPoints() throws UknownCharacterException {
        if (ch_letter == 'Α') {
            int_points = 1;
        } else if (ch_letter == 'Β') {
            int_points = 8;
        } else if (ch_letter == 'Γ') {
            int_points = 4;
        } else if (ch_letter == 'Δ') {
            int_points = 4;
        } else if (ch_letter == 'Ε') {
            int_points = 1;
        } else if (ch_letter == 'Ζ') {
            int_points = 8;
        } else if (ch_letter == 'Η') {
            int_points = 1;
        } else if (ch_letter == 'Θ') {
            int_points = 8;
        } else if (ch_letter == 'Ι') {
            int_points = 1;
        } else if (ch_letter == 'Κ') {
            int_points = 2;
        } else if (ch_letter == 'Λ') {
            int_points = 3;
        } else if (ch_letter == 'Μ') {
            int_points = 3;
        } else if (ch_letter == 'Ν') {
            int_points = 1;
        } else if (ch_letter == 'Ξ') {
            int_points = 10;
        } else if (ch_letter == 'Ο') {
            int_points = 1;
        } else if (ch_letter == 'Π') {
            int_points = 2;
        } else if (ch_letter == 'Ρ') {
            int_points = 2;
        } else if (ch_letter == 'Σ') {
            int_points = 1;
        } else if (ch_letter == 'Τ') {
            int_points = 1;
        } else if (ch_letter == 'Υ') {
            int_points = 2;
        } else if (ch_letter == 'Φ') {
            int_points = 8;
        } else if (ch_letter == 'Χ') {
            int_points = 10;
        } else if (ch_letter == 'Ψ') {
            int_points = 10;
        } else if (ch_letter == 'Ω') {
            int_points = 3;
        } else if (ch_letter == '?') {
            int_points = 1;
        }

    }

    public void setCoords(Integer int_x_coord, Integer int_y_coord) {
        this.int_x_coord = int_x_coord;
        this.int_y_coord = int_y_coord;
    }

    @Override
    public String toString() {
        return "Letter:" + "Character: " + ch_letter + ", Points: " + int_points + ", xCoord: " + int_x_coord + ", yCoord: " + int_y_coord;
    }
}
