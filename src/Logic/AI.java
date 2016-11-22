package Logic;

import Pieces.Coordinate;
import Pieces.MasterPiece;
import java.util.Arrays;

/**
 * AI
 *
 * @author Patrick Shinn
 * @version 10/28/16
 */
public class AI {
    private Coordinate[] moves = new Coordinate[0];
    private MasterPiece[] bestMoves = new MasterPiece[0];
    private Coordinate[] attacks = new Coordinate[0];
    private MasterPiece[] bestAttacks = new MasterPiece[0];
    private Player player;


    public AI(Player player, Board board) {
        this.player = player;
        int lowestVal = 6;
        for (MasterPiece piece : player.getAttacks(board)) { // if we have attacks, this will find them.
            for (Coordinate attack : piece.getMoves(board)) {
                if (board.getPiece(attack.getY(), attack.getX()).getValue() < lowestVal) { // check the piece value against our lowest, if lower, its the new lowest
                    lowestVal = board.getPiece(attack.getY(), attack.getX()).getValue();
                    bestAttacks = new MasterPiece[0]; // if we have a new lower value, dump the array.
                    attacks = new Coordinate[0];
                    bestAttacks = Arrays.copyOf(bestAttacks, 1); // put the new piece at the front.
                    bestAttacks[0] = piece;
                    attacks = Arrays.copyOf(attacks, 1);
                    attacks[0] = attack;
                } else if (board.getPiece(attack.getY(), attack.getX()).getValue() == lowestVal) { // if the value is equal
                    bestAttacks = Arrays.copyOf(bestAttacks, bestAttacks.length + 1); // add it to the array
                    bestAttacks[bestAttacks.length - 1] = piece;
                    attacks = Arrays.copyOf(attacks, attacks.length + 1);
                    attacks[attacks.length - 1] = attack;

                }
                // otherwise we ignore the piece.
            }
        }if (bestAttacks.length == 0){ // if we have no attacks
            lowestVal = 0; // reassign lowestVal to 0 so we can use it to find our highest value piece that can move.
            for (MasterPiece piece: player.getMoves(board)){
                if (piece.getValue() > lowestVal){ // if the current piece is higher than the old highest, replace the value
                    lowestVal = piece.getValue();
                    moves = new Coordinate[0]; // dump the old moves and pieces
                    bestMoves = new MasterPiece[0];
                    for (Coordinate move: piece.getMoves(board)){
                        moves = Arrays.copyOf(moves, moves.length + 1);
                        moves[moves.length - 1] = move;
                        bestMoves = Arrays.copyOf(bestMoves, bestMoves.length + 1);
                        bestMoves[bestMoves.length - 1] = piece;
                    }
                }else if (piece.getValue() == lowestVal){
                    for (Coordinate move: piece.getMoves(board)){
                        bestMoves = Arrays.copyOf(bestMoves, bestMoves.length + 1);
                        bestMoves[bestMoves.length - 1] = piece;
                        moves = Arrays.copyOf(moves, moves.length + 1);
                        moves[moves.length - 1] = move;
                    }
                }
            }
        }

    }

    // this method should play for the AI
    public SpecialCoord play(Board board) {
        // this method will determine which move to make with the given move set information.

        //---------------------------------Attacking--------------------------------------------
        if (bestAttacks.length > 0) { // we have an attack! So no real thinking is needed
            int highPiece = 0; // start at zero so we can find our highest value piece
            int indexToUse = 0;
            for (MasterPiece piece: bestAttacks){
                if (piece.getValue() > highPiece){
                    highPiece = piece.getValue();
                }else indexToUse ++;
            }
            return new SpecialCoord(bestAttacks[indexToUse], attacks[indexToUse].getX(), attacks[indexToUse].getY());
        }

        //-----------------------------------Moving--------------------------------------------
        else if (bestMoves.length > 0) { // if we don't have an attack
            Coordinate[] enemyMoves = new Coordinate[0]; // stores the enemy moves
            int highPiece = 0;
            int indexToUse = 0;
            boolean pieceFound = false;
            int numberToBreakAt = 6;


            // extracting enemy move set
            for (MasterPiece[] row : board.getBoard()) {
                for (MasterPiece piece : row) {
                    if (piece != null){
                        if (piece.getPlayerID() != player.getPlayerNumber()) {
                            for (Coordinate move : piece.getMoves(board)) {
                                if (piece.toString().contains("Pawn")) {// if the piece is a pawn, we have a different move set for attacks
                                    if (player.getPlayerNumber() == 0) {
                                        Coordinate move1 = new Coordinate(move.getX() + 1, move.getY() + 1);
                                        enemyMoves = Arrays.copyOf(enemyMoves, enemyMoves.length + 1);
                                        enemyMoves[enemyMoves.length - 1] = move1;
                                        Coordinate move2 = new Coordinate(move.getX() - 1, move.getY() + 1);
                                        enemyMoves = Arrays.copyOf(enemyMoves, enemyMoves.length + 1);
                                        enemyMoves[enemyMoves.length - 1] = move2;
                                    } else { // if it is the white player, do the same.
                                        Coordinate move1 = new Coordinate(move.getX() + 1, move.getY() - 1);
                                        Coordinate move2 = new Coordinate(move.getX() - 1, move.getY() - 1);
                                        enemyMoves = Arrays.copyOf(enemyMoves, enemyMoves.length + 1);
                                        enemyMoves[enemyMoves.length - 1] = move1;
                                        enemyMoves = Arrays.copyOf(enemyMoves, enemyMoves.length + 1);
                                        enemyMoves[enemyMoves.length - 1] = move2;
                                    }
                                } else {
                                    enemyMoves = Arrays.copyOf(enemyMoves, enemyMoves.length + 1);
                                    enemyMoves[enemyMoves.length - 1] = move;
                                }
                            }
                        }
                    }
                }
            }

            while (!pieceFound){ // while we have not selected a piece to move with
                indexToUse = 0;
                for (MasterPiece piece: bestMoves){ // checking for our best piece to move.
                    if (indexToUse == numberToBreakAt) {
                        if (numberToBreakAt == 0) pieceFound = true; // if we get through all of our pieces and find nothing, just take the first move of the first piece.
                        break; // if we have already checked this piece, break
                    }
                    if (piece.getValue() > highPiece){
                        highPiece = piece.getValue();
                    }else indexToUse ++;
                }
                for (Coordinate enemyMove: enemyMoves){ // now we check the enemy move set
                    if (enemyMove.equals(moves[indexToUse])) pieceFound = true; // if our piece can move there, then do it.
                }
                if (!pieceFound) numberToBreakAt = indexToUse; // if the current piece has no moves in enemy move set, then try again using a different piece.
            }
            return new SpecialCoord(bestMoves[indexToUse], moves[indexToUse].getX(), moves[indexToUse].getY());
        }else return null;

    }
}
