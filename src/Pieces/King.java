package Pieces;

import Logic.Board;

import java.util.Arrays;

/**
 * King
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class King extends MasterPiece {
    // value of the piece for AI
    private final int value = 2;

    public King(int x, int y, int playerID, int arrayIndex){
        super(x, y, playerID, arrayIndex);
    }

    public int getValue() {
        return value;
    }

    @Override
    public Coordinate[] getMoves(Board board){
        Coordinate[] moves = new Coordinate[0];
        try {
            if (board.getPiece(super.getCoords().getCoords()[1] + 1, super.getCoords().getCoords()[0]) == null){ // check for empty spot in front of king
                moves = Arrays.copyOf(moves, moves.length + 1);
                moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[1] + 1, super.getCoords().getCoords()[0]);
            }
            else if(board.getPiece(super.getCoords().getCoords()[1] - 1, super.getCoords().getCoords()[0]) == null){ // check to see if the spot is empty behind king
                moves = Arrays.copyOf(moves, moves.length + 1);
                moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[1] - 1, super.getCoords().getCoords()[0]);

            }else if(board.getPiece(super.getCoords().getCoords()[1], super.getCoords().getCoords()[0] + 1) == null){ // check to see if the spot is empty to the right
                moves = Arrays.copyOf(moves, moves.length + 1);
                moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[1], super.getCoords().getCoords()[0] + 1);

            }else if(board.getPiece(super.getCoords().getCoords()[1], super.getCoords().getCoords()[0] -1) == null){ // check to see if the spot is empty t0 the left
                moves = Arrays.copyOf(moves, moves.length + 1);
                moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[1], super.getCoords().getCoords()[0] - 1);

            }
        }catch (IndexOutOfBoundsException e){
            // ingnore the out of bound issue.
        }

        return moves;
    }
}
