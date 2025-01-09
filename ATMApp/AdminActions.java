package ATMApp;

import Notes.Notes;
import java.util.Scanner;
public class AdminActions {
    public static Accounts adminLogin(Scanner scan) throws CloneNotSupportedException {   // Method to handle admin login
        ATM.getAccounts().add(new Admin("1001","1234"));
        while (true){
            System.out.print("Enter ATMApp.Admin ID: ");
            String adminname = scan.nextLine();  // Read admin ID from input
            for (Accounts adminn : ATM.getAccounts()) {
                if (adminn instanceof Admin) {
                    if (adminn.getId().equals(adminname)) {
                        int i=0;
                        while(i<3){
                            System.out.print("Enter ATMApp.Admin Password: ");
                            String adminpass = scan.nextLine();
                            if(adminn.getPass().equals(adminpass)){
                                System.out.println("Login Successful...");
                                return adminn;
                            }
                            else {
                                System.out.println("Invalid Password...Retry");
                                i++;
                            }
                        }
                    }
                }
            }
            System.out.println("Login Failed...");
            return null;
        }
    }

    public static void addUser(Scanner scan) {    // Method addUser is created and defined

        System.out.println("Enter ATMApp.User ID: ");
        String new_userId = scan.nextLine();
        for (Accounts user : ATM.getAccounts()) {
            if (user instanceof User) {
                if (user.getId().equals(new_userId)) {
                    System.out.println("ATMApp.User Already exists...");
                    return;
                }
            }
        }
        System.out.println("Enter ATMApp.User Password: ");
        String new_userPass = scan.nextLine();
        ATM.getAccounts().add(new User(new_userId, new_userPass, 0.0));
        System.out.println("ATMApp.User added Successfully...");
    }

    public static void deleteUser(Scanner scan) {  // Method deleteUser is created and defined

        System.out.println("Enter ATMApp.User ID to Delete: ");
        String deleteuser = scan.nextLine();
        for (Accounts user : ATM.getAccounts()) {
            if (user instanceof User) {
                if (user.getId().equals(deleteuser)) {
                    ATM.getAccounts().remove(user);
                    System.out.println("ATMApp.User Deleted Successfully...");
                    return;
                }
            }
        }
        System.out.println("ATMApp.User ID not Found...");
    }

    public static void depositMoney(Scanner scan, Accounts admin) {   // Method adminDeposit is created and defined
        System.out.println("Enter Deposit Amount: ");
        double amount = Double.parseDouble(scan.nextLine());   // Converts str into double
        System.out.print("Enter 2000 notes: ");
        int note2000 = Integer.parseInt(scan.nextLine());       // Converts str into int
        System.out.print("Enter 500 notes: ");
        int note500 = Integer.parseInt(scan.nextLine());      // Converts str into int
        System.out.print("Enter 200 notes: ");
        int note200 = Integer.parseInt(scan.nextLine());     // Converts str into int
        System.out.print("Enter 100 notes: ");
        int note100 = Integer.parseInt(scan.nextLine());          // Converts str into int
        if (2000 * note2000 + 500 * note500 + 200 * note200 + 100 * note100 == amount) {    // Checks the condition
            for (Notes notes : ATM.getNotesList()) {     // Gets the items in notesList
                String old = notes.getNoteName();      // Notename is assigned to a variable
                switch (old) {
                    case "2000":
                        notes.setCount(notes.getCount() + note2000);   // Sets the count for 2000 notes
                        break;
                    case "500":
                        notes.setCount(notes.getCount() + note500);     // Sets the count for 500 notes
                        break;
                    case "200":
                        notes.setCount(notes.getCount() + note200);        // Sets the count for 200 notes
                        break;
                    case "100":
                        notes.setCount(notes.getCount() +note100);       // Sets the count for 100 notes
                        break;
                    default:
                        System.out.println("Invalid Notes...");
                        break;
                }
            }
            ATM.setAtmBalance(ATM.getAtmBalance() + amount);     // Sets the atmbalance
            System.out.println("Deposited Successfully...");
            admin.getTransactions().add(new Transactions(admin.getId(),"Deposited", (long) amount));     // Adds the object into admintransactionlist
            System.out.println("ATMApp.Admin Deposited " + amount +"rupees");
            System.out.println("ATMApp.ATM Balance: " + ATM.getAtmBalance());
            for (Notes note : ATM.getNotesList()) {              // Gets notes

                System.out.println("Number of " + note.getNoteName() + " notes are " + note.getCount());
            }
            return;
        }
        System.out.println("Invalid Note Count...");
    }

    public static void viewAllUsers(Scanner scan) {       // Method viewAllUsers is created and defined

        for (Accounts user : ATM.getAccounts()) {
            if (user instanceof User){
                if(ATM.getAccounts().isEmpty()){
                    System.out.println("No ATMApp.User Found...");
                    return;
                }
                System.out.println("ATMApp.User :" + user.getId());
            }
        }
    }

    public static void viewAllTransactionHistory(Scanner scan, Accounts admins) {     // Method viewAllTransactionHistory is created and defined
        System.out.println("1) ATMApp.Admin ATMApp.Transactions\n2) ATMApp.User ATMApp.Transactions\n3) view All ATMApp.Transactions\nEnter your choice: ");
        int options=Integer.parseInt(scan.nextLine());
        switch (options){
            case 1:                 // Prints the transactions of admin
                for(Transactions admin: admins.getTransactions()){
                    System.out.println("ATMApp.Admin " + admin.getId() + " has " + admin.getAction() + " Rs." + admin.getAmount() + " in Date " + admin.getDate());
                }
                break;
            case 2:        // Prints the transaction of users
                for (Accounts user: ATM.getAccounts()) {
                    if (user instanceof User) {
                        for (Transactions users : user.getTransactions()) {
                            System.out.println("ATMApp.User " + users.getId() + " has " + users.getAction() + " Rs. " + users.getAmount() + " in Date " + users.getDate());
                        }
                    }
                }
                break;

            case 3:           // Prints both admin and user transaction
                System.out.println("ATMApp.Admin ATMApp.Transactions\n");
                for(Transactions admin: admins.getTransactions()){
                    System.out.println("ATMApp.Admin " + admin.getId() + " has " + admin.getAction() + " Rs." + admin.getAmount() + " in Date " + admin.getDate());
                }

                System.out.println("ATMApp.User ATMApp.Transactions\n");
                for (Accounts user: ATM.getAccounts()) {
                    if (user instanceof User) {
                        for (Transactions users : user.getTransactions()) {
                            System.out.println("ATMApp.User " + users.getId() + " has " + users.getAction() + " Rs. " + users.getAmount() + " in Date " + users.getDate());
                        }
                    }
                }
                break;
        }
    }
}