public class Vehicle {
    String type, fuel;
    int no_of_wheels;

    // Default Constructor 
    public Vehicle(){
        type = "-";
        fuel = "-";
        no_of_wheels = 0;
    }

    // setter
    public void setVehicleDetails(String type, String fuel, int no_of_wheels){
        // same variable name, we are using "this" keyword
        this.type = type;
        this.fuel = fuel;
        this.no_of_wheels = no_of_wheels;
    }

    public void start(){
        System.out.println(" " + no_of_wheels + " Wheeler Vehicle Started");
    }
}