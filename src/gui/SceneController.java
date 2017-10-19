package gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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

    /**
     * scene, and title
     * @param sceneToDisplay
     * @param title
     */
    public void display(Pane sceneToDisplay,String title){
        primaryStage.setTitle(title);
        Scene myScene = new Scene(sceneToDisplay);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }


    /**
     * scene to display,title,width,height
     * @param sceneToDisplay
     * @param title
     */
    public void popUp(Pane sceneToDisplay,String title){

        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(title));
        Scene dialogScene = new Scene(sceneToDisplay);
        dialog.setScene(dialogScene);
        dialog.show();

    }


}