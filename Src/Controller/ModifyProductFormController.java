package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Part;
import model.Product;
import model.Inventory;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

import static main.Main.inventory;

public class ModifyProductFormController {

    @FXML
    private TableColumn<Part, Integer> ModfiyProdFormAddedPartIdCol;

    @FXML
    private TableColumn<Part, Integer> ModfiyProdFormAddedPartInvLevelCol;

    @FXML
    private TableColumn<Part, String> ModfiyProdFormAddedPartNameCol;

    @FXML
    private TableColumn<Part, Double> ModfiyProdFormAddedPartPriceCol;

    @FXML
    private Button ModifyProdAddButton;

    @FXML
    private TextField ModifyProdFormAutoGenIDField;

    @FXML
    private Button ModifyProdFormCancelBtn;

    @FXML
    private TableColumn<Part, Integer> ModifyProdFormPartIdToAddCol;

    @FXML
    private TableColumn<Part, String> ModifyProdFormPartNamToAddCol;

    @FXML
    private TableView<Part> ModifyProdFormPartsToAddTable;

    @FXML
    private Button ModifyProdFormSaveBtn;

    @FXML
    private TextField ModifyProdInv;

    @FXML
    private TextField ModifyProdMax;

    @FXML
    private TextField ModifyProdMin;

    @FXML
    private TextField ModifyProdName;

    @FXML
    private TableColumn<Part, Integer> ModifyProdPartToAddInvLevelCol;

    @FXML
    private TableColumn<Part, Double> ModifyProdPartToAddPriceCol;

    @FXML
    private TextField ModifyProdPrice;

    @FXML
    private Button ModifyProdRemoveAssoPartBTN;

    @FXML
    private TextField ModifyProdSearchPartsToadd;

    @FXML
    private TableView<Part> ModifyProdTableAddedPartsTable;

    @FXML
    public void getModifyProduct(Product importedProduct) {
        System.out.println("Hi");
        ModifyProdFormAutoGenIDField.setText(String.valueOf(importedProduct.getId()));
        ModifyProdFormAutoGenIDField.setFocusTraversable(false);
        ModifyProdPrice.setText(String.valueOf(importedProduct.getPrice()));
        ModifyProdInv.setText(String.valueOf(importedProduct.getStock()));
        ModifyProdMax.setText(String.valueOf((importedProduct.getMax())));
        ModifyProdMin.setText(String.valueOf((importedProduct.getMin())));
        ModifyProdName.setText(String.valueOf((importedProduct.getName())));

        ModifyProdFormPartsToAddTable.setItems(inventory.getAllParts());
        ModifyProdFormPartIdToAddCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProdFormPartNamToAddCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProdPartToAddInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyProdPartToAddPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ModifyProdTableAddedPartsTable.setItems(importedProduct.getAllAssociatedParts());
        ModfiyProdFormAddedPartIdCol.setCellValueFactory((new PropertyValueFactory<>("id")));
        ModfiyProdFormAddedPartNameCol.setCellValueFactory((new PropertyValueFactory<>("name")));
        ModfiyProdFormAddedPartInvLevelCol.setCellValueFactory((new PropertyValueFactory<>("stock")));
        ModfiyProdFormAddedPartPriceCol.setCellValueFactory((new PropertyValueFactory<>("price")));

    }

    public <stage> void ModifyProdFormCancelBtnPress(ActionEvent event) throws IOException {
        returnToMainScreen(event);
    }

    void returnToMainScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainForm.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene MainFormScene = new Scene(root);
        stage.setScene(MainFormScene);
        stage.show();
    }

    public void removeAssociatedPartBtnPress(ActionEvent event) throws IOException {
        if (ModifyProdTableAddedPartsTable.getSelectionModel().getSelectedItem() != null) {
            // Tyler was here
            // importedProduct.deleteAssociatedPart(ModifyProdTableAddedPartsTable.getSelectionModel().getSelectedItem());
        } else
            AlertHandling(1);
    }

    private void AlertHandling(int num) {
        switch (num) {
            case 1:
                Alert MachineIdAlert = new Alert(Alert.AlertType.ERROR);
                MachineIdAlert.setTitle("No Part Selected");
                MachineIdAlert.setHeaderText("Please select associated Part to remove from Bill of Materials");
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
}
