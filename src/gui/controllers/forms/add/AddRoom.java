package gui.controllers.forms.add;

import controller.SysData;
import gui.utils.methods.Activities;
import gui.utils.methods.FormUtils;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Branch;
import utils.E_Levels;
import utils.E_Rooms;

import java.net.URL;
import java.util.*;

public class AddRoom implements Initializable{

    @FXML
    private TextField txrRoomName;

    @FXML
    private Label maxPartValue;

    @FXML
    private Slider sliderMaxPart;

    @FXML
    private Label minPartValue;

    @FXML
    private Slider sliderMinPart;

    @FXML
    private Label timeLimitValue;

    @FXML
    private Slider timeLimitSlider;

    @FXML
    private Label labelLevels;

    @FXML
    private ComboBox<E_Levels> levelList;

    @FXML
    private Label errorLevel;

    @FXML
    private Label errorBranch;

    @FXML
    private ScrollPane scrollPlat2;

    @FXML
    private ListView<Branch> listBranches;

    @FXML
    private Label errorRoom;

    @FXML
    private ScrollPane scrollPlat;

    @FXML
    private ListView<E_Rooms> listRoomTypes;

    @FXML
    private Button btnAddRoom;

    @FXML
    private Button btnExit;

    @FXML
    private Label errorRoomName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ObservableList<E_Levels> levels = FXCollections.observableArrayList(E_Levels.values());
        levelList.setItems(levels);

        ObservableList<E_Rooms> rooms = FXCollections.observableArrayList(E_Rooms.values());
        ListView<E_Rooms> selectedRooms = new ListView<>();
        listRoomTypes.setItems(rooms);


        ObservableList<Branch> branches = null;
        if (SysData.getInstance().getBranches() != null) {
            branches = FXCollections.observableArrayList(SysData.getInstance().getBranches());
            listBranches.setItems(branches);
        }
        ListView<Branch> selectedBranches = new ListView<>();

        /*Branch
            level
            room
         */
        listBranches.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listBranches.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
                    if (listBranches.getSelectionModel() != null)
                        selectedBranches.setItems(listBranches.getSelectionModel().getSelectedItems());

                }
        );

        btnExit.setOnAction(t -> Activities.closeWindow(t));


        sliderMinPart.valueProperty().addListener((obs, oldval, newVal) ->
                {
                    sliderMinPart.setValue(newVal.intValue());
                    minPartValue.setText(newVal.intValue() +" Participants.");
                    if (sliderMinPart.getValue() >= sliderMaxPart.getValue()) {
                        sliderMaxPart.setValue(newVal.intValue());
                    }
                }

        );

        sliderMaxPart.valueProperty().addListener((obs, oldval, newVal) ->
                {
                    sliderMaxPart.setValue(newVal.intValue());
                    maxPartValue.setText(newVal.intValue() +" Participants.");
                    if (sliderMaxPart.getValue() < sliderMinPart.getValue()) {
                        sliderMinPart.setValue(newVal.intValue());
                    }
                }

        );

        timeLimitSlider.valueProperty().addListener((obs, oldval, newVal) -> {

                timeLimitSlider.setValue(newVal.intValue());
                 timeLimitValue.setText(newVal.intValue() + " minutes.");
         });

        btnAddRoom.setOnAction(e->
                {

                    if(FormUtils.validateString(txrRoomName)){
                        errorRoomName.setText("");
                    }
                    else{
                        errorRoomName.setText("*");
                    }

                    if (levelList.getValue() != null) {
                        errorLevel.setText("");
                    } else {
                        errorLevel.setText("*");
                    }

                    if (selectedRooms.getSelectionModel().getSelectedItems().size() > 0) {
                        errorRoom.setText("");
                    } else {
                        errorRoom.setText("*");
                    }

                    if (selectedBranches.getSelectionModel().getSelectedItems().size() > 0) {
                        errorBranch.setText("");
                    } else {
                        errorBranch.setText("*");
                    }

                    if(
                                    errorRoomName.getText().equals("") &&
                                    errorBranch.getText().equals("") &&
                                    errorRoom.getText().equals("") &&
                                    errorLevel.getText().equals("") ){

                        if(SysData.getInstance().addRoomToBranch(Integer.valueOf(FormUtils.generateId()),txrRoomName.getText().trim(),(int)sliderMaxPart.getValue(),(int)sliderMinPart.getValue(),(int)timeLimitSlider.getValue(),levelList.getValue(),listRoomTypes.getSelectionModel().getSelectedItem(),listBranches.getSelectionModel().getSelectedItem().getBranchNumber())){

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Successfully added Room to Branch");
                                alert.setHeaderText("Success");
                                alert.setContentText("Room "+txrRoomName.getText().trim()+" has been added.");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                    //TODO
                                    //System.out.println(SysData.getInstance().getCustomers().toString());
                                }


                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Failed to add Room");
                                alert.setHeaderText("Error");
                                alert.showAndWait();
                            }




                    }


                }
        );



    }
}
