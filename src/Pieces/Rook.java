package Pieces;

/**
 * Rook
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Rook extends MasterPiece {

    // value of the piece for AI
    private final int value = 4;

    public Rook(int x, int y, int playerID, int arrayIndex){
        super(x,y,playerID, arrayIndex);
    }

    public int getValue() {
        return value;
    }
}
