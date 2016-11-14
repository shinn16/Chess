package GUI;

import Logic.Board;
import Logic.Player;
import Pieces.Coordinate;
import Pieces.MasterPiece;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Array;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;

/***
 * Controller Class
 * This class is responsible for managing the logic behind the GUI.
 * Intensive logic processes should be run in a separate thread as they will cause the GUI to freeze.
 *
 * @author Patrick Shinn
 * @version 10/28/16
 */

public class Controller {
    // FXML Objects
    @FXML
    Label statusLbl = new Label();
    @FXML
    Pane gamePane = new Pane();
    // Board Shit and such
    private Board board; // the board for the game.

    // board drawing components
    private Image blackSpot = new Image(getClass().getResourceAsStream("/Graphics/Images/default/blackSpot.png"));
    private Image whiteSpot = new Image(getClass().getResourceAsStream("/Graphics/Images/default/whiteSpot.png"));

    // pieces
    private Image whitePawn = new Image(getClass().getResourceAsStream("/Graphics/Images/default/whitePawn.png"));
    private Image whiteKnight = new Image(getClass().getResourceAsStream("/Graphics/Images/default/whiteKnight.png"));
    private Image whiteRook = new Image(getClass().getResourceAsStream("/Graphics/Images/default/whiteRook.png"));
    private Image whiteBishop = new Image(getClass().getResourceAsStream("/Graphics/Images/default/whiteBishop.png"));
    private Image whiteQueen = new Image(getClass().getResourceAsStream("/Graphics/Images/default/whiteQueen.png"));
    private Image whiteKing = new Image(getClass().getResourceAsStream("/Graphics/Images/default/whiteKing.png"));

    private Image blackPawn = new Image(getClass().getResourceAsStream("/Graphics/Images/default/blackPawn.png"));
    private Image blackKnight = new Image(getClass().getResourceAsStream("/Graphics/Images/default/blackKnight.png"));
    private Image blackRook = new Image(getClass().getResourceAsStream("/Graphics/Images/default/blackRook.png"));
    private Image blackBishop = new Image(getClass().getResourceAsStream("/Graphics/Images/default/blackBishop.png"));
    private Image blackQueen = new Image(getClass().getResourceAsStream("/Graphics/Images/default/blackQueen.png"));
    private Image blackKing = new Image(getClass().getResourceAsStream("/Graphics/Images/default/blackKing.png"));

    // other variables
    private GraphicsContext graphics;
    private Canvas canvas = null;
    private boolean clicked = false;
    private boolean game = false;
    private Coordinate[] currentMoveSet = null;
    private Coordinate currentPeice = null;

    // this will initialize the change listeners and such
   public void initialize() {
       // update the status label
       statusLbl.setText("No game.");
       //create a canvas to draw on
       canvas = new Canvas(700,700);

       // get the ability to draw on it
       graphics = canvas.getGraphicsContext2D();

       // add the canvas to the gamePane
       gamePane.getChildren().addAll(canvas);

       // setting up an event listener for the mouse movement

    }

    // settings dialogue
    public void settings() {
    }

    // opens the about dialogue
    public void about() {
        new AboutWindow("About", "Wahjudi’s Highly Advanced Chess", "1.0",
                "This is an insane version of chess!", "Can't Trump This", "https://github.com/shinn16/Chess");
    }

    // creates a new game
    public void newGame() {
        new NewGameWindow();
    }

    // undo the last move.
    public void undo() {
        //// TODO: 11/13/16 either finish or remove this method.
    }

    private void freshBoard(){
        // draw the new board for the new game.
        graphics.setStroke(Color.BLACK); // settings up to draw a black border for the board.
        graphics.setLineWidth(5); // sets the weight of the line for the border
        graphics.strokeRect(150, 100, 500, 500); // draws rectangle
        // draws the board.
        for (int y = 0; y < 5; y ++){ // for the y
            for (int x = 0; x < 5; x ++){ // for the x
                if ((x+y)%2 == 0){ // tells us which images to use for this spot on the board.
                    graphics.drawImage(blackSpot, (x * 100) + 150, (y * 100) +100); // draws a 100X100 black spot centered
                }else {
                    graphics.drawImage(whiteSpot, (x * 100) + 150, (y * 100) + 100); // draws a 100X100 white spot centered
                }
            }
        }
        graphics.setStroke(Color.AQUA); // new highlight color
        graphics.setLineWidth(2); // new line width.
    }

    private void drawPieces(){
        // draw the pieces on the board
        // black side
        if (game){ // if there is a game on, we will draw the pieces at their given coordinates.
            MasterPiece[] player0Pieces = board.getPlayers()[0].getPieces();
            MasterPiece[] player1Pieces = board.getPlayers()[1].getPieces();
            
            //// TODO: 11/13/16 this is broken somewhere. The pawn will not update to the king. 

            for (MasterPiece piece: player1Pieces){ // draw the black pieces
                try {
                    if (piece.toString().contains("King")) graphics.drawImage(blackKing, (piece.getCoords().getX() + 1) * 100 + 50, (piece.getCoords().getY() + 1) * 100);
                    else if (piece.toString().contains("Queen")) graphics.drawImage(blackQueen, (piece.getCoords().getX() + 1) * 100 + 50, (piece.getCoords().getY() + 1) * 100);
                    else if (piece.toString().contains("Rook")) graphics.drawImage(blackRook, (piece.getCoords().getX() + 1) * 100 + 50, (piece.getCoords().getY() + 1) * 100);
                    else if (piece.toString().contains("Knight")) graphics.drawImage(blackKnight, (piece.getCoords().getX() + 1) * 100 + 50, (piece.getCoords().getY() + 1) * 100);
                    else if (piece.toString().contains("Bishop")) graphics.drawImage(blackBishop, (piece.getCoords().getX() + 1) * 100 + 50, (piece.getCoords().getY() + 1) * 100);
                    else if (piece.toString().contains("Pawn")) graphics.drawImage(blackPawn, (piece.getCoords().getX() + 1) * 100 + 50, (piece.getCoords().getY() + 1) * 100);
                }catch (NullPointerException e) { // ignore
                }
            }
            for (MasterPiece piece: player0Pieces){ // draw the white pieces
                try{
                    if (piece.toString().contains("King"))graphics.drawImage(whiteKing, (piece.getCoords().getX() + 1)*100 + 50, (piece.getCoords().getY() + 1)*100);
                    else if (piece.toString().contains("Queen")) graphics.drawImage(whiteQueen, (piece.getCoords().getX() + 1)*100 + 50, (piece.getCoords().getY() + 1)*100);
                    else if (piece.toString().contains("Rook")) graphics.drawImage(whiteRook, (piece.getCoords().getX() + 1)*100 + 50, (piece.getCoords().getY() + 1)*100);
                    else if (piece.toString().contains("Knight")) graphics.drawImage(whiteKnight, (piece.getCoords().getX() + 1)*100 + 50, (piece.getCoords().getY() + 1)*100);
                    else if (piece.toString().contains("Bishop")) graphics.drawImage(whiteBishop, (piece.getCoords().getX() + 1)*100 + 50, (piece.getCoords().getY() + 1)*100);
                    else if (piece.toString().contains("Pawn")) graphics.drawImage(whitePawn, (piece.getCoords().getX() + 1)*100 + 50, (piece.getCoords().getY() + 1)*100);
                }catch (NullPointerException e) { // ignore
                }

            }

        }else { // fresh set of pieces
            graphics.drawImage(blackKing, 150, 100);
            graphics.drawImage(blackQueen, 250, 100);
            graphics.drawImage(blackBishop, 350, 100);
            graphics.drawImage(blackKnight, 450, 100);
            graphics.drawImage(blackRook, 550, 100);
            for (int i = 1; i < 6; i++) graphics.drawImage(blackPawn, i * 100 + 50, 200);

            // white side
            graphics.drawImage(whiteRook, 150, 500);
            graphics.drawImage(whiteKnight, 250, 500);
            graphics.drawImage(whiteBishop, 350, 500);
            graphics.drawImage(whiteQueen, 450, 500);
            graphics.drawImage(whiteKing, 550, 500);
            for (int i = 1; i < 6; i++) graphics.drawImage(whitePawn, i * 100 + 50, 400);

            // reset the stroke color to be used for highlighting.
            graphics.setStroke(Color.AQUA);
        }
    }

    // gets the current mouse location
    public void getMouseHover(MouseEvent event) {
        int mouse_x = (int)event.getSceneX();
        int mouse_y = (int)event.getSceneY();

    }

    //gets the current location of the mouse click and does the appropriate actions
    public void getMouseClick(MouseEvent event) {
        int mouse_x = (int) event.getSceneX();
        int mouse_y = (int) event.getSceneY();

        // this will give us the index of the board position.
        mouse_x = (mouse_x - 50) / 100 - 1;
        mouse_y = (mouse_y - 30) / 100 - 1;

        // now we try to get a piece at these coordinates.
        // ensures we are on the board, otherwise resets all graphics in current state.
        if (board.isLocked()) new WarningWindow("Game Over!","The game is over, start a new game if you want to play.");
        else {
                if (mouse_x < 0 || mouse_y < 0 || mouse_x > 4 || mouse_y > 4) {
                    if (game){
                        freshBoard();
                        drawPieces();
                    }
                    clicked = false;
                    currentMoveSet = null;
                    currentPeice = null;

                }else if (clicked) { // if we have already selected a piece
                    if (currentMoveSet.length == 0) { // if we have no moves, clear the board of move options.
                        clicked = false;
                        freshBoard();
                        drawPieces();
                    } else {
                        for (Coordinate move : currentMoveSet) {
                            if (mouse_x == move.getX() && mouse_y == move.getY()) { // if the current click is in the move set, make the move.
                                if (board.getPiece(move.getY(), move.getX()) != null) { // if there is an enemy piece at this point
                                    int pieceAttacked = board.getPiece(move.getY(), move.getX()).getArrayIndex(); // gets the index of the piece
                                    board.getPlayers()[board.getTurnCounter()].capturePiece(pieceAttacked); // remove the piece from the opponents list of pieces
                                }
                                board.makeMove(board.getPiece(currentPeice.getY(), currentPeice.getX()), mouse_y, mouse_x); // move the piece
                                freshBoard(); // update the board.
                                drawPieces();
                                clicked = false; // reset click
                                if (!board.gameOver()) {
                                    String winner = null;
                                    // this portion determines the winner
                                    int whitePlayerPieces = board.getPlayers()[1].getPieceCount();
                                    int blackPlayerPieces = board.getPlayers()[0].getPieceCount();
                                    if (whitePlayerPieces < blackPlayerPieces) winner = "Black";
                                    else if (whitePlayerPieces > blackPlayerPieces) winner = "White";
                                    else winner = "None";
                                    new EndOfGameWindow(winner + " wins!");
                                    statusLbl.setText("Game over.");
                                    board.setLocked(true);
                                } else {
                                    if (board.getTurnCounter() == 0) statusLbl.setText("White's turn");
                                    else statusLbl.setText("Black's turn.");
                                }
                                board.nextTurn(); // advances to the next turn.
                            } else { // else, clear the stuff.
                                clicked = false;
                                freshBoard();
                                drawPieces();
                            }
                    }
                }

                // if we have not already selected a piece and there is a piece at the current position, we also enforce turns here.
            }else if (board.getPiece(mouse_y, mouse_x) != null && board.getTurnCounter() == board.getPiece(mouse_y, mouse_x).getPlayerID()) {

                graphics.strokeRect((mouse_x + 1) * 100 + 52, (mouse_y + 1) * 100 + 2, 98, 98);// highlight the piece tile in blue
                currentPeice = board.getPiece(mouse_y, mouse_x).getCoords(); // store this info for the next click
                currentMoveSet = board.getPiece(mouse_y, mouse_x).getMoves(board);

                for (Coordinate coordinate : currentMoveSet) { //for every move in the move set, highlight the spots.
                    // if the spot contains an enemy, highlight it red
                    if (board.getPiece(coordinate.getY(), coordinate.getX()) != null){
                        if (board.getPiece(coordinate.getY(), coordinate.getX()).getPlayerID() != board.getPiece(currentPeice.getY(), currentPeice.getX()).getPlayerID()){
                            graphics.setStroke(Color.RED);
                            graphics.strokeRect((coordinate.getX() + 1) * 100 + 52, (coordinate.getY() + 1) * 100 + 2, 96, 96);
                        }
                    }
                    else { // highlight it yellow.
                        graphics.setStroke(Color.YELLOW); // set to yellow to highlight the moves
                        graphics.strokeRect((coordinate.getX() + 1) * 100 + 52, (coordinate.getY() + 1) * 100 + 2, 96, 96);
                    }
                }
                graphics.setStroke(Color.AQUA); // reset color
                clicked = true; // we have clicked a piece

            }
        }
    }


    // ------------------------------ Private internal classes ------------------------------

    private class AboutWindow {
        private String windowName,
                developer,
                version,
                appName,
                website,
                aboutApp;
        private Stage window;

        private AboutWindow(String windowName, String appName,
                            String version, String aboutApp,
                            String developer, String website) {
            this.windowName = windowName;
            this.appName = appName;
            this.version = version;
            this.aboutApp = aboutApp;
            this.developer = developer;
            this.website = website;
            display();
        }

        private void display() {
            // Stage setup
            window = new Stage();
            window.setTitle(windowName);
            window.initModality(Modality.APPLICATION_MODAL); // means that while this window is open, you can't interact with the main program.

            // buttons
            Button closeBtn = new Button("Close");
            closeBtn.setOnAction(e -> window.close());

            // Labels
            Label appNameLabel = new Label(appName);
            Label websiteLabel = new Label("Website: " + website);
            Label aboutAppLabel = new Label(aboutApp);
            aboutAppLabel.setAlignment(Pos.CENTER);
            Label developerLabel = new Label("Developers: " + developer);
            Label versionLabel = new Label("Version: " + version);

            // Images
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/Images/App.png")));

            // Layout type
            VBox layout = new VBox(10);
            HBox closeBox = new HBox();
            closeBox.getChildren().addAll(closeBtn);
            closeBox.setAlignment(Pos.CENTER_RIGHT);
            closeBox.setPadding(new Insets(5, 5, 5, 5));
            layout.getChildren().addAll(imageView, appNameLabel, developerLabel, versionLabel, aboutAppLabel, websiteLabel, closeBox);
            layout.setAlignment(Pos.CENTER);

            // Building scene and displaying.
            Scene scene = new Scene(layout);
            scene.getStylesheets().addAll("/Graphics/CSS/StyleSheet.css");
            window.setScene(scene);
            window.setHeight(400);
            window.setWidth(550);
            window.setResizable(false);
            window.getIcons().add(new Image(getClass().getResourceAsStream("/Graphics/Images/App.png")));
            window.show();
        }

    }

    private class WarningWindow { // a warning window for when the user does something wrong.
        private String windowTitle;
        private String warning;

         WarningWindow(String windowTitle, String warning) {
            this.windowTitle = windowTitle;
            this.warning = warning;
            display();

        }

        private void display() {
            // displays the window
            Stage window = new Stage();
            window.setTitle(windowTitle);
            window.initModality(Modality.APPLICATION_MODAL); // means that while this window is open, you can't interact with the main program.

            // buttons
            Button closeBtn = new Button("Close");
            closeBtn.setOnAction(e -> window.close());

            // labels
            Label warningLabel = new Label(warning);

            // images
            ImageView warningImage = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/Images/warning.png")));

            // Building the window
            VBox layout = new VBox(10);
            HBox closeBox = new HBox();
            closeBox.getChildren().addAll(closeBtn);
            closeBox.setAlignment(Pos.CENTER_RIGHT);
            closeBox.setPadding(new Insets(5, 5, 5, 5));
            HBox hBox = new HBox(10);
            hBox.getChildren().addAll(warningImage, warningLabel);
            hBox.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(hBox, closeBox);
            layout.setAlignment(Pos.CENTER);

            // Showing the window
            Scene scene = new Scene(layout);
            scene.getStylesheets().addAll("Graphics/CSS/StyleSheet.css");
            window.setScene(scene);
            window.setHeight(200.00);
            window.setWidth(550.00);
            window.getIcons().add(new Image(getClass().getResourceAsStream("/Graphics/Images/App.png")));
            window.show();
        }
    }

    private class NewGameWindow {
        private Stage primaryStage = new Stage();
        private Button playBtn = new Button("Play");
        private Button closeBtn = new Button("Cancel");
        private Label whiteLbl = new Label("White");
        private Label blackLbl = new Label("Black");
        private ComboBox<String> whiteOptions = new ComboBox<>();
        private ComboBox<String> blackOptions = new ComboBox<>();


        private NewGameWindow() {
            statusLbl.setText("Setting up new game.");
            display();
        }

        private void display() { // builds and displays the window.
            // setting up the buttons
            closeBtn.setOnAction(e -> cancelMethod());
            playBtn.setOnAction(e -> playBtnMethod());

            //settings up player options
            whiteOptions.getItems().addAll("Human", "AI");
            blackOptions.getItems().addAll("Human", "AI");


            // layout setup
            VBox layout = new VBox(10);

            // white options section
            HBox whiteSection = new HBox(10);
            whiteOptions.setValue("None"); // value
            whiteSection.getChildren().addAll(whiteLbl, whiteOptions);
            whiteSection.setAlignment(Pos.CENTER);


            // black section options
            HBox blackSection = new HBox(10);
            blackOptions.setValue("None"); // initial value
            blackSection.getChildren().addAll(blackLbl, blackOptions);
            blackSection.setAlignment(Pos.CENTER);

            // button section
            HBox buttonsSection = new HBox(10);
            buttonsSection.setAlignment(Pos.CENTER_RIGHT);
            buttonsSection.getChildren().addAll(closeBtn, playBtn);
            buttonsSection.setPadding(new Insets(5, 5, 5, 5)); // setting spacing around the Hbox

            // building main layout
            layout.getChildren().addAll(whiteSection, blackSection, buttonsSection);
            layout.setAlignment(Pos.CENTER);

            // building window
            Scene scene = new Scene(layout);
            scene.getStylesheets().addAll("/Graphics/CSS/StyleSheet.css"); // sets the style sheet.
            primaryStage.setScene(scene);
            primaryStage.setTitle("New Game");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // sets the window to be modal, meaning the underlying window cannot be used until this one is closed.
            primaryStage.setWidth(245);
            primaryStage.setHeight(160);
            primaryStage.setResizable(false); // this window cannot be resized.
            primaryStage.show(); // displays the window
        }

        private void cancelMethod(){
            primaryStage.close();
            statusLbl.setText("No game.");
        }

        private void playBtnMethod() {
            statusLbl.setText("White's turn.");
            String playerOneType = whiteOptions.getValue();
            String playerTwoType = blackOptions.getValue();
            
            if (playerOneType.equals("None") || playerTwoType.equals("None")){ // if the player failed to set one of the players properly
                new WarningWindow("Looks like there is something wrong with your settings...", "You have to apply settings for both players!");
            }
            else { // if the player has set up the right options for the game
                Player white = new Player(playerOneType, 0); // set the player types
                Player black = new Player(playerTwoType, 1);
                board = new Board(white, black); // set the board up with white going first.

                freshBoard();
                drawPieces();
                game = true; // we are now playing a game.
                primaryStage.close();

            }
        }
           
    }

    private class EndOfGameWindow {
        private Stage primaryStage = new Stage();
        private Label messageLabel = new Label();
        private Button okayButton = new Button("Okay");
        private Image fireworks = new Image(getClass().getResourceAsStream("/Graphics/Images/fireworks.gif"));
        private ImageView leftWorks = new ImageView(fireworks);
        private ImageView rightWorks = new ImageView(fireworks);

        public EndOfGameWindow(String message) {

            Scene scene;

            messageLabel.setText(message);

            okayButton.setOnAction(e -> primaryStage.close());


            // vbox
            VBox vertical = new VBox(20);
            vertical.getChildren().addAll(messageLabel, okayButton);
            vertical.setPadding(new Insets(5, 5, 5, 5));
            vertical.setAlignment(Pos.CENTER);

            // main layout
            HBox layout = new HBox(5);
            layout.getChildren().addAll(leftWorks, vertical, rightWorks);
            layout.setAlignment(Pos.CENTER);

            scene = new Scene(layout);
            scene.getStylesheets().addAll("/Graphics/CSS/StyleSheet.css");

            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.APPLICATION_MODAL); // sets the window to be modal, meaning the underlying window cannot be used until this one is closed.
            primaryStage.setWidth(400);
            primaryStage.setHeight(250);
            primaryStage.setResizable(false); // this window cannot be resized.\
            primaryStage.setTitle("End of Game"); // window title
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Graphics/Images/App.png"))); // window icon

            primaryStage.show(); // displays the window
        }
    }



    // ------------------------------ Threading classes -------------------------------------

}