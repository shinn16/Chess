package Pieces;

/**
 * MasterPiece
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class MasterPiece implements Piece{
    public Logic.Player player1;
    public Logic.Player player2;
    public int playerID;
    public MasterPiece(int x, int y, int playerID){
        Coordinate coords = new Coordinate(x,y);
        this.playerID = playerID;
    }
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

    public int getPlayerID(){return playerID;}
}
