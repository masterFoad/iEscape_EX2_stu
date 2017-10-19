package gui.controllers.forms.add;

import com.jfoenix.controls.JFXButton;
import controller.SysData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Customer;
import model.Receptionist;
import model.Subscription;
import gui.utils.TypeConstants;


import java.net.URL;
import java.util.*;


public class SubscriptionsList implements Initializable {
    @FXML
    private HBox toolbar;
    @FXML
    private TableView<Subscription> tableView;
    @FXML
    private JFXButton pop;
    @FXML
    private JFXButton export;
    @FXML
    private JFXButton add;

    private List<TableColumn<Subscription, String>> columns;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        initTable();


    }


    private String[] tableColumns() {

        String[] arr = {
                "id",
                "firstName",
                "lastName",
                "start Date",
                "period",
                "view Customer"
        };
        return arr;

    }

    private void initTable() {

        setTableColumns();
        tableView.setItems(FXCollections.observableArrayList(dataSource()));
    }

    private void setTableColumns() {
        int index = 0;
        columns = new ArrayList<>();
        for (String columnName : tableColumns()) {

            columns.add(new TableColumn<>(columnName));
            columns.get(index).setCellFactory(cellFactoryValueForIndex(index));
            columns.get(index).setResizable(true);
            columns.get(index).setMinWidth(200);
            index++;

        }

        tableView.getColumns().addAll(columns);
    }

    /*"id",
    "firstName",
    "lastName","view Customer
    period
    startDate*/
    public Callback cellFactoryValueForIndex(int index) {
        switch (index) {
            case 0:
                /*            "id",
                            "firstName",
                        "lastName", "view Customer"*/
                return (Callback<TableColumn.CellDataFeatures<Subscription, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty("" + param.getValue().getNumber());
            case 1:
                return (Callback<TableColumn.CellDataFeatures<Subscription, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty("" + param.getValue().getCustomer().getFirstName());
            case 2:
                return (Callback<TableColumn.CellDataFeatures<Subscription, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty("" + param.getValue().getCustomer().getLastName());
            case 3:
                return (Callback<TableColumn.CellDataFeatures<Subscription, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty("" + param.getValue().getStartDate());
            case 4:
                return (Callback<TableColumn.CellDataFeatures<Subscription, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty("" + param.getValue().getPeriod());
            default:
                return (Callback<TableColumn.CellDataFeatures<Subscription, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty("" );

        }
    }


    public int numberOfColumns() {
        return tableColumns().length;
    }


    public Collection<? extends Subscription> dataSource() {
        Receptionist receptionist = (Receptionist) SysData.getInstance().getParameter(TypeConstants.LOGGED_RECEPTIONIST);
        Customer customer = (Customer) SysData.getInstance().getParameter(TypeConstants.LOGGED_CUSTOMER);
        if (customer != null) {
            //TODO : get customer subscriptions
            return customer.getSubs().values();
        } else {
            //TODO : get receptionist subscriptions
            if (receptionist != null) {
                return receptionist.getSubscriptions().values();
            }
        }
        //TODO : handle null pointer exception
        return null;
    }


}
