package gui.main;

import gui.SceneController;
import gui.utils.Acts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

//        SceneController screenController = new SceneController(scene);
//        screenController.add("calculator", FXMLLoader.load(getClass().getResource( "calculator.fxml" )));
//        screenController.add("testSwitch", FXMLLoader.load(getClass().getResource( "TestSwitch.fxml" )));
//        screenController.activate("calculator");
        SceneController scenes = SceneController.getInstance();
        scenes.init(primaryStage);
        scenes.display(Acts.getScene(Acts.LOG_IN),"Welcome");

    }
}
