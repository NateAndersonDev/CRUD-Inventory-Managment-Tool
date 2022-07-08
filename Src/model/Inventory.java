package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> parts = FXCollections.observableArrayList();
    private ObservableList<Product> products = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        this.parts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        this.products.add(newProduct);
    }

    public Part lookupPart(int partID) {
        for (Part part : parts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    public Product lookupProduct(int productID) {
        for (Product product : products){
            if(product.getId() == productID){
                return product;
            }
        }
        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> existingPart = FXCollections.observableArrayList();
        for (Part part : parts) {
            if (part.getName().equals(partName)) {
                return existingPart;
            }
        }
        return null;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> existingProduct = FXCollections.observableArrayList();
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return existingProduct;
            }
        }
        return null;
    }

    public void updatePart(int index, Part selectedPart) {
        parts.set(index,selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        products.set(index,newProduct);

    }

    public boolean deletePart(Part selectPart) {
        if(parts.contains(selectPart)){
            parts.remove(selectPart);
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteProduct(Product selectProduct) {
        if(products.contains(selectProduct)){
            products.remove((selectProduct));
            return true;
        } else {
            return false;
        }
    }

    public ObservableList<Part> getAllParts() {
        return this.parts;
    }

    public ObservableList<Product> getAllProducts() {
        return this.products;

    }

}
