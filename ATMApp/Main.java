package ATMApp;

import java.util.Scanner;
public class Main {//ATMApp.Main Class Serve as the entry point for the ATMApp.ATM Application
    public static void main(String[] args) throws CloneNotSupportedException {//ATMApp.Main method is the entry point that throws CloneNotSupportedException indicating that this method might throw a CloneNotSupportedException if any object cloning operations is attempted in the method
        Scanner scan = new Scanner(System.in);// Create a scanner object to take user input
        ATMActions.start(scan);//Starts the ATMApp.ATM Application by calling the start() method
    }
}
