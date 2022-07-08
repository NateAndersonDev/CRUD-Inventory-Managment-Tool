package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import org.w3c.dom.ls.LSOutput;

import java.util.Objects;

public class Main extends Application {
    public static Inventory inventory = new Inventory();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainForm.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        /***
         * Instantiating 5 parts and three Products so there's some data in the program.
         */
        Outsourced Windings1 = new Outsourced(1, "Windings1", 35.90,15,2,80, "Bad Windings Inc.");
        Outsourced Windings2 = new Outsourced(2, "Windings2", 20,12,2,100, "Horrible Windings LLC");
        Outsourced Windings3 = new Outsourced(3, "Windings3", 17.84,35,2,100,"Just the worst Inc.");
        InHouse Stator = new InHouse(4, "Stator",150.09, 28,1,80, 12);
        InHouse Rotor = new InHouse(5, "Rotor",355.02, 11,1,50,05);
        Product FastMotor = new Product(1,"Fast motor", 603.34,6,4,78);
        Product PowerfulMotor = new Product(2,"Powerful motor", 850.64,7,3,50);
        Product BudgetMotor = new Product(3,"Budget motor", 405.34,26,10,100);

        inventory.addPart(Windings1);
        inventory.addPart(Windings2);
        inventory.addPart(Windings3);
        inventory.addPart(Stator);
        inventory.addPart(Rotor);
        inventory.addProduct(FastMotor);
        inventory.addProduct(PowerfulMotor);
        inventory.addProduct(BudgetMotor);

        FastMotor.addAssociatedPart(Windings1);
        FastMotor.addAssociatedPart(Stator);
        FastMotor.addAssociatedPart(Rotor);
        PowerfulMotor.addAssociatedPart(Windings2);
        PowerfulMotor.addAssociatedPart(Stator);
        PowerfulMotor.addAssociatedPart(Rotor);
        BudgetMotor.addAssociatedPart(Windings3);
        BudgetMotor.addAssociatedPart(Stator);
        BudgetMotor.addAssociatedPart(Rotor);


        launch(args);

    }

}