package Pieces;

/**
 * Pawn
 *
 * @author Patrick Shinn, Jason Means
 * @version 11/2/16
 */
public class Pawn extends MasterPiece {
    Coordinate coords = new Coordinate(1,1);
    // value of the piece for AI
    private final int value = 1;

    public Pawn(int x, int y, int playerID, int arrayIndex){
        super(x,y,playerID, arrayIndex);
    }

   /* public boolean hasAttack(){
        if (board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+1] != null && playerID != board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+1].getPlayerID()){
            return true;
        }
        else if (board.getBoard()[coords.getCoords()[0]-1][coords.getCoords()[1]+1] != null && playerID != board.getBoard()[coords.getCoords()[0]-1][coords.getCoords()[1]+1].getPlayerID()){
            return true;
        }
        else return false;
    }
    public boolean hasMove(){
        if (board.getBoard()[coords.getCoords()[0]][coords.getCoords()[1]+1] == null){
            return true;
        }
        else return false;
    }
    */
    public Coordinate makeMove(){
        if (hasAttack()){
            //TODO 11/7/16 Figure out how to allow for the choice of different moves.
        }
        else if (hasMove()){
            coords.setCoords(coords.getCoords()[0], coords.getCoords()[1]+1);
        }
        return coords;
    }

    public int getValue() {
        return value;
    }
}
