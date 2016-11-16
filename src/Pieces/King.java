package Pieces;

import Logic.Board;

import java.util.Arrays;

/**
 * King
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class King extends MasterPiece {
    // value of the piece for AI
    private final int value = 2;

    public King(int x, int y, int playerID, int arrayIndex){
        super(x, y, playerID, arrayIndex);
    }

    public int getValue() {
        return value;
    }


    @Override
    public MasterPiece copyOf(){
        return new King(getCoords().getX(), getCoords().getY(), getPlayerID(), getArrayIndex());
    }

    @Override
    public Coordinate[] getMoves(Board board) {
        Coordinate[] finalMoves;
        Coordinate[] attacks = new Coordinate[0];
        Coordinate[] moves = new Coordinate[0];

        try { // checking above the king
            if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX()) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX(), super.getCoords().getY() + 1); // added the new coordinate
            }else if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX()) != null){ // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX()).getPlayerID() != getPlayerID()){ // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX(), super.getCoords().getY() + 1); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking below the king
            if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX()) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX(), super.getCoords().getY() - 1); // added the new coordinate
            }else if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX()) != null){
                if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX()).getPlayerID() != getPlayerID()){
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX(), super.getCoords().getY() - 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking to the right
            if (board.getPiece(super.getCoords().getY(), super.getCoords().getX() + 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY()); // added the new coordinate
            }else if (board.getPiece(super.getCoords().getY(), super.getCoords().getX() + 1) != null){
                if (board.getPiece(super.getCoords().getY(), super.getCoords().getX() + 1).getPlayerID() != getPlayerID()){
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY());
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Ignore the error
        }

        try { // checking to the left
            if (board.getPiece(super.getCoords().getY(), super.getCoords().getX() - 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY()); // added the new coordinate
            }else{
                if (board.getPiece(super.getCoords().getY(), super.getCoords().getX() - 1).getPlayerID() != getPlayerID()){
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY());
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Ignore the error
        }

        try { // checking diagonal upper left
            if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() - 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() + 1); // added the new coordinate
            }else {
                if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() - 1).getPlayerID() != getPlayerID()){
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() + 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Ignore the error
        }

        try { // checking diagonal bottom left
            if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() - 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() - 1); // added the new coordinate
            }else {
                if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() - 1).getPlayerID() != getPlayerID()){
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() - 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Ignore the error
        }

        try { // checking diagonal upper right
            if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() + 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() + 1); // added the new coordinate
            }else {
                if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() + 1).getPlayerID() != getPlayerID()){
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() + 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Ignore the error
        }

        try { // checking diagonal lower right
            if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() + 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() - 1); // added the new coordinate
            }else {
                if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() + 1).getPlayerID() != getPlayerID()){
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() - 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Ignore the error
        }

        if (attacks.length == 0) finalMoves = moves;
        else finalMoves = attacks;
        return finalMoves;
    }
}
