package ATMAPP;

import java.util.ArrayList;
import java.util.Scanner;

import Notes.Notes;
public class AdminActions {
    public static Accounts adminLogin(Scanner scanner, Accounts admin) {// Method to handle admin login
        ATM.getaccountsList().add(new Admin("1001","1234"));
        while (true) {
            System.out.print("Enter ATMAPP.Admin ATMAPP.User ID: ");
            String adminId = scanner.nextLine();// Read admin ID from input
            if(adminId.equals(Admin.getId())){// Check if ID matches
                int i=0;
                while (i<=2){// Allow up to 3 attempts for password
                    System.out.print("Enter ATMAPP.Admin Password: ");
                    String adminPass = scanner.nextLine();// Read admin password
                    if(adminPass.equals(Admin.getPass())) {// Check password
                        System.out.println("ATMAPP.Admin login successful.");
                        return admin;// Return admin object if login successful
                    }else {
                        System.out.println("Invalid ATMAPP.Admin Password. Please try again.");
                        i++;// Increment attempt counter
                    }
                }
                System.out.println("Too many incorrect attempts.");// Lock out after 3 attempts
            }
            else{
                System.out.println("Invalid ATMAPP.Admin ID. Please try again.");
                return null;// Return null if ID is incorrect
            }
        }
    }
    public static void addUser(Scanner scanner) {// Method to add a new user
        System.out.print("Enter ATMAPP.User ID: ");
        String userId = scanner.nextLine();// Read user ID
        for (Accounts existingUser : ATM.getaccountsList()){// Check if user already exists
            if(existingUser.getId().equals(userId)){
                System.out.println("ATMAPP.User ID already exists!!");
                return;
            }
        }
        System.out.print("Enter ATMAPP.User Password: ");
        String userPass = scanner.nextLine();// Read user password
        User newUser = new User(Accounts.id, Accounts.pass);// Create a new user
        ATM.getaccountsList().add(newUser);// Add user to ATMAPP.ATM's user list
        addTransaction("ADD USER",0.0);// Log the transaction
        System.out.println("ATMAPP.User added successfully!");
    }
    public static void deleteUser(Scanner scanner) {// Method to delete a user
        System.out.print("Enter ATMAPP.User ID to delete: ");
        String userId = scanner.nextLine();// Read user ID
        for (Accounts user : ATM.getaccountsList()) {// Search for the user in the list
            if (user.getId().equals(userId)) {
                ATM.getaccountsList().remove(user);// Remove user from the list
                addTransaction("DELETE USER",0.0);// Log the transaction
                System.out.println("ATMAPP.User deleted successfully!");
                return;
            }
        }
        System.out.println("ATMAPP.User not found!");// Display error if user not found
    }

    public static ArrayList<Accounts> viewAllUsers() {// Method to view all users
        System.out.println("All Users:");
        for (Accounts user : ATM.getaccountsList()) {// Iterate through the user list
            System.out.println("ATMAPP.User ID: " + user.getId());// Display each user's ID
        }
        return ATM.getaccountsList();// Return the user list
    }
    public static ArrayList<Transactions> viewTransactionHistory() {// Method to view the transaction history
        System.out.println("ALL TRANSACTION HISTORY");
        System.out.println("-------------------------");
        ArrayList<Transactions> allTransactions = new ArrayList<>();// Combine admin and user transactions
        System.out.println("ATMAPP.Admin ATMAPP.ATM.ATMAPP.Transactions:");
        for(Transactions transaction : Admin.getAdminTransactionHistory()){
            System.out.println(transaction);// Display admin transactions
            allTransactions.add(transaction);
        }
        System.out.println("\nATMAPP.User ATMAPP.Transactions: ");
        for(Accounts user : ATM.getaccountsList()) {// Iterate through users
            System.out.println("ATMAPP.User ID: " + user.getId());
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
        System.out.println("Enter notes to deposit into ATMAPP.ATM:");
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
            for(Notes notes : ATM.getNotesList()){// Update note counts in ATMAPP.ATM
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
            ATM.setAtmBalance(ATM.getAtmBalance()+amount);// Update ATMAPP.ATM balance
            System.out.println("ATMAPP.ATM Balance: " + ATM.getAtmBalance());
        }
        return true;
    }
    private static Transactions addTransaction(String action, double amount){// Private method to log a transaction
        Transactions transaction=new Transactions(action,amount,"ATMAPP.Admin");// Create a new transaction
        Admin.getAdminTransactionHistory().add(transaction);// Add transaction to admin history
        return transaction;
    }
}