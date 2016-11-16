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
    //// TODO: 11/14/16 Make this class
    Player player;
    Board board;
    public AI(Player player, Board board){
        this.board = board;
        this.player = player;
    }

    // this method should play for the AI
    public void play(Board board){
        MasterPiece[][] pieces;
        Coordinate[]attacks = new Coordinate[0];
        Coordinate[]Positions = new Coordinate[0];
        int counter = 0;
        pieces = board.getBoard();
        //get the attacks
        for (int row = 0; row < pieces.length; row++)
        {
            for (int col = 0; col < pieces.length; col++)
            {
                if (pieces[row][col] != null && pieces[row][col].hasAttack(board))
                {
                    Coordinate[] spots;
                    spots = pieces[row][col].getMoves(board);
                    for(Coordinate moves: spots)
                    {
                        if(moves != null) {
                            attacks[counter] = moves;
                            attacks = Arrays.copyOf(attacks, attacks.length+1);
                            counter++;
                        }
                    }
                }
            }
        }

        //if there are attacks to be made
        if(attacks.length != 0)
        {
            for(int i = 0; i < attacks.length; i++)
            {
                MasterPiece myPiece;
                myPiece = board.getPiece(attacks[i].getY(), attacks[i].getX());
                if (myPiece.getValue() == 0)
                {

                }
            }
        }
        else{
            for (int row = 0; row < pieces.length; row++)
            {
                for (int col = 0; col < pieces.length; col++)
                {
                    if (pieces[row][col] != null && pieces[row][col].hasAttack(board))
                    {
                        Coordinate[] spots = new Coordinate[5];
                        spots = pieces[row][col].getMoves(board);
                        for(Coordinate moves: spots)
                        {
                            if(moves != null) {
                                Positions[counter] = moves;
                                Positions = Arrays.copyOf(Positions, Positions.length+1);
                                counter++;
                            }
                        }
                    }
                }
            }

        }

        //

        //
        System.out.println("AI called");

    }
}
