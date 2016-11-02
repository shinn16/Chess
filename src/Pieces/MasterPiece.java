package Pieces;

/**
 * MasterPiece
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class MasterPiece implements Piece{
    @Override
    public boolean hasAttack() {
        return false;
    }

    @Override
    public boolean hasMove() {
        return false;
    }

    @Override
    public Coordinate makeMove() {
        return new Coordinate(1,1);
    }
}
