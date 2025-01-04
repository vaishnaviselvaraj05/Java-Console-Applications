package ATMAPP;

public class Main {//ATMAPP.Main Class Serve as the entry point for the ATMAPP.ATM Application
    public static void main(String[] args) throws CloneNotSupportedException {//ATMAPP.Main method is the entry point that throws CloneNotSupportedException indicating that this method might throw a CloneNotSupportedException if any object cloning operations is attempted in the method
        ATM atm = new ATM();//Creates an instance of the ATMAPP.ATM class
        atm.start();//Starts the ATMAPP.ATM Application by calling the start() method
    }
}