package Pieces;

import Logic.Board;

import java.util.Arrays;

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

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public MasterPiece copyOf(){
        return new Rook(getCoords().getX(), getCoords().getY(), getPlayerID(), getArrayIndex());
    }

    public Coordinate[] getMoves(Board board){
        Coordinate[] finalMoves;
        Coordinate[] attacks = new Coordinate[0];
        Coordinate[] moves = new Coordinate[0];

        try {
            for (int i = 1; i < 5; i ++){ // checking vertical down first
                if (board.getPiece((getCoords().getY() + i), getCoords().getX()) == null){ // if the spot is empty
                    moves = Arrays.copyOf(moves, moves.length + 1);
                    moves[moves.length - 1] = new Coordinate(getCoords().getX(), getCoords().getY() + i);
                }else { // if the spot contains an enemy
                    if (board.getPiece((getCoords().getY() + i), getCoords().getX()).getPlayerID() != getPlayerID() ){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(getCoords().getX(), getCoords().getY() + i);
                        break; // if we hit a piece to attack, break
                    } else break; // we have hit one of our own pieces, so break
                }
            }
        }catch (IndexOutOfBoundsException e) { //ignore
        }

        try {
            for (int i = 1; i < 5; i ++){ // checking vertical up
                if (board.getPiece((getCoords().getY() - i), getCoords().getX()) == null){ // if the spot is empty
                    moves = Arrays.copyOf(moves, moves.length + 1);
                    moves[moves.length - 1] = new Coordinate(getCoords().getX(), getCoords().getY() - i);
                }else { // if the spot contains an enemy
                    if (board.getPiece((getCoords().getY() - i), getCoords().getX()).getPlayerID() != getPlayerID() ){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(getCoords().getX(), getCoords().getY() - i);
                        break; // if we hit a piece to attack, break
                    } else break; // we have hit one of our own pieces, so break
                }
            }
        }catch (IndexOutOfBoundsException e) { //ignore
        }

        try {
            for (int i = 1; i < 5; i ++){ // checking right
                if (board.getPiece((getCoords().getY()), getCoords().getX() + i) == null){ // if the spot is empty
                    moves = Arrays.copyOf(moves, moves.length + 1);
                    moves[moves.length - 1] = new Coordinate(getCoords().getX() + i, getCoords().getY());
                }else { // if the spot contains an enemy
                    if (board.getPiece((getCoords().getY()), getCoords().getX() + i).getPlayerID() != getPlayerID() ){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(getCoords().getX() + i, getCoords().getY());
                        break; // if we hit a piece to attack, break
                    } else break; // we have hit one of our own pieces, so break
                }
            }
        }catch (IndexOutOfBoundsException e) { //ignore
        }

        try {
            for (int i = 1; i < 5; i ++){ // checking left
                if (board.getPiece((getCoords().getY()), getCoords().getX() - i) == null){ // if the spot is empty
                    moves = Arrays.copyOf(moves, moves.length + 1);
                    moves[moves.length - 1] = new Coordinate(getCoords().getX() - i, getCoords().getY());
                }else { // if the spot contains an enemy
                    if (board.getPiece((getCoords().getY()), getCoords().getX() - i).getPlayerID() != getPlayerID() ){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(getCoords().getX() - i, getCoords().getY());
                        break; // if we hit a piece to attack, break
                    } else break; // we have hit one of our own pieces, so break
                }
            }
        }catch (IndexOutOfBoundsException e) { //ignore
        }


        if (attacks.length == 0) finalMoves = moves;
        else finalMoves = attacks;
        return finalMoves;
    }


}
