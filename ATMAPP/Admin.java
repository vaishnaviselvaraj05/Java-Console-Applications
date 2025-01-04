package ATMAPP;

import java.util.ArrayList;
public class Admin extends Accounts {
    private static ArrayList<Transactions> adminTransactionHistory = new ArrayList<>();// A static list to store the transaction history for the admin
    public Admin(String id,String pass){
        super(id,pass);
    }
    public static ArrayList<Transactions> getAdminTransactionHistory() {// Getter method to retrieve the admin's transaction history
        return adminTransactionHistory;
    }
}