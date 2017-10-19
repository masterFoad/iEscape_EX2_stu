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


import java.net.URL;
import java.util.*;


public class SubscriptionsList implements Initializable{
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


    private String[] tableColumns(){

        String [] arr = {
        "id",
        "firstName",
        "lastName","view Customer"};
        return arr;

    }

    private void initTable(){

        Receptionist receptionist = (Receptionist)SysData.getInstance().getParameter("loggedReceptionist");


        setTableColumns(receptionist);
        tableView.setItems(FXCollections.observableArrayList(dataSource()));

    }

    private void setTableColumns( Receptionist receptionist ) {
        HashMap<Integer, Subscription> subs = receptionist.getSubscriptions();

        int index = 0;
        columns = new ArrayList<>();
        for (String columnName : tableColumns()) {

            columns.add(new TableColumn<>(columnName));
            columns.get(index).setCellFactory(cellFactoryValueForIndex(0));
            columns.get(index).setResizable(true);
            columns.get(index).setMinWidth(200);
            index++;

        }

        tableView.getColumns().addAll(columns);
    }
            /*"id",
            "firstName",
            "lastName","view Customer*/
        public Callback cellFactoryValueForIndex(int index) {
            switch (index){
                case 0:
                    return (Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(""+param.getValue().getId());
                case 1:
                    return (Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(""+param.getValue().getFirstName());
                case 2:
                    return (Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(""+param.getValue().getLastName());
                case 3:
                    return (Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(""+param.getValue().getBirthdate());
                case 4:
                    return (Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(""+param.getValue().getLevel());
                case 5:
                    return (Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(""+param.getValue().getEmail());
                case 6:
                    return (Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(""+param.getValue().getCustomerAddress());

            }
            return null;
        }




    public int numberOfColumns() {
        return 6;
    }


    public Collection<? extends Subscription> dataSource() {
        Receptionist receptionist = (Receptionist)SysData.getInstance().getParameter();

        return receptionist.getSubscriptions();
    }


}
