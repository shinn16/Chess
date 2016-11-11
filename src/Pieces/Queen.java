package Pieces;

import Logic.Board;

import java.util.Arrays;

/**
 * Queen
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Queen extends MasterPiece {

    // value of the piece for AI
    private final int value = 5;

    public Queen(int x, int y, int playerID, int arrayIndex) {
        super(x, y, playerID, arrayIndex);
    }

    public int getValue() {
        return value;
    }

    @Override
    public  Coordinate[] getMoves(Board board){
        Coordinate[] finalMoves;
        Coordinate[] attacks = new Coordinate[0];
        Coordinate[] moves = new Coordinate[0];

        try{ //check up and left
            for(int i=1;i<=super.getCoords().getY();i++){
                if (board.getPiece(super.getCoords().getY() - i, super.getCoords().getX() - i) == null) {
                    moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                    moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - i, super.getCoords().getY() - i); // added the new coordinate
                }else {
                    if (board.getPiece(super.getCoords().getY() - i, super.getCoords().getX() - i).getPlayerID() != getPlayerID()){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - i, super.getCoords().getY() - i);
                        break;
                    } else break;
                }
            }
        }
        catch(IndexOutOfBoundsException e){}

        try{ //check up and right
            for(int i=1;i<=super.getCoords().getY();i++){
                if (board.getPiece(super.getCoords().getY() - i, super.getCoords().getX() + i) == null) {
                    moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                    moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + i, super.getCoords().getY() - i); // added the new coordinate
                }else {
                    if (board.getPiece(super.getCoords().getY() - i, super.getCoords().getX() + i).getPlayerID() != getPlayerID()){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + i, super.getCoords().getY() - i);
                        break;
                    } else break;
                }
            }
        }
        catch(IndexOutOfBoundsException e){}

        try{ //check down and right
            for(int i=1;i<=5;i++){
                if (board.getPiece(super.getCoords().getY() + i, super.getCoords().getX() + i) == null) {
                    moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                    moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + i, super.getCoords().getY() + i); // added the new coordinate
                }else {
                    if (board.getPiece(super.getCoords().getY() + i, super.getCoords().getX() + i).getPlayerID() != getPlayerID()){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + i, super.getCoords().getY() + i);
                        break;
                    } else break;
                }
            }
        }
        catch(IndexOutOfBoundsException e){}

        try{ //check down and left
            for(int i=1;i<=5;i++){
                if (board.getPiece(super.getCoords().getY() + i, super.getCoords().getX() - i) == null) {
                    moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                    moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - i, super.getCoords().getY() + i); // added the new coordinate
                }else {
                    if (board.getPiece(super.getCoords().getY() + i, super.getCoords().getX() - i).getPlayerID() != getPlayerID()){
                        attacks = Arrays.copyOf(attacks, attacks.length + 1);
                        attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - i, super.getCoords().getY() + i);
                        break;
                    } else break;
                }
            }
        }
        catch(IndexOutOfBoundsException e){}

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
                    moves[moves.length - 1] = new Coordinate(getCoords().getX(), getCoords().getY() + i);
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

        if (attacks.length == 0) finalMoves = moves;
        else finalMoves = attacks;
        return finalMoves;
    }
}

