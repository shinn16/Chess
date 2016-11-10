package Logic;

import Pieces.Coordinate;
import Pieces.MasterPiece;
/**
 * Board
 *
 * @author Patrick Shinn
 * @version 10/28/16
 */
public class Board {
    private MasterPiece[][] board = new MasterPiece[5][5]; // creates a 2d array that is 5X5
    private int turnCounter = 0;
    private Player[] players = new Player[2];

    // contructor
    public Board(Player player1, Player player2){

        players[0] = player1;
        players[1] = player2;
        // adding player1 pieces to the board
        MasterPiece[] player1Pieces = player1.getPieces();
        for (MasterPiece piece: player1Pieces){
            Coordinate coords = piece.getCoords();
            board[coords.getCoords()[1]][coords.getCoords()[0]] = piece;
        }

        // adding player2 pieces to the board
        MasterPiece[] player2Pieces = player2.getPieces();
        for (MasterPiece piece: player2Pieces) {
            Coordinate coords = piece.getCoords();
            board[coords.getCoords()[1]][coords.getCoords()[0]] = piece;
        }

        // // TODO: 11/8/16 remove debug
        for (MasterPiece[] Pieces: board){
            for (MasterPiece piece: Pieces){
                if (piece == null) System.out.println("Null");
                else  System.out.println(piece.toString());

            }
        }

    }

    // gets the piece at the current location specified.
    public MasterPiece getPiece(int y, int x){
        return board[y][x];
    }

    // moves piece, used for general movement and attacks.
    public void makeMove(MasterPiece piece, int y, int x){
        board[y][x] = piece; // move the piece to the new position
        board[piece.getCoords().getCoords()[1]][ piece.getCoords().getCoords()[0]] = null; // set the old position to null
        piece.setCoords(y, x); // update coords in piece.
    }

    // returns the board.
    public MasterPiece[][] getBoard(){ // this will be used to feed the AI a Board
        return this.board;
    }

    // returns the players.
    public Player[] getPlayers() {
        return players;
    }
}
