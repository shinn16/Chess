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

    public boolean hasAttack(Board board){
        boolean attack = false; // assume there is not an attack to start with
        for (Coordinate move: getMoves(board)){ // for the moves the piece has
            if (getMoves(board).length > 0) { // if  the piece has a move in the move set
                if (board.getPiece(move.getY(), move.getX()) != null) attack = true; // if a piece is in the move set, it must belong to an enemy.
                break; // no need to check any further
            }
        }
        return attack;
    }

    public MasterPiece copyOf(){
        return new MasterPiece(getCoords().getX(), getCoords().getY(), getPlayerID(), getArrayIndex());
    }

    @Override
    public String toString(){
        return getClass().toString();
    }

}
