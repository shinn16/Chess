package Pieces;

import Logic.Board;

import java.util.Arrays;

/**
 * Knight
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Knight extends MasterPiece {
    // value of the piece for AI
    private final int value = 3;

    public Knight(int x, int y, int playerID, int arrayIndex) {
        super(x, y, playerID, arrayIndex);
    }

    @Override
    public int getValue() {
        return value;
    }


    @Override
    public MasterPiece copyOf(){
        return new Knight(getCoords().getX(), getCoords().getY(), getPlayerID(), getArrayIndex());
    }

    @Override
    public Coordinate[] getMoves(Board board) {
        Coordinate[] finalMoves;
        Coordinate[] attacks = new Coordinate[0];
        Coordinate[] moves = new Coordinate[0];

        try { // checking above and to the right
            if (board.getPiece(super.getCoords().getY() - 2, super.getCoords().getX() + 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() - 2); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() - 2, super.getCoords().getX() + 1) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() - 2, super.getCoords().getX() + 1).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() - 2); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking above and to the left
            if (board.getPiece(super.getCoords().getY() - 2, super.getCoords().getX() - 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() - 2); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() - 2, super.getCoords().getX() - 1) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() - 2, super.getCoords().getX() - 1).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() - 2); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking below and to the right
            if (board.getPiece(super.getCoords().getY() + 2, super.getCoords().getX() + 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() + 2); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() + 2, super.getCoords().getX() + 1) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() + 2, super.getCoords().getX() + 1).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + 1, super.getCoords().getY() + 2); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking below and to the left
            if (board.getPiece(super.getCoords().getY() + 2, super.getCoords().getX() - 1) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() + 2); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() + 2, super.getCoords().getX() - 1) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() + 2, super.getCoords().getX() - 1).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - 1, super.getCoords().getY() + 2); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking above and to the right
            if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() + 2) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + 2, super.getCoords().getY() - 1); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() + 2) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() + 2).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + 2, super.getCoords().getY() - 1); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking above and to the left
            if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() - 2) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - 2, super.getCoords().getY() - 1); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() - 2) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() - 1, super.getCoords().getX() - 2).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - 2, super.getCoords().getY() - 1); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking below and to the right
            if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() + 2) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() + 2, super.getCoords().getY() + 1); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() + 2) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() + 2).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() + 2, super.getCoords().getY() + 1); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }

        try { // checking below and to the left
            if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() - 2) == null) {
                moves = Arrays.copyOf(moves, moves.length + 1); // expand the moves array
                moves[moves.length - 1] = new Coordinate(super.getCoords().getX() - 2, super.getCoords().getY() + 1); // added the new coordinate
            } else if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() - 2) != null) { // if there is a piece at this position
                if (board.getPiece(super.getCoords().getY() + 1, super.getCoords().getX() - 2).getPlayerID() != getPlayerID()) { // if it is an enemy piece
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = new Coordinate(super.getCoords().getX() - 2, super.getCoords().getY() + 1); // add it to the attack array.
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // ignore the error
        }
        if (attacks.length == 0) finalMoves = moves;
        else finalMoves = attacks;
        return finalMoves;
    }
}


