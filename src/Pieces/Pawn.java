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
    // value of the piece for AI
    private final int value = 1;

    public Pawn(int x, int y, int playerID, int arrayIndex){
        super(x,y,playerID, arrayIndex);
    }


    @Override
    public Coordinate[] getMoves(Board board){
        Coordinate[] moves = new Coordinate[0];
        if (getPlayerID() == 0){
            if(board.getPiece(super.getCoords().getCoords()[1] + 1, super.getCoords().getCoords()[0]) == null){ // check to see if the spot is empty
                moves = Arrays.copyOf(moves, moves.length + 1);
                moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[0], super.getCoords().getCoords()[1] +1); // add the new coordinate to the list
            }else if (board.getPiece(super.getCoords().getCoords()[1] + 1, super.getCoords().getCoords()[0]).getPlayerID() != getPlayerID()){

            }

        }else {
            if(board.getPiece(super.getCoords().getCoords()[1] - 1, super.getCoords().getCoords()[0]) == null){ // check to see if the spot is empty
                moves = Arrays.copyOf(moves, moves.length + 1);
                moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[0], super.getCoords().getCoords()[1] - 1); // add the new coordinate to the list

            }
        }
        return moves;
    }

    public int getValue() {
        return value;
    }
}
