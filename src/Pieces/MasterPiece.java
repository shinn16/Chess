package Pieces;

import Logic.Board;

/**
 * MasterPiece
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class MasterPiece{
    
    private int playerID;
    private Coordinate coords;
    private int arrayIndex;
    public MasterPiece(int x, int y, int playerID, int arrayIndex){
        this.coords = new Coordinate(x,y);
        this.playerID = playerID;
        this.arrayIndex = arrayIndex;
    }

    public Coordinate getCoords() {
        return coords;
    }

    public int getPlayerID(){return playerID;}

    public void setCoords(int y, int x){
        coords.setCoords(x, y);
    }

    public int getArrayIndex() { // used for managing pieces in the piece array of the player class.
        return arrayIndex;
    }

    public Coordinate[] getMoves(Board board){
        return new Coordinate[0];
    }

    @Override
    public String toString(){
        return getClass().toString();
    }

}
