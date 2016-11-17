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

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getType() {
        return type;
    }

    public void setPieces(MasterPiece[] pieces) {
        this.pieces = pieces;
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

    // this method updates the piece array of the player to match that of the board.
    void updatePieces(Board board) {
        for (MasterPiece piece : pieces) {
            if (piece != null) { // here we try to ensure that the board and the piece array match
                if (board.getPiece(piece.getCoords().getY(), piece.getCoords().getX()) == null){
                    pieces[piece.getArrayIndex()] = null;
                }
                if (piece.toString().contains("Pawn")) { // here we update the pawns if we can.
                    if (((Pawn) piece).kingMe()) {
                        piece = new King(piece.getCoords().getX(), piece.getCoords().getY(), piece.getPlayerID(), piece.getArrayIndex());
                        this.getPieces()[piece.getArrayIndex()] = piece;
                        board.setPiece(piece.getCoords().getY(), piece.getCoords().getX(), piece);
                    }
                }
            }
        }
    }

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

    // clones the player
    public Player copyOf(){
        Player clone = new Player(this.getType(), this.playerNumber);
        MasterPiece[] piecesClone = new MasterPiece[10];
        for (MasterPiece piece: this.getPieces()){
            if (piece != null) piecesClone[piece.getArrayIndex()] = piece.copyOf();
        }
        clone.setPieces(piecesClone);
        return clone;
    }

    public boolean equals(Player player){
        return (playerNumber == player.getPlayerNumber());
    }
}
