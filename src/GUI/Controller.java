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
import javafx.scene.control.TextField;
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
    Canvas canvas = new Canvas();

    // Board Shit and such
    Board board; // the board for the game.
    Player player1;
    Player player2;

    // Graphics junk used for drawing the Board and such
    GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    // this will initialize the change listeners and such
    void initialize(){

        // this binds the canvas to the pane, so if the pane resizes, so does the canvas.
        canvas.heightProperty().bind(gamePane.heightProperty());
        canvas.heightProperty().bind(gamePane.widthProperty());
    }

    // settings dialogue
    public void settings(){}

    // opens the about dialogue
    public void about(){

    }

    // creates a new game
    public void newGame(){
        new NewGameWindow();
    }

    // undo the last move.
    public void undo(){

    }

    // gets the current mouse location
    void getMouseHover(){}

    //gets the current location of the mouse click
    void getMouseClick(){}

    // ------------------------------ Private internal classes ------------------------------

    private class AboutWindow {
        private String windowName ,
                developer,
                version,
                appName,
                website,
                aboutApp;
        private Stage window;

        private AboutWindow(String windowName, String appName,
                            String version, String aboutApp,
                            String developer, String website){
            this.windowName = windowName;
            this.appName = appName;
            this.version = version;
            this.aboutApp = aboutApp;
            this.developer = developer;
            this.website = website;
            display();
        }
        private void display(){
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
            closeBox.setPadding(new Insets(5,5,5,5));
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

    private class NewGameWindow{
        private Stage primaryStage = new Stage();
        private Button playBtn = new Button("Play");
        private Button closeBtn = new Button("Cancel");

        // all of these are for player settings.
        private ComboBox[] playerComboBoxes = new ComboBox[5];

        // text entry spots for board sizes.
        private TextField xSize = new TextField();
        private TextField ySize = new TextField();
        private TextField zSize = new TextField();

        // other labels
        private final Label playerSettings = new Label("Player Settings");
        private final Label boardSettings = new Label("Board Settings");

        private NewGameWindow() {
            xSize.setPromptText("Width");
            ySize.setPromptText("Length");
            zSize.setPromptText("Height");
            display();
        }
        private void display(){
                // setting up the buttons
                closeBtn.setOnAction(e -> primaryStage.close());
                playBtn.setOnAction(e -> playBtnMethod());

                //text field alignment settings
                xSize.setAlignment(Pos.CENTER);
                ySize.setAlignment(Pos.CENTER);
                zSize.setAlignment(Pos.CENTER);

                //settings up player options
                for (int i = 0; i < playerComboBoxes.length; i++) {
                    ComboBox<String> cb = new ComboBox<>();

                    cb.getItems().addAll("None", "Human", "AI (Smarty)");
                    cb.setPrefSize(125, 25);
                    cb.setValue("None");

                    playerComboBoxes[i] = cb;
                }

                // layout setup
                VBox layout = new VBox(10);

                // "Player Settings" label
                layout.getChildren().add(playerSettings);

                // Add combo boxes for each player slot
                for (int i = 0; i < playerComboBoxes.length; i++) {
                    HBox section = new HBox(10);
                    section.getChildren().addAll(new Label("Player " + (i+1)), playerComboBoxes[i]);
                    section.setAlignment(Pos.CENTER);
                    layout.getChildren().add(section);
                }

                VBox boardSectionVertical = new VBox(10);
                HBox boardSectionHorizontal = new HBox(10);
                boardSectionHorizontal.getChildren().addAll(xSize, ySize, zSize);
                boardSectionHorizontal.setAlignment(Pos.CENTER);
                boardSectionVertical.getChildren().addAll(boardSettings, boardSectionHorizontal);
                boardSectionVertical.setAlignment(Pos.CENTER);


                HBox buttonsSection = new HBox(10);
                buttonsSection.setAlignment(Pos.CENTER_RIGHT);
                buttonsSection.getChildren().addAll(closeBtn, playBtn);
                buttonsSection.setPadding(new Insets(5,5,5,5)); // setting spacing around the Hbox

                // building main layout
                layout.getChildren().addAll(boardSectionVertical, buttonsSection);
                layout.setAlignment(Pos.CENTER);

                // building window
                Scene scene = new Scene(layout);
                scene.getStylesheets().addAll("/Graphics/CSS/StyleSheet.css"); // sets the style sheet.
                primaryStage.setScene(scene);
                primaryStage.setTitle("New Game");
                primaryStage.initModality(Modality.APPLICATION_MODAL); // sets the window to be modal, meaning the underlying window cannot be used until this one is closed.
                primaryStage.setWidth(245);
                primaryStage.setHeight(350);
                primaryStage.setResizable(false); // this window cannot be resized.
                primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Graphics/Images/appIcon.png"))); // window icon
                primaryStage.show(); // displays the window
            }

            private void playBtnMethod(){



            }
        }





    }

    // ------------------------------ Threading classes -------------------------------------

