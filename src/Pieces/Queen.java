package Pieces;

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
}
