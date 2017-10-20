package gui;

import controller.SysData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Address;
import utils.E_Levels;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static utils.E_Cities.Herzliya;

public class testingMain extends Application {


    public static void main(String[] args) {

        try {
            SysData.getInstance().addCustomer("000099991","Foad" , "hero",new Date(), "gilad234" , E_Levels.BEGINNERS   , new URL("hTTp:\\\\gilad@Gmail.com"), new Address("Israel" , Herzliya      , "Herzel" 	,  22, "052-9878765".split("")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Add Branch");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/views/customersUI.fxml"));
        Pane myPane = loader.load();
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
