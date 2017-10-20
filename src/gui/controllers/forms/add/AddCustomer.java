package gui.controllers.forms.add;

import controller.SysData;
import gui.utils.Commons;
import gui.utils.methods.Activities;
import gui.utils.methods.FormUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customer;
import utils.E_Levels;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import static gui.utils.Commons.clearSelectedCustomers;
import static gui.utils.Commons.isEditingCustomer;

public class AddCustomer implements Initializable {


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

    private Customer customerToAdd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerToAdd = new Customer();
        ObservableList<E_Levels> levels = FXCollections.observableArrayList(E_Levels.values());
        levelList.setItems(levels);

        SysData.getInstance().setParameter("Addressable",customerToAdd);
        if(true){
            if(Commons.isSelectedCustomers() && isEditingCustomer()){
                customerToAdd = Commons.getSelectedCustomers().get(0);

                txtFullName.setText(customerToAdd.getFirstName()+" "+customerToAdd.getLastName());
                txtEmail.setText(customerToAdd.getEmail().toString());
                txtPassword.setText(customerToAdd.getPassword());
                txtConfirmPassword.setText(customerToAdd.getPassword());
                Instant instant = Instant.ofEpochMilli(customerToAdd.getBirthdate().getTime());
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                LocalDate localDate = localDateTime.toLocalDate();
                birthDatePicker.setValue(localDate);
                levelList.getSelectionModel().select(customerToAdd.getLevel());
                customerToAdd.setTheAddress(customerToAdd.getTheAddress());
                btnAddCustomer.setText("Edit");


            }
//            txtFullName.setText();

        }





        btnExit.setOnAction(t->{

            Activities.closeWindow(t);
            clearSelectedCustomers();

        });

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


                if(Commons.isLoggedReceptionist()||Commons.isLoggedAdmin()) {
                    if (Commons.isSelectedCustomers()) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Successfully Updated Customer");
                        alert.setHeaderText("Success");
                        alert.setContentText("Customer "+customerToAdd.getFirstName()+" has been added.");
                        Activities.closeWindow(t);
                    }
                }

                if(SysData.getInstance().addCustomer(FormUtils.generateId(),customerToAdd.getFirstName(),customerToAdd.getLastName(),customerToAdd.getBirthdate(),customerToAdd.getPassword(),customerToAdd.getLevel(),customerToAdd.getEmail(),customerToAdd.getCustomerAddress())){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Successfully added Customer");
                    alert.setHeaderText("Success");
                    alert.setContentText("Customer "+customerToAdd.getFirstName()+" has been added.");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        //TODO
                        //System.out.println(SysData.getInstance().getCustomers().toString());
                        Activities.closeWindow(t);
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
