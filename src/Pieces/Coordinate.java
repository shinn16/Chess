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

    public void setCoords(int x, int y) {
        this.coords[0] = x;
        this.coords[1] = y;
    }

    public int getX(){return coords[0];}

    public int getY(){return coords[1];}

    @Override
    public String toString(){
        return "X: " + coords[0] + " Y: " + coords[1];
    }
}
