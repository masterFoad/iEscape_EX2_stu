package gui.controllers.forms.add;

import controller.SysData;
import gui.utils.methods.Activities;
import gui.utils.methods.FormUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customer;
import model.Employee;
import model.Instructor;
import utils.E_Levels;
import utils.E_Rooms;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class AddEmployee implements Initializable{

    @FXML
    private TextField txtFullName;

    @FXML
    private Label errorFullName;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Label errorBirthDate;

    @FXML
    private DatePicker startWorkingDatePicker;

    @FXML
    private Label errorWorkingDate;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label errorPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Button btnSetAddress;

    @FXML
    private Label errorAddress;

    @FXML
    private RadioButton instructorRadio;

    @FXML
    private ToggleGroup myGroup;

    @FXML
    private RadioButton receptionistRadio;

    @FXML
    private ComboBox<E_Levels> levelList;

    @FXML
    private Label errorLevel;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnExit;

    @FXML
    private ListView<E_Rooms> listRoomTypes;

    @FXML
    private Label errorRoom;

    @FXML
    private Label errorEmployee;

    @FXML
    private ScrollPane scrollPlat;

    @FXML
    private Label labelLevels;

    @FXML
    private Label labelRooms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Employee employeeToAdd = new Employee();

        levelList.setVisible(false);
        scrollPlat.setVisible(false);
        levelList.hide();
        listRoomTypes.setVisible(false);
        listRoomTypes.setDisable(true);
        labelLevels.setVisible(false);
        labelRooms.setVisible(false);


        SysData.getInstance().setParameter("Addressable",employeeToAdd);

        ObservableList<E_Levels> levels = FXCollections.observableArrayList(E_Levels.values());
        levelList.setItems(levels);

        ObservableList<E_Rooms> rooms = FXCollections.observableArrayList(E_Rooms.values());
        ListView<E_Rooms> selectedRooms = new ListView<>();
        listRoomTypes.setItems(rooms);
        listRoomTypes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listRoomTypes.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->
            selectedRooms.setItems(listRoomTypes.getSelectionModel().getSelectedItems())
        );




        receptionistRadio.setOnAction(e->{

            if(receptionistRadio.isSelected()) {
                levelList.hide();
                listRoomTypes.setDisable(true);
                levelList.setVisible(false);
                scrollPlat.setVisible(false);
                labelLevels.setVisible(false);
                labelRooms.setVisible(false);
                levelList.hide();
                listRoomTypes.setVisible(false);
                listRoomTypes.setDisable(false);

            }
            if(receptionistRadio.isSelected())
                listRoomTypes.setOnMouseEntered(t->{

                    listRoomTypes.setTooltip(new Tooltip("Only available for Instructors."));

                });
            else{
                listRoomTypes.setTooltip(new Tooltip("Multiple Selection"));
                levelList.setVisible(true);
                scrollPlat.setVisible(true);

                labelLevels.setVisible(true);
                labelRooms.setVisible(true);
                listRoomTypes.setVisible(true);
                listRoomTypes.setDisable(false);
            }

        });

        instructorRadio.setOnAction(e->{

            System.out.println("here");

            if(instructorRadio.isSelected()) {
                levelList.show();
                listRoomTypes.setDisable(false);
                listRoomTypes.setTooltip(new Tooltip("Multiple Selection"));
                levelList.setVisible(true);
                scrollPlat.setVisible(true);
                levelList.hide();
                listRoomTypes.setVisible(true);
                listRoomTypes.setDisable(false);
                labelLevels.setVisible(true);
                labelRooms.setVisible(true);
            }
            if(receptionistRadio.isSelected())
                listRoomTypes.setOnMouseEntered(t->{

                    listRoomTypes.setTooltip(new Tooltip("Only available for Instructors."));

                    listRoomTypes.setTooltip(new Tooltip("Multiple Selection"));
                    listRoomTypes.setTooltip(new Tooltip("Multiple Selection"));
                    levelList.setVisible(false);
                    scrollPlat.setVisible(false);
                    labelLevels.setVisible(false);
                    labelRooms.setVisible(false);
                    levelList.hide();
                    listRoomTypes.setVisible(false);
                    listRoomTypes.setDisable(false);
                });
            else{
                levelList.show();
                listRoomTypes.setDisable(false);
                listRoomTypes.setTooltip(new Tooltip("Multiple Selection"));
                levelList.setVisible(true);
                scrollPlat.setVisible(true);
                levelList.hide();
                listRoomTypes.setVisible(true);
                listRoomTypes.setDisable(false);
                labelLevels.setVisible(true);
                labelRooms.setVisible(true);
            }

        });

        SysData.getInstance().setParameter("Addressable",employeeToAdd);

        btnExit.setOnAction(t-> Activities.closeWindow(t));


        btnSetAddress.setOnAction(t -> {
            try {
                Activities.openNewWindow(this,"/gui/views/addAddress.fxml","Add Address");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        btnAddEmployee.setOnAction(t -> {


            if(FormUtils.validateString(txtFullName)) {
                setEmployeeName(employeeToAdd);
                errorFullName.setText("");
            }
            else{
                errorFullName.setText("*");
            }


            if(FormUtils.validateString(txtPassword)
                    && FormUtils.validateString(txtConfirmPassword)
                    && txtPassword.getText().equals(txtConfirmPassword.getText())) {
                employeeToAdd.setPassword(txtPassword.getText());
                errorPassword.setText("");
            }
            else{
                errorPassword.setText("*");
            }



            if(instructorRadio.isSelected()) {
                if (levelList.getValue() != null) {
                    errorLevel.setText("");
                } else {
                    errorLevel.setText("*");
                }
            }else{
                errorLevel.setText("");
            }


            if(birthDatePicker.getValue()!=null){
                employeeToAdd.setBirthdate(FormUtils.dateFromDatePicker(birthDatePicker));
                errorBirthDate.setText("");
            }else{
                errorBirthDate.setText("*");
            }

            if(startWorkingDatePicker.getValue()!=null){
                employeeToAdd.setBirthdate(FormUtils.dateFromDatePicker(startWorkingDatePicker));
                errorBirthDate.setText("");
            }else{
                errorBirthDate.setText("*");
            }

            if(instructorRadio.isSelected() || receptionistRadio.isSelected()){
                errorEmployee.setText("");
            }else{
                errorEmployee.setText("*");
            }



            if(employeeToAdd.getAddress()==null){
                errorAddress.setText("*");
            }else{
                errorAddress.setText("");
            }
            if(instructorRadio.isSelected()) {
                if (selectedRooms.getSelectionModel().getSelectedItems().size() > 0) {
                    errorRoom.setText("");
                } else {
                    errorRoom.setText("*");
                }
            }else{
                errorRoom.setText("");
            }

            if(
                    errorEmployee.getText().equals("")&&
                            errorAddress.getText().equals("") &&
                            errorPassword.getText().equals("") &&
                            errorFullName.getText().equals("") &&
                            errorBirthDate.getText().equals("") &&
                            errorWorkingDate.getText().equals("") &&
                                    errorRoom.getText().equals("") &&
                            errorLevel.getText().equals("") ){

                //TODOD
                if(instructorRadio.isSelected()) {
                    Instructor insToAdd = ((Instructor) employeeToAdd);
                    insToAdd.setLevel(levelList.getValue().getLevel());

                    HashSet<E_Rooms> roomSet= new HashSet<>(selectedRooms.getSelectionModel().getSelectedItems());

                    if(SysData.getInstance().addInstructor(Integer.valueOf(FormUtils.generateId()),insToAdd.getFirstName(),insToAdd.getLastName(),insToAdd.getBirthdate(),insToAdd.getStartWorkingDate(),insToAdd.getPassword(),insToAdd.getLevel(),insToAdd.getAddress(),roomSet.toArray(new E_Rooms[roomSet.size()]))){

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Successfully added Instructor");
                        alert.setHeaderText("Success");
                        alert.setContentText("Instructor "+insToAdd.getFirstName()+" has been added.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            //TODO
                            //System.out.println(SysData.getInstance().getCustomers().toString());
                        }


                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Failed to add Instructor");
                        alert.setHeaderText("Error");
                        alert.showAndWait();
                    }


                }
                else   if(receptionistRadio.isSelected()) {
                    if(SysData.getInstance().addReceptionist(Integer.valueOf(FormUtils.generateId()),employeeToAdd.getFirstName(),employeeToAdd.getLastName(),employeeToAdd.getBirthdate(),employeeToAdd.getStartWorkingDate(),employeeToAdd.getPassword(),employeeToAdd.getAddress())){

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Successfully added Receptionist");
                        alert.setHeaderText("Success");
                        alert.setContentText("Instructor "+employeeToAdd.getFirstName()+" has been added.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            //TODO
                            //System.out.println(SysData.getInstance().getCustomers().toString());
                        }


                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Failed to add Receptionist");
                        alert.setHeaderText("Error");
                        alert.showAndWait();
                    }



                }

            }






        });


    }



    private void setEmployeeName(Employee employee)
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
        employee.setFirstName(firstName);
        employee.setLastName(lastName.toString());

    }
}
