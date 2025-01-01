package ATM;

import java.util.ArrayList;
import java.util.Scanner;
public class AdminActions {
    public static Admin adminLogin(Scanner scanner, Admin admin) {// Method to handle admin login
        while (true) {
            System.out.print("Enter Admin User ID: ");
            String adminId = scanner.nextLine();// Read admin ID from input
            if(adminId.equals(Admin.getAdmin_id())){// Check if ID matches
                int i=0;
                while (i<=2){// Allow up to 3 attempts for password
                    System.out.print("Enter Admin Password: ");
                    String adminPass = scanner.nextLine();// Read admin password
                    if(adminPass.equals(Admin.getAdmin_pass())) {// Check password
                        System.out.println("Admin login successful.");
                        return admin;// Return admin object if login successful
                    }else {
                        System.out.println("Invalid Admin Password. Please try again.");
                        i++;// Increment attempt counter
                    }
                }
                System.out.println("Too many incorrect attempts.");// Lock out after 3 attempts
            }
            else{
                System.out.println("Invalid Admin ID. Please try again.");
                return null;// Return null if ID is incorrect
            }
        }
    }
    public static void addUser(Scanner scanner) {// Method to add a new user
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();// Read user ID
        for (User existingUser : ATM.getUserList()){// Check if user already exists
            if(existingUser.getUserId().equals(userId)){
                System.out.println("User ID already exists!!");
                return;
            }
        }
        System.out.print("Enter User Password: ");
        String userPass = scanner.nextLine();// Read user password
        User newUser = new User(userId, userPass);// Create a new user
        ATM.getUserList().add(newUser);// Add user to ATM's user list
        addTransaction("ADD USER",0.0);// Log the transaction
        System.out.println("User added successfully!");
    }
    public static void deleteUser(Scanner scanner) {// Method to delete a user
        System.out.print("Enter User ID to delete: ");
        String userId = scanner.nextLine();// Read user ID
        for (User user : ATM.getUserList()) {// Search for the user in the list
            if (user.getUserId().equals(userId)) {
                ATM.getUserList().remove(user);// Remove user from the list
                addTransaction("DELETE USER",0.0);// Log the transaction
                System.out.println("User deleted successfully!");
                return;
            }
        }
        System.out.println("User not found!");// Display error if user not found
    }

    public static ArrayList<User> viewAllUsers() {// Method to view all users
        System.out.println("All Users:");
        for (User user : ATM.getUserList()) {// Iterate through the user list
            System.out.println("User ID: " + user.getUserId());// Display each user's ID
        }
        return ATM.getUserList();// Return the user list
    }
    public static ArrayList<Transactions> viewTransactionHistory() {// Method to view the transaction history
        System.out.println("ALL TRANSACTION HISTORY");
        System.out.println("-------------------------");
        ArrayList<Transactions> allTransactions = new ArrayList<>();// Combine admin and user transactions
        System.out.println("Admin ATM.Transactions:");
        for(Transactions transaction : Admin.getAdminTransactionHistory()){
            System.out.println(transaction);// Display admin transactions
            allTransactions.add(transaction);
        }
        System.out.println("\nUser Transactions: ");
        for(User user : ATM.getUserList()) {// Iterate through users
            System.out.println("User ID: " + user.getUserId());
            for (Transactions transaction : User.getUserTransactionHistory()) {
                System.out.println(transaction);// Display each user's transactions
                allTransactions.add(transaction);
            }
            System.out.println("-------------------------");
        }
        return allTransactions;// Return all transactions
    }
    public static boolean depositMoney(Scanner scanner) {// Method to handle money deposits by admin
        System.out.println("Enter amount to deposit:");
        double amount = Double.parseDouble(scanner.nextLine());// Read deposit amount
        System.out.println("Enter notes to deposit into ATM.ATM:");
        System.out.print("Enter 2000 notes: ");
        int note2000 = Integer.parseInt(scanner.nextLine());// Read count of 2000 notes
        System.out.print("Enter 500 notes: ");
        int note500 = Integer.parseInt(scanner.nextLine());// Read count of 500 notes
        System.out.print("Enter 200 notes: ");
        int note200 = Integer.parseInt(scanner.nextLine());// Read count of 200 notes
        System.out.print("Enter 100 notes: ");
        int note100 = Integer.parseInt(scanner.nextLine());// Read count of 100 notes
        double total=note2000*2000+note500*500+note200*200+note100*100;// Calculate total
        if(total != amount){// Validate the amount
            System.out.println("Invalid amount entered. Transaction failed.");
        }else{
            System.out.println("Amount Deposited Successfully!!");
            for(Notes notes : ATM.getNotesList()){// Update note counts in ATM
                String money=notes.getNoteName();
                switch (money){
                    case "2000":
                        notes.setCount(notes.getCount()+note2000);
                        break;
                    case "500":
                        notes.setCount(notes.getCount()+note500);
                        break;
                    case "200":
                        notes.setCount(notes.getCount()+note200);
                        break;
                    case "100":
                        notes.setCount(notes.getCount()+note100);
                        break;
                    default:
                        System.out.println("Invalid Money");
                        break;
                }
            }
            for (Notes notes : ATM.getNotesList()) {// Display updated note counts
                System.out.println("Notes Count: " + notes.getNoteName() + ": " + notes.getCount() + " ");
            }
            addTransaction("DEPOSIT",amount);// Log the deposit transaction
            ATM.setAtmBalance(ATM.getAtmBalance()+amount);// Update ATM balance
            System.out.println("ATM Balance: " + ATM.getAtmBalance());
        }
        return true;
    }
    private static Transactions addTransaction(String action, double amount){// Private method to log a transaction
        Transactions transaction=new Transactions(action,amount,"Admin");// Create a new transaction
        Admin.getAdminTransactionHistory().add(transaction);// Add transaction to admin history
        return transaction;
    }
}
