package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;

import java.io.IOException;
import java.util.Objects;

import static main.Main.inventory;

public class AddPartFormController {

    @FXML
    private TextField AddMachineOrCompanyTextField;

    @FXML
    private TextField AddPartInv;

    @FXML
    private TextField AddPartMax;

    @FXML
    private TextField AddPartMin;

    @FXML
    private TextField AddPartName;

    @FXML
    private TextField AddPartPrice;

    @FXML
    private ToggleGroup AddPartRadio;

    @FXML
    private Text MachineOrCompanyName;

    @FXML
    private RadioButton addPartInHouseBtn;

    @FXML
    private RadioButton addPartOutsourcedBtn;

    @FXML
    void AddRadioSelected(ActionEvent event) {
        if (addPartOutsourcedBtn.isSelected()) {
            MachineOrCompanyName.setText("Company Name");
        } else if (addPartInHouseBtn.isSelected()) {
            MachineOrCompanyName.setText("MachineID");
        }
    }


    @FXML
    void AddPartSaveButton(ActionEvent event) throws IOException {
        try {

            boolean validPartAdded = false;
            int id = getRandomPartID();
            double price = Double.parseDouble(AddPartPrice.getText());
            int inv = Integer.parseInt(AddPartInv.getText());
            int min = Integer.parseInt(AddPartMin.getText());
            int max = Integer.parseInt(AddPartMax.getText());
            String name = AddPartName.getText();
            int machineID;
            String companyName;

            if(checkMin(min,max) && (checkInv(inv,min,max))) {

                if(addPartInHouseBtn.isSelected()){
                    try{
                        machineID = Integer.parseInt(AddMachineOrCompanyTextField.getText());
                        InHouse NewInHousePart = new InHouse(id,name,price,inv,min,max,machineID);
                        inventory.addPart(NewInHousePart);
                        validPartAdded = true;
                    } catch (Exception e){
                        AlertHandling(1);
                    }
                }
                if(addPartOutsourcedBtn.isSelected()){
                        companyName = AddMachineOrCompanyTextField.getText();
                        Outsourced NewOutSourcedPart= new Outsourced(id,name,price,inv,min,max,companyName);
                        inventory.addPart(NewOutSourcedPart);
                        validPartAdded = true;
                    }
                if(validPartAdded){
                    returnToMainScreen(event);
                }
                } else{
                AlertHandling(2);
            }

        } catch (Exception e){
            AlertHandling(2);
        }
    }



        @FXML
        public <stage > void AddPartCancelBtn (ActionEvent event) throws IOException {
            returnToMainScreen(event);
        }

        void returnToMainScreen (ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainForm.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene MainFormScene = new Scene(root);
            stage.setScene(MainFormScene);
            stage.show();
        }

        private void AlertHandling ( int num){
            switch (num) {
                case 1:
                    Alert MachineIdAlert = new Alert(Alert.AlertType.ERROR);
                    MachineIdAlert.setTitle("Invalid Machine ID");
                    MachineIdAlert.setHeaderText("Machine ID must only contain Integers");
                    MachineIdAlert.show();
                    break;
                case 2:
                    Alert General = new Alert(Alert.AlertType.ERROR);
                    General.setTitle("Invalid or Missing Values");
                    General.setHeaderText("Fields either contain missing or invalid values\n" +
                            "make sure min value is less than max value");
                    General.show();
                    break;
                case 3:
                    Alert EmptyFields = new Alert(Alert.AlertType.ERROR);
                    EmptyFields.setTitle("Fields invalid or missing Values");
                    EmptyFields.setHeaderText("Form contains invalid or empty Fields");
                    EmptyFields.show();
            }
        }

        private boolean checkInv ( int inv, int min, int max){
            return (inv >= min) && (inv <= max);
        }

        private boolean checkMin ( int min, int max){
            return min <= max;
        }

        private int getRandomPartID () {
           int tempId=1;
            while (inventory.lookupPart(tempId) != null) {
                tempId = (int) (Math.random() * (1000));
            }
            return tempId;
        }
    }




