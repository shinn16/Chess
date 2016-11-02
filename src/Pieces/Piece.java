package Pieces;

/**
 * Piece
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public interface Piece {
    boolean hasMove();
    boolean hasAttack();
    void makeMove();

}
