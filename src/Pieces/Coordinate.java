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

    public int[] getCoords(){
        return coords;
    }
}
