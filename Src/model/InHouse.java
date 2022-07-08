package model;

public class InHouse extends Part {
    private int machineID;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }
    //setter for machineID
    public void setMachineId(int machineId){
        this.machineID = machineId;
    }
    //getter for machineID
    public int getMachineId() {
        return machineID;
    }

}
