package gui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Acts {

    public final static String LOG_IN = "login";
    public final static String MAIN_MENU = "mainmenu";


    private  static FXMLLoader loader;



    private Acts(){}

    public static Pane getScene(String sceneName) throws IOException {

            if(sceneName.equals(LOG_IN)){
                loader = new FXMLLoader();
                loader.setLocation(Acts.class.getResource("/gui/views/logIn.fxml"));
                return loader.load();
            }
            if(sceneName.equals(MAIN_MENU)){
                loader = new FXMLLoader();
                loader.setLocation(Acts.class.getResource("/gui/views/mainMenu.fxml"));
                return loader.load();
            }



            return null;
    }



   // public static final Pane LOG_IN =


}
