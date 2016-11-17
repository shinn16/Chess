package Logic;

import Pieces.Coordinate;
import Pieces.MasterPiece;
import java.util.Arrays;

/**
 * AI
 *
 * @author Patrick Shinn
 * @version 10/28/16
 */
public class AI {
    private Coordinate[] moves = new Coordinate[0];
    private MasterPiece[] bestMoves = new MasterPiece[0];
    private Coordinate[] attacks = new Coordinate[0];
    private MasterPiece[] bestAttacks = new MasterPiece[0];


    public AI(Player player, Board board) {
        int lowestVal = 6;
        for (MasterPiece piece : player.getAttacks(board)) { // if we have attacks, this will find them.
            for (Coordinate attack : piece.getMoves(board)) {
                if (board.getPiece(attack.getY(), attack.getX()).getValue() < lowestVal) { // check the piece value against our lowest, if lower, its the new lowest
                    lowestVal = board.getPiece(attack.getY(), attack.getX()).getValue();
                    bestAttacks = new MasterPiece[0]; // if we have a new lower value, dump the array.
                    attacks = new Coordinate[0];
                    bestAttacks = Arrays.copyOf(bestAttacks, 1); // put the new piece at the front.
                    bestAttacks[0] = piece;
                    attacks = Arrays.copyOf(attacks, 1);
                    attacks[0] = attack;
                } else if (board.getPiece(attack.getY(), attack.getX()).getValue() == lowestVal) { // if the value is equal
                    bestAttacks = Arrays.copyOf(bestAttacks, bestAttacks.length + 1); // add it to the array
                    bestAttacks[bestAttacks.length - 1] = piece;
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = attack;

                }
                // otherwise we ignore the piece.
            }
        }if (bestAttacks.length == 0){ // if we have no attacks
            lowestVal = 0; // reassign lowestVal to 0 so we can use it to find our highest value piece that can move.
            for (MasterPiece piece: player.getMoves(board)){
                if (piece.getValue() > lowestVal){ // if the current piece is higher than the old highest, replace the value
                    lowestVal = piece.getValue();
                    moves = new Coordinate[0]; // dump the old moves and pieces
                    bestMoves = new MasterPiece[1];
                    bestMoves[0] = piece;
                    for (Coordinate move: piece.getMoves(board)){
                        moves = Arrays.copyOf(moves, moves.length + 1);
                        moves[moves.length - 1] = move;
                    }
                }else if (piece.getValue() == lowestVal){
                    bestMoves = Arrays.copyOf(bestMoves, bestMoves.length + 1);
                    for (Coordinate move: piece.getMoves(board)){
                        moves = Arrays.copyOf(moves, moves.length + 1);
                        moves[moves.length - 1] = move;
                    }
                }
            }
        }

    }

    // this method should play for the AI
    public SpecialCoord play(Board board) {
        // this method will determine which move to make with the given move set information.

        if (bestAttacks.length > 0) { // we have an attack! So no real thinking is needed
            return new SpecialCoord(bestAttacks[0], attacks[0].getX(), attacks[0].getY());
        }
        else if (bestMoves.length > 0){
            return new SpecialCoord(bestMoves[0], moves[0].getX(), moves[0].getY());
        }else return null;

    }
}
