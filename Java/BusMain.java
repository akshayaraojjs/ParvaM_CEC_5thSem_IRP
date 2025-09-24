public class BusMain {
    public static void main(String[] args){
        Vehicle v1 = new Vehicle();
        v1.setVehicleDetails("Private", "Petrol", 4);
        v1.start();
        // v1.showDetails(); // Cannot access Child methods

        Bus bus1 = new Bus("VRL Travels", "Karnataka");
        bus1.setVehicleDetails("Private", "Petrol", 6); // Can access the Parent methods
        bus1.showDetails();
        bus1.start(); // Can access the Parent methods (Bus is accessing the properties of Vehicle class)

        Bus bus2 = new Bus("BMTC", "Karnataka");
        bus2.setVehicleDetails("Government", "EV", 6);
        bus2.showDetails();
        bus2.start();
    }
}