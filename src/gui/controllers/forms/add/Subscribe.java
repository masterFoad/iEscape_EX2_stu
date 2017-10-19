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
import model.Customer;
import model.Receptionist;
import utils.E_Cities;
import utils.E_Levels;
import utils.E_Periods;
import model.Subscription;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.utils.TypeConstants;
public class Subscribe implements Initializable {


    @FXML
    private ComboBox<E_Periods> periodComboBox;

    @FXML
    private Label errorLevel;

    @FXML
    private Button subscribeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Customer customer = (Customer)SysData.getInstance().getParameter("selectedCustomer");

        Receptionist receptionist = (Receptionist)SysData.getInstance().getParameter(TypeConstants.LOGGED_RECEPTIONIST);

        //TODO : check what happened id the number starts with 0s e.g 055  = 55 or 055 ? important
        int subscriptionId = Integer.parseInt(FormUtils.generateId());
        Date d = new Date();
        Subscription s = new  Subscription( subscriptionId,  customer,  receptionist, E_Periods.HALF,d );


        ObservableList<E_Periods> periods = FXCollections.observableArrayList(E_Periods.values());

        periodComboBox.setItems(periods);
        periodComboBox.getSelectionModel();


        subscribeButton.setOnAction(t -> {
            System.out.println("periodComboBox " + periodComboBox.getSelectionModel());
        });
//        errorLevel.setText("*");
    }
}
