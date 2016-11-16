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
    //// TODO: 11/14/16 Make this class
    private Player player;
    private Board board;
    private Coordinate[] attacks = new Coordinate[0];
    private MasterPiece[] bestAttacks = new MasterPiece[0];


    public AI(Player player, Board board) {
        this.board = board;
        this.player = player;
        int lowestVal = 6;
        for (MasterPiece piece : player.getAttacks(board)) {
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
        }

    }

    // this method should play for the AI
    public SpecialCoord play(Board board) {

        if (bestAttacks.length > 0) { // we have an attack! So no real thinking is needed
            return new SpecialCoord(bestAttacks[0], attacks[0].getX(), attacks[0].getY());
        }
        else return null;

    }
}
