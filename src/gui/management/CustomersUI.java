package gui.management;

import com.jfoenix.controls.JFXButton;
import controller.SysData;
import gui.SceneController;
import gui.utils.Acts;
import gui.utils.TypeConstants;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Address;
import model.Customer;
import utils.E_Levels;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static gui.utils.Commons.setEditingCustomerOff;
import static gui.utils.Commons.setEditingCustomerOn;
import static utils.E_Cities.Herzliya;


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
        setSelectionListener();
        doubleClickListener();


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
        };
        return arr;

    }

    private void initTable(){
        setTableColumns();
        System.out.println(dataSource().toString());
        tableView.setItems(FXCollections.observableArrayList(dataSource()));

    }

    private void setSelectionListener(){

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ObservableList<Customer> selectedCustomers = tableView.getSelectionModel().getSelectedItems();

                SysData.getInstance().setParameter(TypeConstants.SELECTED_CUSTOMERS,selectedCustomers.stream().collect(Collectors.toList()));
               // System.out.println( SysData.getInstance().getParameter(TypeConstants.SELECTED_CUSTOMERS));
            }
        });

    }

    private void doubleClickListener(){

        tableView.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                setEditingCustomerOn();
                    ObservableList<Customer> selectedCustomers = tableView.getSelectionModel().getSelectedItems();

                    SysData.getInstance().setParameter(TypeConstants.SELECTED_CUSTOMERS,selectedCustomers.stream().collect(Collectors.toList()));
                    // System.out.println( SysData.getInstance().getParameter(TypeConstants.SELECTED_CUSTOMERS));

                try {
                    SceneController.getInstance().popUp(Acts.getScene(Acts.ADD_CUSTOMER), "Edit Customer");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setTableColumns() {
        int index = 0;
        columns = new ArrayList<>();
        for (String columnName : tableColumns()) {

            columns.add(new TableColumn<>(columnName));
            columns.get(index).setCellValueFactory(cellFactoryValueForIndex(index));
            columns.get(index).setResizable(true);
            columns.get(index).setMinWidth(200);

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


            }
            return null;
        }


    private void setupAddButton() {


        add.setOnAction(event -> {
            try {
                setEditingCustomerOff();
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
