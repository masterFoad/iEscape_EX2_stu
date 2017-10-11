package gui.controllers.forms.add;

import controller.SysData;
import gui.utils.NumberTextField;
import gui.utils.methods.Activities;
import gui.utils.methods.FormUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Address;
import model.Addressable;
import utils.E_Cities;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAddress implements Initializable {


    //  private Addressable toSetTheAddressTo;

    //    public AddAddress(Addressable a) {
    //        this.toSetTheAddressTo = a;
    //    }

    @FXML
    private ComboBox<E_Cities> cmbCities;


    @FXML
    private TextField txtStreet;

    @FXML
    private NumberTextField txtHouseNumber;

    @FXML
    private Button btnSetAddress;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Label errorCity;

    @FXML
    private Label errorHouseNumber;

    @FXML
    private Label errorStreet;

    @FXML
    private Label errorPhoneNumber;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<E_Cities> cities = FXCollections.observableArrayList(E_Cities.values());
        cmbCities.setItems(cities);


        Addressable toAddAddressTo = (Addressable)SysData.getInstance().getParameter("Addressable");

        /**
         * close window
         */
        btnCancel.setOnAction(e-> Activities.closeWindow(e));


        btnSetAddress.setOnAction(t -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successfully Added Address");
            alert.setHeaderText("Address");
            alert.setContentText("Successfully Added Address");


            if (cmbCities.getValue() == null) {
                errorCity.setText("*");
            } else {
                errorCity.setText("");
            }

            if (FormUtils.validateString(txtHouseNumber)) {
                errorHouseNumber.setText("");
            } else {
                errorHouseNumber.setText("*");
            }
            if (FormUtils.validateString(txtPhoneNumber) && txtPhoneNumber.getText().trim().length()==10) {
                errorPhoneNumber.setText("");
            } else {
                errorPhoneNumber.setText("*");
            }
            if (FormUtils.validateString(txtStreet)) {
                errorStreet.setText("");
            } else {
                errorStreet.setText("*");
            }



            if (errorHouseNumber.getText().equals("") &&
                    errorPhoneNumber.getText().equals("") &&
                    errorStreet.getText().equals("") &&
                    errorCity.getText().equals("")) {

                String[] phone = txtPhoneNumber.getText().trim().split("");

                        Address addressToAdd = new Address(((E_Cities)cmbCities.getValue()).getCountry()
                                ,(E_Cities)cmbCities.getValue(),txtStreet.getText().trim(),Integer.valueOf(txtHouseNumber.getText()),phone);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    toAddAddressTo.setTheAddress(addressToAdd);
                    SysData.getInstance().removeParameter("Addressable");

                    Activities.closeWindow(t);
                }

            }


        });

    }
}
