package Pieces;

import Logic.Board;

import java.util.Arrays;

/**
 * Pawn
 *
 * @author Patrick Shinn, Jason Means
 * @version 11/2/16
 */
public class Pawn extends MasterPiece {
    // value of the piece for AI
    private final int value = 1;

    public Pawn(int x, int y, int playerID, int arrayIndex){
        super(x,y,playerID, arrayIndex);
    }

    @Override
    public Coordinate[] getMoves(Board board){
        Coordinate[] finalMoves;
        Coordinate[] attacks = new Coordinate[0];
        Coordinate[] moves = new Coordinate[0];

        // checking for moves
        if (getPlayerID() == 0){
            try {
                if(board.getPiece(super.getCoords().getCoords()[1] + 1, super.getCoords().getCoords()[0]) == null){ // check to see if the spot is empty
                    moves = Arrays.copyOf(moves, moves.length + 1);
                    moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[0], super.getCoords().getCoords()[1] +1); // add the new coordinate to the list
                }
            }catch (IndexOutOfBoundsException e){
                // ignore
            }

        }else {
            try {
                if(board.getPiece(super.getCoords().getCoords()[1] - 1, super.getCoords().getCoords()[0]) == null){ // check to see if the spot is empty
                    moves = Arrays.copyOf(moves, moves.length + 1);
                    moves[moves.length - 1] = new Coordinate(super.getCoords().getCoords()[0], super.getCoords().getCoords()[1] - 1); // add the new coordinate to the list
                }
            }catch (IndexOutOfBoundsException e){
                //ignore
            }

        }
        // checking for attacks
        if (getPlayerID() == 0){
            try {
                // if black use black move set
                if (board.getPiece((super.getCoords().getY() + 1), super.getCoords().getX() +1) != null){
                    if (board.getPiece((super.getCoords().getY() + 1), super.getCoords().getX() + 1).getPlayerID() != getPlayerID()) {
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate((super.getCoords().getX() + 1), super.getCoords().getY() + 1);
                    }
                }
                if (board.getPiece((super.getCoords().getY() + 1), super.getCoords().getX() - 1) != null){
                     if (board.getPiece((super.getCoords().getY() + 1), super.getCoords().getX() - 1).getPlayerID() != getPlayerID()) {
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate((super.getCoords().getX() - 1), super.getCoords().getY() + 1);
                    }
                }
            }catch (IndexOutOfBoundsException e){
                // ignore
            }

        } else{ // use white move set
            try {
                if (board.getPiece((super.getCoords().getY() - 1), super.getCoords().getX() +1) != null){
                    if (board.getPiece((super.getCoords().getY() - 1), super.getCoords().getX() +1).getPlayerID() != getPlayerID() ){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate((super.getCoords().getX() + 1), super.getCoords().getY() - 1);
                    }
                }
                if (board.getPiece((super.getCoords().getY() - 1), super.getCoords().getX() - 1) != null){
                      if (board.getPiece((super.getCoords().getY() - 1), super.getCoords().getX() - 1).getPlayerID() != getPlayerID() ){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate((super.getCoords().getX() - 1), super.getCoords().getY() - 1);
                    }
                }
            }catch (IndexOutOfBoundsException e){
                // ignore
            }
        }


        // if there are no attacks, return moves.
        if (attacks.length ==0) finalMoves = moves;
        else finalMoves = attacks;

        return finalMoves;
    }

    public int getValue() {
        return value;
    }
}
