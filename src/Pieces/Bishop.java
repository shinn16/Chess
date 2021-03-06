package Pieces;

import Logic.Board;
import javafx.scene.image.Image;
import java.math.*;
import java.util.Arrays;

/**
 * Bishop
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Bishop extends MasterPiece{
    // for AI
    private final int value = 4;


    public Bishop(int x, int y, int playerID, int arrayIndex){
        super(x, y, playerID, arrayIndex);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public MasterPiece copyOf(){
        return new Bishop(getCoords().getX(), getCoords().getY(), getPlayerID(), getArrayIndex());
    }

    @Override
    public Coordinate[] getMoves(Board board){
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

        if (attacks.length == 0) finalMoves = moves;
        else finalMoves = attacks;
        return finalMoves;
    }
}

