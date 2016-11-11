package Pieces;

import Logic.Board;

import java.util.Arrays;

/**
 * Queen
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Queen extends MasterPiece {

    // value of the piece for AI
    private final int value = 5;

    public Queen(int x, int y, int playerID, int arrayIndex) {
        super(x, y, playerID, arrayIndex);
    }

    public int getValue() {
        return value;
    }

    @Override
    public  Coordinate[] getMoves(Board board){
        Coordinate[] moves = new Coordinate[0];
        int[] checkValues = {super.getCoords().getX(), super.getCoords().getY()};
        Boolean done = false;
        // while we can, we are going to check straight ahead of us.
        while (!done){
            try{
                if (board.getPiece(checkValues[1] + 1, checkValues[0]) == null){
                    moves = Arrays.copyOf(moves, moves.length + 1);
                    moves[moves.length - 1] = new Coordinate(checkValues[0], checkValues[1]);
                    checkValues[1] += 1;
                }else {
                    checkValues[0] = super.getCoords().getX();
                    checkValues[1] = super.getCoords().getY();
                    break;
                }
            }catch (IndexOutOfBoundsException e){
                break;
            }

            // while we can, we are going to check straight behind us of us.
            while (!done) {
                try {
                    if (board.getPiece(checkValues[1] - 1, checkValues[0]) == null) {
                        moves = Arrays.copyOf(moves, moves.length + 1);
                        moves[moves.length - 1] = new Coordinate(checkValues[0], checkValues[1]);
                        checkValues[1] -= 1;
                    } else {
                        checkValues[0] = super.getCoords().getX();
                        checkValues[1] = super.getCoords().getY();
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }

        }

        return moves;
    }

}
