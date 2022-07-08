package Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

import static main.Main.inventory;

/***
 * Controller to handle Main Scene.
 */
public class MainSceneController {
    private Parent root;
    @FXML
    private AnchorPane MainScenePane;
    private Stage stage;
    private Parent root1;

    @FXML
    private TableColumn<Part, Integer> PartsInvLevlCol;

    @FXML
    private TableView<Part> PartsTable;

    @FXML
    private TableColumn<Part, Double> PartscostCol;

    @FXML
    private TableColumn<Product, Double> ProdCostCol;

    @FXML
    private TableColumn<Product, Integer> ProdIDCol;

    @FXML
    private TableColumn<Product, Integer> ProdInvLvlCol;

    @FXML
    private TableColumn<Product, String> ProdNameCol;

    @FXML
    private TableView<Product> ProductsTable;

    @FXML
    private TextField mainSearchPartField;

    @FXML
    private TextField mainSearchProductsField;

    @FXML
    private TableColumn<Part, String> partsPartNameCol;

    @FXML
    private TableColumn<Part, Integer> partsPartidcol;


    /***
     * Button to redirect scene to 'add part form' scene.
     */
    public <stage> void mainOpenAddPartForm(ActionEvent openAddPartForm) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/addPartForm.fxml")));
        stage = (Stage)((Node)openAddPartForm.getSource()).getScene().getWindow();
        Scene addPartFormscene = new Scene(root);
        stage.setScene(addPartFormscene);
        stage.show();
    }

    /***
     * button to redirect scene to 'modify part form' scene
     */
    public void mainOpenModifyPartForm(ActionEvent openModifyPartForm) throws IOException {
        Part selectedPart = PartsTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            AlertHandling(0);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/modifyPartForm.fxml"));
            root = loader.load();

            ModifyPartFormController add_modifyPartFormController = loader.getController();
            add_modifyPartFormController.getModifyPart(selectedPart);

            stage = (Stage) ((Node) openModifyPartForm.getSource()).getScene().getWindow();
            Scene addPartFormScene = new Scene(root);
            stage.setScene(addPartFormScene);
            stage.show();
        }
    }

    public void mainDeleteSelectedPart(ActionEvent e) {
        inventory.deletePart(PartsTable.getSelectionModel().getSelectedItem());
        AlertHandling(0);
    }

    /***
     * //button to redirect scene to 'add product form' scene.
     */
    public void mainOpenAddProductForm(ActionEvent openAddProductForm) throws IOException  {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/addProduct.fxml")));
        stage = (Stage)((Node)openAddProductForm.getSource()).getScene().getWindow();
        Scene addProductFormScene = new Scene(root);
        stage.setScene(addProductFormScene);
        stage.show();
    }

    /***
     * button to redirect scene to 'modify product form' scene.
     */
    public void mainOpenModifyProductForm(ActionEvent openModifyProductForm) throws IOException  {
        Product selectedProduct = ProductsTable.getSelectionModel().getSelectedItem();
      if(selectedProduct == null){
            AlertHandling(1);
      } else {
           FXMLLoader loader1= new FXMLLoader(getClass().getResource("../view/modifyProductForm.fxml"));
           root1 = loader1.load();

           ModifyProductFormController modifyproductCont =loader1.getController();
           modifyproductCont.getModifyProduct(selectedProduct);

           stage = (Stage) ((Node) openModifyProductForm.getSource()).getScene().getWindow();
            Scene addProductFormScene = new Scene(root1);
            stage.setScene(addProductFormScene);
           stage.show();

        }
    }

    public void mainDeleteSelectedProduct(ActionEvent e) {
        inventory.deleteProduct(ProductsTable.getSelectionModel().getSelectedItem());
        AlertHandling(1);
    }

    /***
     * //button to exit the application.
     */
    public void mainExitApplication(ActionEvent exitApp) {
        stage = (Stage) MainScenePane.getScene().getWindow();
        stage.close();
    }


    public void initialize(){
        PartsTable.setItems(inventory.getAllParts());
        partsPartidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInvLevlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartscostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductsTable.setItems(inventory.getAllProducts());
        ProdIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProdInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProdCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void AlertHandling(int num){
        switch(num){
            case 0:
                Alert partNotFound = new Alert(Alert.AlertType.ERROR);
                partNotFound.setTitle("Part Not Found");
                partNotFound.setHeaderText("Please select Part");
                partNotFound.show();
                break;
            case 1:
                Alert productNotFound = new Alert(Alert.AlertType.ERROR);
                productNotFound.setTitle("Product Not Found");
                productNotFound.setHeaderText("Please select Product");
                productNotFound.show();
                break;
        }

    }

}
