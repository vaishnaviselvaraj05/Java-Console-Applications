package ATMAPP;// Importing necessary classes for handling lists and user input
import java.util.ArrayList;
import java.util.Scanner;

import Notes.Notes;
public class UserActions {
    // Handles user login
    public static Accounts userLogin(Scanner scanner) {
        System.out.print("Enter ATMAPP.User ID: ");
        String userId = scanner.nextLine();// Get user ID input
        System.out.print("Enter ATMAPP.User Password: ");
        String userPass = scanner.nextLine();// Get password input
        // Loop through the user list to find a match
        for (Accounts user : ATM.getaccountsList()) {
            if (user.getId().equals(userId) && user.getPass().equals(userPass)) {
                System.out.println("Login Successful! Welcome, " + userId);
                ATM.setCurrentUser((User) user);// Set the current logged-in user in the ATMAPP.ATM system
                return user;// Return the logged-in user
            }
        }
        System.out.println("Invalid ATMAPP.User ID or Password. Please try again.");
        return null;// Return null if login fails
    }
    // Displays the balance for a user
    public static void checkBalance(User user) {
        System.out.println("Your current balance is: " + user.getBalance());
    }
    // Allows a user to deposit an amount into their account
    public static User depositAmount(Scanner scanner, User user) {// Get deposit amount
        // Get the count of each denomination
        System.out.println("Enter amount to deposit:");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter notes to deposit into ATMAPP.ATM:");
        System.out.print("Enter 2000 notes: ");
        int note2000 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter 500 notes: ");
        int note500 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter 200 notes: ");
        int note200 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter 100 notes: ");
        int note100 = Integer.parseInt(scanner.nextLine());
        double total=note2000*2000+note500*500+note200*200+note100*100;// Calculate the total value of notes deposited
        if(total != amount){
            System.out.println("Invalid amount entered. Transaction failed.");// Validation check
        }else{
            System.out.println("Amount Deposited Successfully!!");
            user.setBalance(user.getBalance()+amount);// Update user balance
            System.out.println("Updated Balance: "+user.getBalance());
            // Update ATMAPP.ATM's note count
            for(Notes notes : ATM.getNotesList()){
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
            // Display updated ATMAPP.ATM note counts
            for (Notes notes : ATM.getNotesList()) {
                System.out.println("Notes Count: " + notes.getNoteName() + ": " + notes.getCount() + " ");
            }
            addTransaction("DEPOSIT",amount);// Record transaction
            ATM.setAtmBalance(ATM.getAtmBalance()+amount);// Update ATMAPP.ATM balance
            System.out.println("ATMAPP.ATM Balance: " + ATM.getAtmBalance());
        }
        return user;
    }
    // Handles withdrawal logic for a specific denomination
    public static long performWithdrawal(Notes notes, long amount, ArrayList<String>duplicate){
        long count=(long)amount/Long.parseLong(notes.getNoteName());// Calculate notes needed
        if(Long.parseLong(notes.getNoteName())<=amount && count<=notes.getCount()){
            amount=amount-(count*Integer.parseInt(notes.getNoteName()));// Deduct amount
            notes.setCount(notes.getCount()-count);// Update note count in ATMAPP.ATM
            return amount;
        }
        return amount;// Return remaining amount if withdrawal is not possible
    }
    // Handles withdrawal process for a user
    public static User withdrawAmount(Scanner scanner, User user) throws CloneNotSupportedException {
        ArrayList<Notes>duplicate=new ArrayList<>();// Temporary list for simulation
        ArrayList<String>denomination=new ArrayList<>();
        System.out.println("Enter amount to withdraw: ");
        long amount=Long.parseLong(scanner.nextLine());// Get withdrawal amount

        if (amount<=user.getBalance()) {// Check if user has sufficient balance
            double money = amount;
            // Clone the notes list for safe manipulation
            for (Notes notes : ATM.getNotesList()) {
                duplicate.add(notes.clone());
            }
            while (amount != 0) {
                for (Notes notes : duplicate) {
                    String type = notes.getNoteName();
                    switch (type) {
                        case "2000", "500", "200", "100":
                            amount = UserActions.performWithdrawal(notes, amount, denomination);// Process withdrawalo
                            break;
                        default:
                            System.out.println("Invalid Money");
                    }
                }
                // If withdrawal is successful
                if (amount == 0) {
                    ATM.setNotesList(duplicate);// Update ATMAPP.ATM's notes list
                    ATM.getCurrentUser().setBalance(user.getBalance() - money);// Update user's balance
                    System.out.println("Withdrawal Successful!");
                    System.out.println("Updated Balance: " + user.getBalance());
                    addTransaction("WITHDRAW", money);// Record transaction
                    for (Notes notes : ATM.getNotesList()) {
                        System.out.println("ATMAPP.ATM.Notes Count: " + notes.getNoteName() + ": " + notes.getCount() + " ");
                    }
                    ATM.setAtmBalance(ATM.getAtmBalance() - money);// Update ATMAPP.ATM balance
                    System.out.println("ATMAPP.ATM Balance: " + ATM.getAtmBalance());
                    return user;
                }
            }
        }
        System.out.println("Insufficient Funds. Transaction Failed.");
        return user;
    }

    public static User viewTransactionHistory(User user) {// Displays a user's transaction history
        System.out.println("Transaction History for " + user.getId() + ":");
        for (Transactions transaction : user.getUserTransactionHistory()) {
            System.out.println(transaction);// Display each transaction
        }
        return user;
    }
    // Adds a transaction to the user's history
    public static User addTransaction(String action, double amount) {
        Transactions transaction = new Transactions(action, amount, ATM.getCurrentUser().getId());
        User.getUserTransactionHistory().add(transaction);// Add to user's history
        return ATM.getCurrentUser();
    }
    // Allows a user to change their password
    public static User changePassword(Scanner scanner, User user) {
        System.out.print("Enter current password: ");
        String currentPass = scanner.nextLine();// Get current password
        if (!currentPass.equals(user.getPass())) {// Validate current password
            System.out.println("Incorrect current password. Password change denied.");
            return null;
        }
        System.out.print("Enter new password: ");
        String newPass = scanner.nextLine();// Get new password
        user.setPass(newPass);// Update password
        System.out.println("Password changed successfully!");
        return user;
    }
}