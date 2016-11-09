package Pieces;

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
}
