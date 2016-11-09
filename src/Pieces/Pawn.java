package Pieces;

import Logic.Board;

import java.util.Arrays;

/**
 * Pawn
 *
 * @author Patrick Shinn, Jason Means
 * @version 11/2/16
 */
public class Pawn extends MasterPiece {
    Coordinate coords = new Coordinate(1,1);
    // value of the piece for AI
    private final int value = 1;

    public Pawn(int x, int y, int playerID, int arrayIndex){
        super(x,y,playerID, arrayIndex);
    }

   /* public boolean hasAttack(){
        if (board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+1] != null && playerID != board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+1].getPlayerID()){
            return true;
        }
        else if (board.getBoard()[coords.getCoords()[0]-1][coords.getCoords()[1]+1] != null && playerID != board.getBoard()[coords.getCoords()[0]-1][coords.getCoords()[1]+1].getPlayerID()){
            return true;
        }
        else return false;
    }
    public boolean hasMove(){
        if (board.getBoard()[coords.getCoords()[0]][coords.getCoords()[1]+1] == null){
            return true;
        }
        else return false;
    }
    */
   // comment for push
    public Coordinate[] getMoves(Board board){
        Coordinate[] moves = new Coordinate[0];
        if (getPlayerID() == 0){
            if(board.getPiece(coords.getCoords()[1] + 1, coords.getCoords()[0]) == null){ // check to see if the spot is empty
                moves = Arrays.copyOf(moves, moves.length + 1);
                moves[moves.length - 1] = new Coordinate(coords.getCoords()[0], coords.getCoords()[1] +1); // add the new coordinate to the list
            }else if (board.getPiece(coords.getCoords()[1] + 1, coords.getCoords()[0]).getPlayerID() != getPlayerID()){ //
                // // TODO: 11/9/16 Attack stuff
            }

        }
        return moves;
    }

    public int getValue() {
        return value;
    }
}
