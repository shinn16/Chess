package Pieces;

/**
 * Knight
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Knight extends MasterPiece {
    Logic.Board board = new Logic.Board(player1, player2);
    Coordinate coords = new Coordinate(1,1);
    public Knight(int x, int y, int playerID){
        super(x,y,playerID);
    }
    public boolean hasAttack(){
        if (board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+3] != null && playerID != board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+3].getPlayerID()){
            return true;
        }
        else return false;
    }
    //public boolean hasMove(){}
    //public Coordinate makeMove(){}
}
