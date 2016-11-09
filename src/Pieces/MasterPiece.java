package Pieces;

import Logic.Board;

/**
 * MasterPiece
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class MasterPiece{
    
    private Board board; //// TODO: 11/7/16 each peice will need to somehow deal with the board. 
    private int playerID;
    private Coordinate coords;
    private int arrayIndex;
    public MasterPiece(int x, int y, int playerID, int arrayIndex){
        this.coords = new Coordinate(x,y);
        this.playerID = playerID;
        this.arrayIndex = arrayIndex;
    }

    public boolean hasAttack() {
        return false;
    }

    public boolean hasMove() {
        hasAttack(); // has move will enforce the attack method.
        return false;
    }

    public Coordinate getCoords() {
        return coords;
    }

    public int getPlayerID(){return playerID;}

    public int getArrayIndex() { // used for managing pieces in the piece array of the player class.
        return arrayIndex;
    }


    @Override
    public String toString(){
        return getClass().toString();
    }

}
