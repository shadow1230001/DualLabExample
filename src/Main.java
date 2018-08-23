import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Buses buses = new Buses();
            buses.readFromFile("input.txt");
            buses.sortListOfBusesByTA();
            buses.checkingEfficientBusesByTA();
            buses.sortListOfBusesByTD();
            buses.checkingEfficientBusesByTD();
            buses.checkingTheBest();
            buses.printFile("output.txt");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}
