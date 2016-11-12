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
    private int turnCounter = 1;
    private Player[] players = new Player[2];

    // constructor
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

    // get the turn
    public int getTurnCounter() {
        return turnCounter;
    }

    // go to next turn
    public boolean nextTurn(){
        if (turnCounter == 1) turnCounter = 0;
        else turnCounter = 1;
        return gameOver();
    }

    // checks for a winner
    private boolean gameOver(){ // if either player is out of pieces, or the current player cannot make a move, the game is over.
        return ((players[0].getPieces().length == 0 || players[1].getPieces().length == 0) || !players[turnCounter].hasMove(this));
    }
}
