package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.util.Objects;

import static main.Main.inventory;

//Controller to handle both the 'add part form' and 'modify part form'.
public class ModifyPartFormController {

    private Part importedPart;

    @FXML
    private RadioButton InHouseRadio;

    @FXML
    private Text InOrOutDisplayName;

    @FXML
    private TextField InOrOutTextField;

    @FXML
    private ToggleGroup ModifyPart;

    @FXML
    private RadioButton OutsourcedRadio;

    @FXML
    private Button PartCancelBtn;

    @FXML
    private TextField PartId;

    @FXML
    private TextField PartInv;

    @FXML
    private TextField PartMax;

    @FXML
    private TextField PartMin;

    @FXML
    private TextField PartName;

    @FXML
    private TextField PartPrice;

    @FXML
    private Button SaveButton;

    @FXML
    void GetPartType(ActionEvent event) {
        if (OutsourcedRadio.isSelected()) {
            InOrOutDisplayName.setText("Company Name");
        } else if (InHouseRadio.isSelected()) {
            InOrOutDisplayName.setText("MachineID");
        }
    }

    //Cancel button to redirect scene to main form.
    public <stage> void PartCancelBtn(ActionEvent event) throws IOException {
        returnToMainScreen(event);
    }

    public void getModifyPart(Part importedPart) {
        if (importedPart instanceof Outsourced) {
            OutsourcedRadio.setSelected(true);
            InOrOutDisplayName.setText("Company Name");
            InOrOutTextField.setText(String.valueOf(((Outsourced) importedPart).getCompanyName()));
        } else if (importedPart instanceof InHouse) {
            InHouseRadio.setSelected(true);
            InOrOutDisplayName.setText("MachineID");
            InOrOutTextField.setText(String.valueOf(((InHouse) importedPart).getMachineId()));
        }
        PartId.setText(String.valueOf(importedPart.getId()));
        PartId.setFocusTraversable(false);
        PartPrice.setText(String.valueOf(importedPart.getPrice()));
        PartInv.setText(String.valueOf(importedPart.getStock()));
        PartMax.setText(String.valueOf((importedPart.getMax())));
        PartMin.setText(String.valueOf((importedPart.getMin())));
        PartName.setText(String.valueOf((importedPart.getName())));
    }

    public void PartSaveButton(ActionEvent event) throws IOException {
        boolean validPartAdded = false;
        int id = Integer.parseInt(PartId.getText());
        double price = Double.parseDouble(PartPrice.getText());
        int inv = Integer.parseInt(PartInv.getText());
        int min = Integer.parseInt(PartMin.getText());
        int max = Integer.parseInt(PartMax.getText());
        String name = PartName.getText();
        int index = (inventory.getAllParts().indexOf(inventory.lookupPart(id)));

        if (checkInv(inv, min, max) && checkMin(min, max)) {
            if (OutsourcedRadio.isSelected()) {
                String companyName = InOrOutTextField.getText();
                InOrOutDisplayName.setText("Company Name");
                Outsourced newOutSourcedPart = new Outsourced(id, name, price, inv, min, max, companyName);
               inventory.updatePart(index, newOutSourcedPart);
                validPartAdded = true;
            } if (InHouseRadio.isSelected()) {
                try {
                    InOrOutDisplayName.setText("MachineID");
                    int machineId = Integer.parseInt(InOrOutTextField.getText());
                    InHouse newInHousePart = new InHouse(id, name, price, inv, min, max, machineId);
                    inventory.updatePart(index, newInHousePart);
                    validPartAdded = true;
                } catch (Exception e){
                    AlertHandling(1);
                }
            }
            if (validPartAdded) {
                returnToMainScreen(event);
            }
        } else {
            AlertHandling(2);
        }
    }

    void returnToMainScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainForm.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene MainFormScene = new Scene(root);
        stage.setScene(MainFormScene);
        stage.show();
    }

    private void AlertHandling(int num) {
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
        }
    }

    private boolean checkInv(int inv, int min, int max) {
        boolean check = (inv >= min) && inv <= max;
        return check;
    }

    private boolean checkMin(int min, int max) {
        boolean check = min <= max;
        return check;
    }
}