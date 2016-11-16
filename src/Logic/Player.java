package Logic;

import Pieces.*;

import java.util.Arrays;

/**
 * Player
 *
 * @author Patrick Shinn
 * @version 11/2/16
 */
public class Player {
    private String type;
    private int playerNumber;
    // this is used to keep track of player pieces
    private MasterPiece[] pieces = new MasterPiece[10];

    public Player(String type, int playerNumber) {
        this.type = type;
        this.playerNumber = playerNumber;
        if (playerNumber == 1) { // if the player is player 2, assign them the respective piece set.
            pieces[0] = new Pawn(0, 1, 0, 0);
            pieces[1] = new Pawn(1, 1, 0, 1);
            pieces[2] = new Pawn(2, 1, 0, 2);
            pieces[3] = new Pawn(3, 1, 0, 3);
            pieces[4] = new Pawn(4, 1, 0, 4);
            pieces[5] = new Rook(4, 0, 0, 5);
            pieces[6] = new Knight(3, 0, 0, 6);
            pieces[7] = new Bishop(2, 0, 0, 7);
            pieces[8] = new Queen(1, 0, 0, 8);
            pieces[9] = new King(0, 0, 0, 9);
        } else { // player 1 settings
            pieces[0] = new Pawn(0, 3, 1, 0);
            pieces[1] = new Pawn(1, 3, 1, 1);
            pieces[2] = new Pawn(2, 3, 1, 2);
            pieces[3] = new Pawn(3, 3, 1, 3);
            pieces[4] = new Pawn(4, 3, 1, 4);
            pieces[5] = new Rook(0, 4, 1, 5);
            pieces[6] = new Knight(1, 4, 1, 6);
            pieces[7] = new Bishop(2, 4, 1, 7);
            pieces[8] = new Queen(3, 4, 1, 8);
            pieces[9] = new King(4, 4, 1, 9);
        }
    }

    public String getType() {
        return type;
    }

    public MasterPiece[] getPieces() {
        return pieces;
    }

    public void capturePiece(int pieceIndex) {
        pieces[pieceIndex] = null;
    }

    // counts the number of pieces in the array.
    public int getPieceCount() {
        int count = 0; // starts at
        for (MasterPiece piece : pieces) {
            if (piece != null) count++; // for every piece that isnt null, increase count by one.
        }
        return count;
    }

    // the sole purpose of this method is to update the pawns to king in the event that the pawn crosses to the other side of the board.
    void updatePieces(Board board) {
        for (MasterPiece piece : pieces) {
            if (piece != null) {
                if (piece.toString().contains("Pawn")) {
                    if (((Pawn) piece).kingMe()) {
                        piece = new King(piece.getCoords().getX(), piece.getCoords().getY(), piece.getPlayerID(), piece.getArrayIndex());
                        this.getPieces()[piece.getArrayIndex()] = piece;
                        board.setPiece(piece.getCoords().getY(), piece.getCoords().getX(), piece);
                    }
                }
            }
        }
    }

    //  // TODO: 11/15/16 I don't think we need this method, if a piece has an attack, its move set only returns attacks.
    public MasterPiece[] getAttacks(Board board) {
        MasterPiece[] attackPieces = new MasterPiece[0];
        // for every piece that the player has, we are checking for attacks 
        for (MasterPiece piece : pieces) {
            if (piece != null) {
                if (piece.hasAttack(board)) {
                    attackPieces = Arrays.copyOf(attackPieces, attackPieces.length + 1);
                    attackPieces[attackPieces.length - 1] = piece; // adds the piece to an attack array.
                }
            }
        }

        return attackPieces;
    }

    // checks if any piece has a move
    boolean hasMove(Board board) {
        boolean move = false;
        for (MasterPiece piece : pieces) { // for every piece
            if (piece != null) {
                if (piece.getMoves(board).length > 0)
                    move = true; // if there is a piece that has moves, this method returns true.
            }
        }
        return move;
    }

    @Override
    protected Player clone(){
        return new Player(this.getType(), this.playerNumber);
    }
}
