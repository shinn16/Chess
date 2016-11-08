package Pieces;

/**
 * Knight
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Knight extends MasterPiece {

    public Knight(int x, int y, int playerID, int arrayIndex){
        super(x,y,playerID, arrayIndex);
    }
    /*public boolean hasAttack(){
        if (board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+3] != null && playerID != board.getBoard()[coords.getCoords()[0]+1][coords.getCoords()[1]+3].getPlayerID()){
            return true;
        }
        else return false;
    }
    */
}


