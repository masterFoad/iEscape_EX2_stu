package gui.management;

import controller.SysData;
import gui.SceneController;
import gui.utils.Acts;
import gui.utils.TypeConstants;
import gui.utils.methods.FormUtils;
import gui.utils.methods.LogInHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customer;
import model.Receptionist;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogIn implements Initializable{

    @FXML
    private Label errorName;

    @FXML
    private TextField userName;

    @FXML
    private Label errorPass;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signIn;

    @FXML
    private Label nameHolder;

    @FXML
    private Label passHolder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signIn.setOnAction(e->{

            if(FormUtils.validateString(userName)){
                errorName.setText("");
                nameHolder.setText(userName.getText().trim());
            }else{
                errorName.setText("*");
                nameHolder.setText("");
            }

            if(FormUtils.validateString(passwordField)){
                errorPass.setText("");
                passHolder.setText(userName.getText());
            }else{
                errorPass.setText("*");
                passHolder.setText("");
            }

            if(!nameHolder.getText().trim().isEmpty() && !passHolder.getText().trim().isEmpty() ){

                LogInHandler LIH = new LogInHandler(nameHolder.getText().trim(),passHolder.getText());
                Customer customerToLog = null;
                Receptionist recToLog = null;
                boolean admin = false;
                //getting suitable user for sysData , if found.
               if( LIH.getUser()!=null){
                   if(LIH.getUser() instanceof Customer)
                   {
                       customerToLog = (Customer)LIH.getUser();
                   }
                   if(LIH.getUser() instanceof Receptionist)
                   {
                       recToLog = (Receptionist)LIH.getUser();
                   }
                   if(LIH.getUser() instanceof Boolean)
                   {
                       admin = ((Boolean)LIH.getUser()).booleanValue();
                   }

                   /**
                    * setting up the Logged in user, Must check which one is not null.
                    */
                   SysData.getInstance().setParameter(TypeConstants.ADMIN,admin);
                   SysData.getInstance().setParameter(TypeConstants.CUSTOMER,customerToLog);
                   SysData.getInstance().setParameter(TypeConstants.RECEPTIONIST,recToLog);

                    //setting up the scene changer
                   SceneController scenes = SceneController.getInstance();

                    //TODO - add additional parameters maybe
                   //displaying scene with appropriate title
                   if(recToLog!=null){
                       try {
                           scenes.display(Acts.getScene(Acts.MAIN_MENU),"Welcome "+recToLog.getFirstName());
                       } catch (IOException e1) {
                           e1.printStackTrace();
                       }
                   }else
                   if(customerToLog!=null){
                       try {
                           scenes.display(Acts.getScene(Acts.MAIN_MENU),"Welcome "+customerToLog.getFirstName());
                       } catch (IOException e1) {
                           e1.printStackTrace();
                       }
                   }
                   else
                   if(admin){

                       try {
                           scenes.display(Acts.getScene(Acts.MAIN_MENU),"Welcome Admin");
                       } catch (IOException e1) {
                           e1.printStackTrace();
                       }

                   }


               }
               else{
                   errorName.setText("*");
                   errorPass.setText("*");
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                   alert.setTitle("Wrong Username or Password.");
                   alert.setHeaderText("Error");

                   Optional<ButtonType> result = alert.showAndWait();
                   if (result.get() == ButtonType.OK){

                       //System.out.println(SysData.getInstance().getCustomers().toString());
                   }

               }


            }

                }
        );


    }
}
