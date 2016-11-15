package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(new Scene(root, 1000, 659)); // sets the size of the window at open

        primaryStage.setResizable(false);
        primaryStage.getIcons().addAll(new Image("/Graphics/Images/App.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
