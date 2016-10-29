package GUI;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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

    // Graphics junk used for drawing the board and such
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

    }

    // undo the last move.
    public void undo(){

    }

    // gets the current mouse location
    void getMouseHover(){}

    //gets the current location of the mouse click
    void getMouseClick(){}

    // ------------------------------ Private internal classes ------------------------------

    // ------------------------------ Threading classes -------------------------------------
}
