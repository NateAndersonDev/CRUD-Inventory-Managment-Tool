package model;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    //setter for companyName
    public void setCompanyName(String companyName){
        this.companyName =companyName;
    }
    //getter for companyName
    public String getCompanyName(){
        return this.companyName;
    }
}
