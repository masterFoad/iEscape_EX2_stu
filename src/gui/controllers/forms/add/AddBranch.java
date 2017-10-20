package gui.controllers.forms.add;

import controller.SysData;
import gui.utils.methods.Activities;
import gui.utils.methods.FormUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Branch;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddBranch implements Initializable{

    @FXML
    private TextField txtBranchName;

    @FXML
    private Label errorBranchName;

    @FXML
    private Button btnSetAddress;

    @FXML
    private Label errorAddress;

    @FXML
    private Button btnAddBranch;

    @FXML
    private Button btnExit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Branch bToAdd = new Branch();
        SysData.getInstance().setParameter("Addressable",bToAdd);

        errorBranchName.requestFocus();

        btnExit.setOnAction(t->Activities.closeWindow(t));

        btnSetAddress.setOnAction(t -> {


            try {
                Activities.openNewWindow(this,"/gui/views/addAddress.fxml","Add Address");
            } catch (IOException e) {
                e.printStackTrace();
            }



        });


        btnAddBranch.setOnAction(t ->{

            if(FormUtils.validateString(txtBranchName)){
                bToAdd.setBranchName(txtBranchName.getText().trim());
                errorBranchName.setText("");
            }
            else{
                errorBranchName.setText("*");
            }

            if(bToAdd.getBranchAddress()==null){
                errorAddress.setText("*");
            }else{
                errorAddress.setText("");
            }


            if(errorAddress.getText().equals("") && errorBranchName.getText().equals("")){

                if(SysData.getInstance().addBranch(Integer.valueOf(FormUtils.generateId()),bToAdd.getBranchName(),bToAdd.getBranchAddress().getCity(),bToAdd.getBranchAddress().getCountry(),bToAdd.getBranchAddress().getStreet(),bToAdd.getBranchAddress().getHousNumber(),bToAdd.getBranchAddress().getPhoneNumber())){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Successfully added Branch");
                    alert.setHeaderText("Success");
                    alert.setContentText("Branch "+bToAdd.getBranchName()+" has been added.");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        //TODO
                        Activities.closeWindow(t);
                        //System.out.println(SysData.getInstance().getCustomers().toString());
                    }


                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed to add Branch");
                    alert.setHeaderText("Error");
                    alert.showAndWait();
                }


            }


        });





    }


}
