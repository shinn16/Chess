package Logic;

import Pieces.MasterPiece;

/**
 * board
 *
 * @author Patrick Shinn
 * @version 10/28/16
 */
public class board {
    private MasterPiece[][] board = new MasterPiece[5][5]; // creates a 2d array that is 5X5
    private int turnCounter = 0;
    private Player player1;
    private Player player2;

    public board(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public MasterPiece[][] getBoard(){ // this will be used to feed the AI a board
        return this.board;
    }


    public void commitMove(){
        if (turnCounter == 0){   // if it is player1's move
            // // TODO: 11/2/16 make this do shit.
            turnCounter ++; // increase to the next one.
        }
        else{ // it is player two's turn.
            // // TODO: 11/2/16  make this do stuff. Flip the board and such
            turnCounter = 0;
        }

    }
}
