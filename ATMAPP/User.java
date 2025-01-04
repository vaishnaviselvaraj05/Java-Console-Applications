package ATMAPP;// Importing necessary class for handling lists

import java.util.ArrayList;

public class User extends Accounts {
    private static ArrayList<Transactions> userTransactionHistory = new ArrayList<>();// Static list to maintain the transaction history of all users
    // Private fields to store user details
    private double balance;// ATMAPP.User's account balance
    // Constructor to initialize a user with ID and password
    public User(String id, String pass) {
        super(id,pass);
        this.balance=0.0;
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