package Pieces;

import javafx.scene.image.Image;

/**
 * Bishop
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Bishop extends MasterPiece{
    private final int value = 4;
    public Bishop(int x, int y, int playerID, int arrayIndex){
        super(x, y, playerID, arrayIndex);
    }

    public int getValue() {
        return value;
    }
}

