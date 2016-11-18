package Pieces;

/**
 * Coordinate
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Coordinate {
    private int[] coords = new int[2];

    public Coordinate(int x, int y){
        this.coords[0] = x;
        this.coords[1] = y;
    }

    // returns the coordinate array
    public int[] getCoords(){
        return coords;
    }

    // sets the coordinate array
    void setCoords(int x, int y) {
        this.coords[0] = x;
        this.coords[1] = y;
    }

    // gets the x coordinate
    public int getX(){return coords[0];}

    // gets the y coordinate
    public int getY(){return coords[1];}

    // for use in AI
    public boolean equals(Coordinate coordinate) {
        return (getX() == coordinate.getX() && getY() == coordinate.getY());
    }

    @Override
    public String toString(){
        return "X: " + coords[0] + " Y: " + coords[1];
    }
}
