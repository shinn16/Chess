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

    public void setPiece(int y, int x, MasterPiece piece){
        board[y][x] = piece;
    }

    private void setBoard(MasterPiece[][] board) {
        this.board = board;
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
        boolean player0Pieces = false;
        boolean player1Pieces = false;

        if (players[0].getPieceCount() > 0) player0Pieces = true;
        if (players[1].getPieceCount() > 0) player1Pieces = true;
        return (player0Pieces && player1Pieces && players[turnCounter].hasMove(this)); // all of these conditions must be true for the game to end.
    }
}
