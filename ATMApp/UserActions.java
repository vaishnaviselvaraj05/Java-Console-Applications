package ATMApp;

import Notes.Notes;
import java.util.ArrayList;
import java.util.Scanner;

// All the methods are static because every user's operation is same

public class UserActions {  // Handles user login
    public static Accounts userLogin(Scanner scan) throws CloneNotSupportedException {   // Method userLogin is created and defined

        System.out.print("Enter ATMApp.User ID: ");
        String username = scan.nextLine();   // Get user ID input
        for (Accounts user : ATM.getAccounts()) {   // Loop through the user list to find a match
            if (user instanceof User) {
                if (user.getId().equals(username)) {
                    System.out.print("Enter ATMApp.User Password: ");
                    String userpass = scan.nextLine();   // Get password input
                    if (user.getPass().equals(userpass)) {
                        System.out.println("Login Successful...");
                        return user;   // Return the logged-in user
                    }
                }
            }
        }
        System.out.println("Login Failed...");
        return null;   // Return null if login fails
    }


    public static void depositAmount(Scanner scanner, User currentuser) { // Allows a user to deposit an amount into their account
        // Get the count of each denomination
        System.out.println("Enter amount to deposit:");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter notes to deposit into ATMApp.ATM:");
        System.out.print("Enter 2000 notes: ");
        int note2000 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter 500 notes: ");
        int note500 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter 200 notes: ");
        int note200 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter 100 notes: ");
        int note100 = Integer.parseInt(scanner.nextLine());
        double total = note2000 * 2000 + note500 * 500 + note200 * 200 + note100 * 100;// Calculate the total value of notes deposited
        if (total != amount) {
            System.out.println("Invalid amount entered. Transaction failed.");// Validation check
        } else {
            System.out.println("Amount Deposited Successfully!!");
            currentuser.setBalance(currentuser.getBalance() + amount);// Update user balance
            System.out.println("Updated Balance: " + currentuser.getBalance());
            // Update ATMApp.ATM's note count
            for (Notes notes : ATM.getNotesList()) {
                String money = notes.getNoteName();
                switch (money) {
                    case "2000":
                        notes.setCount(notes.getCount() + note2000);
                        break;
                    case "500":
                        notes.setCount(notes.getCount() + note500);
                        break;
                    case "200":
                        notes.setCount(notes.getCount() + note200);
                        break;
                    case "100":
                        notes.setCount(notes.getCount() + note100);
                        break;
                    default:
                        System.out.println("Invalid Money");
                        break;
                }
            }
            currentuser.setBalance(currentuser.getBalance() + amount);     // Sets currentuser balance
            ATM.setAtmBalance(ATM.getAtmBalance() + amount);    // Sets atmbalance
            System.out.println("Deposited Successfully...");
            for (Notes note : ATM.getNotesList()) {   // Display updated ATMApp.ATM note counts
                System.out.println("Number of " + note.getNoteName() + " notes are " + note.getCount());
            }
            currentuser.getTransactions().add(new Transactions(currentuser.getId(), " Deposited", (long) amount));   // Adds obj to usertransaction
            System.out.println(currentuser.getId() + " : Deposited :- " + amount);
            System.out.println("ATMApp.ATM Balance: " + ATM.getAtmBalance());
            return;
        }
        System.out.println("Invalid Number Of Notes...");
    }

    // Handles withdrawal logic for a specific denomination
    public static long performWithdrawal(Notes notes, long amount, ArrayList<String> duplicate) {
        long count = (long) amount / Long.parseLong(notes.getNoteName());// Calculate notes needed
        if (Long.parseLong(notes.getNoteName()) <= amount && count <= notes.getCount()) {
            amount = amount - (count * Integer.parseInt(notes.getNoteName()));// Deduct amount
            notes.setCount(notes.getCount() - count);// Update note count in ATMApp.ATM
            return amount;
        }
        return amount;// Return remaining amount if withdrawal is not possible
    }

    // Handles withdrawal process for a user
    public static long withdrawAmount(long userWithdraw, Notes note, ArrayList<String> denominations){   // Method withdrawOperation is created and defined
        long count= userWithdraw/Integer.parseInt(note.getNoteName());   // Calculate notes needed
        if(Long.parseLong(note.getNoteName()) <= userWithdraw && count <= note.getCount()){
            userWithdraw = userWithdraw - (count * Integer.parseInt(note.getNoteName()));  // Deduct amount
            note.setCount((note.getCount() - count));      // Update note count in ATMApp.ATM
            denominations.add("You withdrawed : "+note.getNoteName()+" ,Count: "+count);
            return userWithdraw;
        }
        return userWithdraw;  // Return remaining amount if withdrawal is not possible
    }

    public static void userWithdraw(Scanner scan, User currentuser) throws CloneNotSupportedException {    // Method userwithdraw is created and defined
        ArrayList<Notes> duplicate=new ArrayList<>();    // Temporary list for simulation
        ArrayList<String> denominations=new ArrayList<>();

        System.out.println("Withdraw Amount: ");
        long userWithdraw=Long.parseLong(scan.nextLine());   // Get withdrawal amount
        if(userWithdraw<= currentuser.getBalance()){   // Check if user has sufficient balance
            long amount =userWithdraw;      // Assigns userWithdraw to dupwithdraw
            for(Notes note: ATM.getNotesList()){

                duplicate.add(note.clone());    // Clone the notes list for safe manipulation

            }
            while(userWithdraw!=0) {
                for (Notes dupnote : duplicate) {
                    String dupNotename = dupnote.getNoteName();    // Assigns notename
                    switch (dupNotename) {
                        case "2000", "500", "200", "100":
                            userWithdraw = UserActions.withdrawAmount(userWithdraw, dupnote, denominations);   // Process withdrawal
                            break;
                    }
                }
                // If withdrawal is successful
                if (userWithdraw == 0) {
                    ATM.setNotesList(duplicate);    // Update ATMApp.ATM's notes list
                    currentuser.setBalance(currentuser.getBalance() - amount);  // Update user's balance
                    ATM.setAtmBalance(ATM.getAtmBalance()- amount);   // Sets the balance of atm
                    System.out.println("Withdraw Successful...");
                    System.out.println("ATMApp.ATM Balance : " + ATM.getAtmBalance());
                    for (String str : denominations) {
                        System.out.println(str);
                    }
                    for (Notes note : ATM.getNotesList()) {
                        System.out.println("Number of " + note.getNoteName() + " are " + note.getCount());
                    }
                    currentuser.getTransactions().add(new Transactions(currentuser.getId(), " Withdrawed", (long) amount));   // Adds obj to usertransaction
                    System.out.println(currentuser.getId() + " : Withdrawed :- " + amount);
                    System.out.println("ATMApp.ATM Balance: " + ATM.getAtmBalance());
                    return;
                }
                System.out.println("Denominations not match...");
                return;
            }

        }
        System.out.println("Insufficient Amount...");
    }
    public static void changePassword(Scanner scan, User currentuser){     // Allows a user to change their password
        System.out.println("Current Password: ");
        String oldPass=scan.nextLine();   // Get current password
        if(oldPass.equals(currentuser.getPass())){   // Validate current password
            System.out.println("New Password: ");
            String newPass = scan.nextLine();// Get new password
            currentuser.setPass(newPass);    // Update password
            System.out.println("Password Changed Successfully...");
            return;
        }
        System.out.println("Wrong Password...Retry");
    }

    public static void viewTransactionHistory(Scanner scan, User currentuser){      // Displays a user's transaction history
        for(Transactions user:currentuser.getTransactions()){
            System.out.println("You " + user.getId()+" has "+user.getAction()+" Rs."+user.getAmount()+" in Date "+user.getDate());    // Display each transaction
        }
    }
}