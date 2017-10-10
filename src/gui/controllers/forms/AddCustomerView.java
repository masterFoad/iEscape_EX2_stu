package gui.controllers.forms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customer;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerView implements Initializable {


    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private Button btnAddCustomer;




//    @FXML
//    protected void addCustomer(ActionEvent event) {
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtFirstName.setText("");
        txtLastName.setText("");
        btnAddCustomer.setOnAction(t -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("TEST");
            alert.setContentText("TEST");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
             //TODO
            }
        });

    }
}
