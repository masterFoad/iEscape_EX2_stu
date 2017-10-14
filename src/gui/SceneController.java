package gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {

    private Stage primaryStage;

    private static class SingleInstance {
        private static final SceneController instance = new SceneController();
    }

    public static SceneController getInstance() {
        return SceneController.SingleInstance.instance;
    }



    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void display(Pane sceneToDisplay,String title){
        primaryStage.setTitle(title);
        Scene myScene = new Scene(sceneToDisplay);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}