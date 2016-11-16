package Logic;

import Pieces.Coordinate;
import Pieces.MasterPiece;

/**
 * SpecialCoord
 *
 * @author Patrick Shinn
 * @version 11/16/16
 */
public class SpecialCoord extends Coordinate{

    private MasterPiece piece;

    public SpecialCoord(MasterPiece piece, int x, int y){
        super(x, y);
        this.piece = piece;
    }

    public MasterPiece getPiece() {
        return piece;
    }
}
