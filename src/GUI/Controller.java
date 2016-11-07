package GUI;

import Logic.Board;
import Logic.Player;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML

    // Board Shit and such
    Board board; // the board for the game.
    Player white;
    Player black;

    // Graphics junk used for drawing the Board and such
    GraphicsContext graphics;

    // this will initialize the change listeners and such
   public void initialize() {
        // this creates a canvas and binds the size to the size of the canvas
        System.out.println("Ran init");
        Canvas canvas = new Canvas();
        canvas.setHeight(2000);
        canvas.setWidth(2000);

        // adds the canvas to the gamePane
        gamePane.getChildren().addAll(canvas);

        // gets the ability to draw on canvas
        graphics = canvas.getGraphicsContext2D();

       System.out.println(canvas.getHeight());
    }

    // settings dialogue
    public void settings() {
    }

    // opens the about dialogue
    public void about() {

    }

    // creates a new game
    public void newGame() {
        new NewGameWindow();
    }

    // undo the last move.
    public void undo() {

    }

    // gets the current mouse location
    void getMouseHover() {
    }

    //gets the current location of the mouse click
    void getMouseClick() {
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
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/Images/appIcon.png")));

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
            window.getIcons().add(new Image(getClass().getResourceAsStream("/Graphics/Images/appIcon.png")));
            window.show();
        }

        private void closeAction() {
            window.close();
        }
    }

    // // TODO: 11/3/16 fix this up to work with this program
    public class WarningWindow { // a warning window for when the user does something wrong.
        private String windowTitle;
        private String warning;

        public WarningWindow(String windowTitle, String warning) {
            this.windowTitle = windowTitle;
            this.warning = warning;

        }

        public void display() {
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
            ImageView warningImage = new ImageView(new Image(getClass().getResourceAsStream("/graphics/warning.png")));

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
            window.getIcons().add(new Image(getClass().getResourceAsStream("/graphics/AppIcon.png")));
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

        // board drawing components
        private Image blackSpot = new Image(getClass().getResourceAsStream("/Graphics/Images/blackSpot.png"));
        private Image whiteSpot = new Image(getClass().getResourceAsStream("/Graphics/Images/whiteSpot.png"));

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

        private NewGameWindow() {
            display();
        }

        private void display() { // builds and displays the window.
            // setting up the buttons
            closeBtn.setOnAction(e -> primaryStage.close());
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

        private void playBtnMethod() {
            System.out.println("New game");
            String playerOneType = whiteOptions.getValue();
            String playerTwoType = blackOptions.getValue();
            
            if (playerOneType.equals("None") || playerTwoType.equals("None")){ // if the player failed to set one of the players properly
                // // TODO: 11/3/16 make the warning window, must have two players. 
            }
            else { // if the player has set up the right options for the game
                white = new Player(playerOneType); // set the player types
                black = new Player(playerTwoType);
                board = new Board(white, black); // set the board up with white going first.

                // draw the new board for the new game.
                double startX = gamePane.getWidth()/2 - 250; // trying to get the center of window so we can center the board.
                double startY = gamePane.getHeight()/ + 250;

                System.out.println("startX: " + startX + " startY: " + startY);

                for (int y = 0; y < 5; y ++){ // for the y
                    for (int x = 0; x < 5; x ++){ // for the x
                        if ((x+y)%2 == 0){ // tells us which images to use for this spot on the board.
                            graphics.drawImage(blackSpot, (x * 100) + startX, (y * 100) + startY); // draws a 50X50 black spot
                            System.out.println("black. Y: " + y + " X: " + x);
                        }else {
                            graphics.drawImage(whiteSpot, (x * 100) + startX, (y * 100) + startY); // draws a 50X50 white spot
                            System.out.println("White.  Y: " + y + " X: " + x);
                        }
                    }
                }



            }
        }
           
    }


    // ------------------------------ Threading classes -------------------------------------

}