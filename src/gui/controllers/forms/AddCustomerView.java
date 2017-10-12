package gui.controllers.forms;

import controller.SysData;
import gui.utils.methods.Activities;
import gui.utils.methods.FormUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;
import utils.E_Cities;
import utils.E_Levels;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class AddCustomerView implements Initializable {


    @FXML
    private TextField txtFullName;

    @FXML
    private Label errorFullName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Label errorPassword;

    @FXML
    private TextField txtEmail;
    @FXML
    private Label errorEmail;

    @FXML
    private Label errorAddress;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private ComboBox<E_Levels> levelList;

    @FXML
    private Button btnAddCustomer;


    @FXML
    private Button btnSetAddress;

    @FXML
    private Label errorLevel;

    @FXML
    private Label errorDate;

    @FXML
    private Button btnExit;

//    @FXML
//    protected void addCustomer(ActionEvent event) {
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Customer customerToAdd = new Customer();
        txtFullName.setText("");

        ObservableList<E_Levels> levels = FXCollections.observableArrayList(E_Levels.values());
        levelList.setItems(levels);

        SysData.getInstance().setParameter("Addressable",customerToAdd);

        btnExit.setOnAction(t->Activities.closeWindow(t));

        btnAddCustomer.setOnAction(t -> {


        if(FormUtils.validateString(txtFullName)) {
            setCustomerName(customerToAdd);
            errorFullName.setText("");
        }
        else{
            errorFullName.setText("*");
        }

            if(FormUtils.isEmailValid(txtEmail.getText().trim())) {
                try {
                    customerToAdd.setEmail(new URL("hTTp:\\\\"+txtEmail.getText().trim()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                errorEmail.setText("");
            }
            else{
                errorEmail.setText("*");
            }

            if(FormUtils.validateString(txtPassword)
                    && FormUtils.validateString(txtConfirmPassword)
                    && txtPassword.getText().equals(txtConfirmPassword.getText())) {
                customerToAdd.setPassword(txtPassword.getText());
                errorPassword.setText("");
            }
            else{
                errorPassword.setText("*");
            }

            if(levelList.getValue()!=null){
                customerToAdd.setLevel(levelList.getValue());
                errorLevel.setText("");
            }else{
                errorLevel.setText("*");
            }

            if(birthDatePicker.getValue()!=null){
                customerToAdd.setBirthdate(FormUtils.dateFromDatePicker(birthDatePicker));
                errorDate.setText("");
            }else{
                errorDate.setText("*");
            }

            if(customerToAdd.getCustomerAddress()==null){
                errorAddress.setText("*");
            }else{
                errorAddress.setText("");
            }




            if(
                    errorAddress.getText().equals("") &&
                    errorPassword.getText().equals("") &&
                    errorEmail.getText().equals("") &&
                    errorFullName.getText().equals("") &&
                    errorDate.getText().equals("") &&
                    errorLevel.getText().equals("") ){

                if(SysData.getInstance().addCustomer(FormUtils.generateId(),customerToAdd.getFirstName(),customerToAdd.getLastName(),customerToAdd.getBirthdate(),customerToAdd.getPassword(),customerToAdd.getLevel(),customerToAdd.getEmail(),customerToAdd.getCustomerAddress())){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Successfully added Customer");
                    alert.setHeaderText("Success");
                    alert.setContentText("Customer "+customerToAdd.getFirstName()+" has been added.");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        //TODO
                        //System.out.println(SysData.getInstance().getCustomers().toString());
                    }


                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed to add Customer");
                    alert.setHeaderText("Error");
                    alert.showAndWait();
                }

            }





        });

        btnSetAddress.setOnAction(t -> {


            try {
                Activities.openNewWindow(this,"/gui/views/addAddress.fxml","Add Address");
            } catch (IOException e) {
                e.printStackTrace();
            }



        });


    }


    private void setCustomerName(Customer customer)
    {
        String firstName  = "";
        StringJoiner lastName = new StringJoiner(" ");
        int counter =0;
        for(String s:txtFullName.getText().trim().split(" ")){
            if(s!=null && !s.equals("") || !s.equals(" ")) {
                if (counter == 0) {
                    firstName = s;
                    counter++;
                } else {
                    lastName.add(s);
                }
            }

        }
        customer.setFirstName(firstName);
        customer.setLastName(lastName.toString());

    }

}
