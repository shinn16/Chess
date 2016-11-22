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
            MasterPiece[] pieces = new MasterPiece[0]; // this will hold our pieces
            for (MasterPiece[] row: board.getBoard()){
                for (MasterPiece piece: row){
                    if (piece != null){
                        if (piece.getPlayerID() != player.getPlayerNumber()){ // if the pieces is ours
                            pieces = Arrays.copyOf(pieces, pieces.length + 1); // add it to the array.
                            pieces[pieces.length - 1] = piece;
                        }
                    }
                }
            }
            for (MasterPiece piece: pieces){
                if (piece.getMoves(board).length > 0){ // if the piece has moves
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
    }

    // this method should play for the AI
    public SpecialCoord play(Board board) {
        // this method will determine which move to make with the given move set information.

        //---------------------------------Attacking--------------------------------------------
        if (bestAttacks.length > 0) { // we have an attack! So no real thinking is needed
            int highPiece = 0; // start at zero so we can find our highest value piece
            int indexToUse = 0;
            int counter = 0;
            for (MasterPiece piece: bestAttacks){ // for every piece that has an attack
                if (piece.getValue() > highPiece){ // if it is valued higher than the current high piece
                    highPiece = piece.getValue(); // we set the new high value to be this piece
                    indexToUse = counter; // we set the index to be used to this piece.
                    counter ++;
                }else if (piece.getValue() == highPiece) counter ++;
            }
            return new SpecialCoord(bestAttacks[indexToUse], attacks[indexToUse].getX(), attacks[indexToUse].getY());
        }

        //-----------------------------------Moving--------------------------------------------
        else if (bestMoves.length > 0) { // if we don't have an attack
            Coordinate[] enemyMoves = new Coordinate[0]; // stores the enemy moves
            MasterPiece[] enemyPieces = new MasterPiece[0];
            int highPiece = 0;
            int indexToUse = 0;


            // getting the enemy
            for (MasterPiece[] row: board.getBoard()){ // for every piece on the board, find enemy pieces.
                for (MasterPiece piece: row){
                    if (piece != null){
                        if (piece.getPlayerID() == this.player.getPlayerNumber()){ // AI player is inverted, so you are the enemy here
                            enemyPieces = Arrays.copyOf(enemyPieces, enemyPieces.length + 1);
                            enemyPieces[enemyPieces.length - 1] = piece;
                        }
                    }
                }
            }
            // getting enemy moves
            for (MasterPiece piece: enemyPieces){
                for (Coordinate move: piece.getMoves(board)){
                        enemyMoves = Arrays.copyOf(enemyMoves, enemyMoves.length + 1);
                        enemyMoves[enemyMoves.length - 1] = move;
                }
            }

            boolean done = false;
            for (int i = 0; i < bestMoves.length; i ++){
                for (Coordinate enemyMove: enemyMoves){
                    if (enemyMove.equals(moves[i])) {
                        indexToUse = i;
                        done = true;
                        break;
                    }

                }
                if (done) break;
            }


            SpecialCoord special = new SpecialCoord(bestMoves[indexToUse], moves[indexToUse].getX(), moves[indexToUse].getY());
            System.out.println(special.toString());
            return special;
        }else return null;

    }
}
