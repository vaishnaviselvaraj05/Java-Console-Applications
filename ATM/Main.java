package ATM;//Declares that this class is part of the ATM Package
public class Main {//Main Class Serve as the entry point for the ATM Application
    public static void main(String[] args) throws CloneNotSupportedException {//Main method is the entry point that throws CloneNotSupportedException indicating that this method might throw a CloneNotSupportedException if any object cloning operations is attempted in the method
        ATM atm = new ATM();//Creates an instance of the ATM class
        atm.start();//Starts the ATM Application by calling the start() method
    }
}
