package ATM;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class ATM {
    private static ArrayList<User> userList = new ArrayList<>();//Static list to store all users in the ATM system
    public static ArrayList<Notes> notesList = new ArrayList<>(Arrays.asList(new Note2000("2000",0),new Note500("500",0),new Note200("200",0),new Note100("100",0)));//Static list to store availab;e notes in the ATM(2000,500,200,100)
    private static Admin adminLoginSuccessful=null;// Static variable to store the admin login status
    private static User currentUser = null;// Static variable to store the currently logged-in user
    private static double atmBalance = 0.0;// Static variable to store the ATM's overall balance
    public static ArrayList<User> getUserList() {// Getter method to return the list of users
        return userList;
    }
    public static ArrayList<Notes> getNotesList() {// Getter method to return the list of available notes in the ATM
        return notesList;
    }
    public static void setNotesList(ArrayList<Notes> notesList) {// Setter method to set the list of notes in the ATM
        ATM.notesList = notesList;
    }
    public static double getAtmBalance() {// Getter method to return the ATM's current balance
        return atmBalance;
    }
    public static void setAtmBalance(double balance) { // Setter method to set the ATM's balance
        atmBalance = balance;
    }
    public static User getCurrentUser() { // Getter method to return the currently logged-in user
        return currentUser;
    }
    public static void setCurrentUser(User user) {// Setter method to set the currently logged-in user
        currentUser = user;
    }
    public void start() throws CloneNotSupportedException {// Method to start the ATM system
        Scanner scanner = new Scanner(System.in);// Create a scanner object to take user input
        Admin admin = new Admin();// Create an Admin object for login attempt
        while (true) {// Main loop for the ATM system where the user can choose to log in as an admin or user
            // Display the main menu options
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");// Read the user's choice
            String choice = scanner.nextLine();
            switch (choice) {// Switch case to handle the user's choice
                case "1":
                    adminLoginSuccessful = AdminActions.adminLogin(scanner,admin); // Attempt admin login and if successful, show the admin menu
                    if (adminLoginSuccessful!=null) {
                        adminMenu(scanner);// Go to admin menu if login successful
                    }
                    break;
                case "2":
                    currentUser = UserActions.userLogin(scanner);// Attempt user login and if successful, show the user menu
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
            System.out.println("Admin Menu");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. View All Users");
            System.out.println("4. View Transaction History");
            System.out.println("5. Deposit Money");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");// Read the admin's choice
            String choice = scanner.nextLine();
            switch (choice) {// Switch case to handle the admin's choice
                case "1":
                    AdminActions.addUser(scanner);// Admin adds a new user
                    break;
                case "2":
                    AdminActions.deleteUser(scanner);// Admin deletes a user
                    break;
                case "3":
                    AdminActions.viewAllUsers();// Admin views all users
                    break;
                case "4":
                    AdminActions.viewTransactionHistory();// Admin views transaction history
                    break;
                case "5":
                    AdminActions.depositMoney(scanner);// Admin deposits money into the ATM
                    break;
                case "6":
                    System.out.println("Exiting Admin menu...");// Exit the admin menu
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
            System.out.println("User Menu");
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
                    UserActions.checkBalance(user); // User checks their balance
                    break;
                case "2":
                    UserActions.depositAmount(scanner,user);// User deposits money into their account
                    break;
                case "3":
                    UserActions.withdrawAmount(scanner,user);// User withdraws money from their account
                    break;
                case "4":
                    UserActions.viewTransactionHistory(user);// User views their transaction history
                    break;
                case "5":
                    UserActions.changePassword(scanner, user);// User changes their password
                    break;
                case "6":
                    // User logs out and exits the menu
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
