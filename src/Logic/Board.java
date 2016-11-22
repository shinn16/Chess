package Logic;

import Pieces.Coordinate;
import Pieces.MasterPiece;
import javafx.application.Platform;

import java.util.Arrays;

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
    private boolean locked = false;

    // constructor
    public Board(Player player1, Player player2){

        players[0] = player1;
        players[1] = player2;
        // adding player1 pieces to the board
        MasterPiece[] player1Pieces = player1.getPieces();
        for (MasterPiece piece: player1Pieces){
            if (piece != null){
                board[piece.getCoords().getY()][piece.getCoords().getX()] = piece;
            }
        }

        // adding player2 pieces to the board
        MasterPiece[] player2Pieces = player2.getPieces();
        for (MasterPiece piece: player2Pieces){
            if (piece != null){
                board[piece.getCoords().getY()][piece.getCoords().getX()] = piece;
            }
        }
    }

    void setPiece(int y, int x, MasterPiece piece){
        board[y][x] = piece;
    }

    public void setTurnCounter(int turn){
        this.turnCounter = turn;
    }

    // gets the piece at the current location specified.
    public MasterPiece getPiece(int y, int x){
        return board[y][x];
    }

    // moves piece, used for general movement and attacks.
    public void makeMove(MasterPiece piece, int y, int x) {
        board[y][x] = piece; // move the piece to the new position
        board[piece.getCoords().getY()][piece.getCoords().getX()] = null; // set the old position to null
        piece.setCoords(y, x); // update coords in piece.
        for (Player player : players) {
            player.updatePieces(this);
        }
    }

    // returns the players who is currently at turn.
    public Player getCurrentPlayer(){
        return players[turnCounter];
    }

    // returns the board.
    public MasterPiece[][] getBoard(){ // this will be used to feed the AI a Board
        return this.board;
    }

    // returns the players.
    public Player[] getPlayers() {
        return players;
    }

    // returns the state of the board (locked or not)
    public boolean isLocked() {
        return locked;
    }

    // sets the lock property
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    // get the turn
    public int getTurnCounter() {
        return turnCounter;
    }

    // go to next turn
    public void nextTurn(){
        if (turnCounter == 1) turnCounter = 0;
        else turnCounter = 1;

    }

    public boolean hasAttack(){
        boolean attack = false;

        // literally looking at every single fucking piece on the board
        for (MasterPiece[] row: this.getBoard()){
            for (MasterPiece piece: row){
                if (piece!= null){
                    if (piece.getPlayerID() == this.getTurnCounter()){
                        if (piece.hasAttack(this)){
                            attack = true;
                            break;

                        }
                    }
                }
            }
        }
        return attack;
    }

    //clone method
    public Board copyOf(){
        return new Board(players[0].copyOf(), players[1].copyOf());
    }


    // checks for a winner
    public boolean gameOver(){ // if either player is out of pieces, or the current player cannot make a move, the game is over.
        boolean bothPlayersHavePieces = true;
        int blackCount = 0;
        int whiteCount = 0;

        for (MasterPiece[] row: this.getBoard()){ // check every piece on the board and count them up
            for (MasterPiece piece: row){
                if (piece != null){
                    if (piece.getPlayerID() == 0) whiteCount ++;
                    else blackCount ++;
                }
            }
        }
        boolean bothPlayersHaveMoves = true;
        MasterPiece[] pieces1 = new MasterPiece[0];
        MasterPiece[] pieces2 = new MasterPiece[0];
        for (Player player: players){ // this checks to see if either player is stuck and cannot make a move.
            for (MasterPiece[] row: this.getBoard()){
                for (MasterPiece piece: row){
                    if (piece != null){
                        if (piece.getPlayerID() == player.getPlayerNumber()){
                            if (piece.getMoves(this).length != 0){
                                if (player.getPlayerNumber() == 0 && piece.getPlayerID() == 0){
                                    pieces1 = Arrays.copyOf(pieces1, pieces1.length + 1);
                                    pieces1[pieces1.length - 1] = piece;
                                }else {
                                    pieces2 = Arrays.copyOf(pieces2, pieces2.length + 1);
                                    pieces2[pieces2.length - 1] = piece;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (pieces1.length == 0 || pieces2.length == 0) bothPlayersHaveMoves = false;

        if (whiteCount == 0 || blackCount ==0 ) bothPlayersHavePieces =false; // if one player or the other has no pieces, the game is over.

        return (bothPlayersHavePieces && bothPlayersHaveMoves); // all of these conditions must be true for the game to end.
    }
}
