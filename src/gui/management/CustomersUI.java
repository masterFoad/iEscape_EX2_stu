package gui.management;

import com.jfoenix.controls.JFXButton;
import controller.SysData;
import gui.SceneController;
import gui.utils.Acts;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;


public class CustomersUI implements Initializable{
    @FXML
    private HBox toolbar;
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private JFXButton pop;
    @FXML
    private JFXButton export;
    @FXML
    private JFXButton add;

    private List<TableColumn<Customer, String>> columns;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        initTable();
        setupAddButton();


    }

    private String getFxmlPath(){
        return ""+"/gui/views/customersUI.fxml";
    }

    private String getAddButtonPath(){
        return ""+"/gui/views/addCustomerView.fxml";
    }



    private String[] tableColumns(){

        String [] arr = {
        "id",
        "firstName",
        "lastName",
        "birthdate",
        "level",
        "Email",
        "customerAddress"};
        return arr;

    }

    private void initTable(){
        setTableColumns();
        tableView.setItems(FXCollections.observableArrayList(dataSource()));

    }

    private void setTableColumns() {
        int index = 0;
        columns = new ArrayList<>();
        for (String columnName : tableColumns()) {

            columns.add(new TableColumn<>(columnName));
            columns.get(0).setCellFactory(cellFactoryValueForIndex(0));
            columns.get(0).setResizable(true);
            columns.get(0).setMinWidth(200);

            index++;

        }

        tableView.getColumns().addAll(columns);
    }

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


    private void setupAddButton() {


        add.setOnAction(event -> {
            try {
                SceneController.getInstance().popUp(Acts.getScene(Acts.ADD_CUSTOMER), "Add Customer");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    public int numberOfColumns() {
        return 6;
    }


    public Collection<? extends Customer> dataSource() {
        return SysData.getInstance().getCustomers();
    }


}
