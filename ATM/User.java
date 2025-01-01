package ATM;
// Importing necessary class for handling lists
import java.util.ArrayList;

public class User {
    private static ArrayList<Transactions> userTransactionHistory = new ArrayList<>();// Static list to maintain the transaction history of all users
    // Private fields to store user details
    private String userId;// Unique identifier for the user
    private String userPass;// User's password
    private double balance;// User's account balance
    // Constructor to initialize a user with ID and password
    public User(String userId, String userPass) {
        this.userId = userId;// Set the user ID
        this.userPass = userPass;// Set the user password
        this.balance = 0.0;// Initialize balance to zero
    }

    public String getUserId() {// Getter for user ID
        return userId;// Return the user's ID
    }

    public String getUserPass() {// Getter for user password
        return userPass;// Return the user's password
    }

    public void setUserPass(String userPass) {// Setter for user password
        this.userPass = userPass;// Update the user's password
    }

    public double getBalance() {// Getter for user balance
        return balance;// Return the user's account balance
    }

    public void setBalance(double balance) {// Setter for user balance
        this.balance = balance;// Update the user's account balance
    }

    public static ArrayList<Transactions> getUserTransactionHistory() {// Static getter for user transaction history
        return userTransactionHistory;// Return the list of transactions
    }
}
