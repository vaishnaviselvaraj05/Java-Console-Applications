package ATMAPP;

import ListOfNotes.Note100;
import ListOfNotes.Note200;
import ListOfNotes.Note2000;
import ListOfNotes.Note500;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Notes.Notes;
public class ATM {
    private static ArrayList<Accounts> accountsList = new ArrayList<>();//Static list to store all users in the ATMAPP.ATM system
    public static ArrayList<Notes> notesList = new ArrayList<>(Arrays.asList(new Note2000("2000",0),new Note500("500",0),new Note200("200",0),new Note100("100",0)));//Static list to store availab;e notes in the ATMAPP.ATM(2000,500,200,100)
    private static Admin adminLoginSuccessful=null;// Static variable to store the admin login status
    private static User currentUser = null;// Static variable to store the currently logged-in user
    private static double atmBalance = 0.0;// Static variable to store the ATMAPP.ATM's overall balance
    public static ArrayList<Accounts> getaccountsList() {
        return accountsList;// Getter method to return the list of user
    }
    public static ArrayList<Notes> getNotesList() {// Getter method to return the list of available notes in the ATMAPP.ATM
        return notesList;
    }
    public static void setNotesList(ArrayList<Notes> notesList) {// Setter method to set the list of notes in the ATMAPP.ATM
        ATM.notesList = notesList;
    }
    public static double getAtmBalance() {// Getter method to return the ATMAPP.ATM's current balance
        return atmBalance;
    }
    public static void setAtmBalance(double balance) { // Setter method to set the ATMAPP.ATM's balance
        atmBalance = balance;
    }
    public static User getCurrentUser() { // Getter method to return the currently logged-in user
        return currentUser;
    }
    public static void setCurrentUser(User user) {// Setter method to set the currently logged-in user
        currentUser = user;
    }
    public void start() throws CloneNotSupportedException {// Method to start the ATMAPP.ATM system
        Scanner scanner = new Scanner(System.in);
        Accounts admin =new Admin(Accounts.getId(),Accounts.getPass());// Create a scanner object to take user input
        while (true) {// ATMAPP.Main loop for the ATMAPP.ATM system where the user can choose to log in as an admin or user
        // Display the main menu options
        System.out.println("1. ATMAPP.Admin");
        System.out.println("2. ATMAPP.User");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");// Read the user's choice
        String choice = scanner.nextLine();
        switch (choice) {// Switch case to handle the user's choice
            case "1":
                adminLoginSuccessful = (Admin) AdminActions.adminLogin(scanner,admin); // Attempt admin login and if successful, show the admin menu
                if (adminLoginSuccessful!=null) {
                    adminMenu(scanner);// Go to admin menu if login successful
                }
                break;
            case "2":
                currentUser = (User) UserActions.userLogin(scanner);// Attempt user login and if successful, show the user menu
                if (currentUser != null) {
                    userMenu(currentUser, scanner);// Go to user menu if login successful
                }
                break;
            case "3":
                System.out.println("Exiting system...");// Exit the program
                return;
            default:
                System.out.println("Invalid choice. Please try again.");// Handle invalid input
            }
        }
    }
    public void adminMenu(Scanner scanner) {// Method to display the admin menu and handle admin actions
        while (true) {// Loop to keep the admin menu active until the admin chooses to exit
            // Display the admin menu options
            System.out.println("ATMAPP.Admin Menu");
            System.out.println("1. Add ATMAPP.User");
            System.out.println("2. Delete ATMAPP.User");
            System.out.println("3. View All Users");
            System.out.println("4. View Transaction History");
            System.out.println("5. Deposit Money");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");// Read the admin's choice
            String choice = scanner.nextLine();
            switch (choice) {// Switch case to handle the admin's choice
                case "1":
                    AdminActions.addUser(scanner);// ATMAPP.Admin adds a new user
                    break;
                case "2":
                    AdminActions.deleteUser(scanner);// ATMAPP.Admin deletes a user
                    break;
                case "3":
                    AdminActions.viewAllUsers();// ATMAPP.Admin views all users
                    break;
                case "4":
                    AdminActions.viewTransactionHistory();// ATMAPP.Admin views transaction history
                    break;
                case "5":
                    AdminActions.depositMoney(scanner);// ATMAPP.Admin deposits money into the ATMAPP.ATM
                    break;
                case "6":
                    System.out.println("Exiting ATMAPP.Admin menu...");// Exit the admin menu
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");// Handle invalid input
            }
        }
    }
    // Method to display the user menu and handle user actions
    public void userMenu(User user, Scanner scanner) throws CloneNotSupportedException {
        while (true) {// Loop to keep the user menu active until the user chooses to exit
            // Display the user menu options
            System.out.println("ATMAPP.User Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Transaction History");
            System.out.println("5. Change Password");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");// Read the user's choice
            String choice = scanner.nextLine();
            switch (choice) {// Switch case to handle the user's choice
                case "1":
                    UserActions.checkBalance(user); // ATMAPP.User checks their balance
                    break;
                case "2":
                    UserActions.depositAmount(scanner,user);// ATMAPP.User deposits money into their account
                    break;
                case "3":
                    UserActions.withdrawAmount(scanner,user);// ATMAPP.User withdraws money from their account
                    break;
                case "4":
                    UserActions.viewTransactionHistory(user);// ATMAPP.User views their transaction history
                    break;
                case "5":
                    UserActions.changePassword(scanner, user);// ATMAPP.User changes their password
                    break;
                case "6":
                    // ATMAPP.User logs out and exits the menu
                    System.out.println("Logging out...");
                    ATM.setCurrentUser(null);// Clear the current user
                    return;// Exit user menu
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}