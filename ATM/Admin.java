package ATM;

import java.util.ArrayList;
public class Admin {
    private static final String admin_id="1001";// A static final variable to store the admin ID (unchangeable)
    private static final String admin_pass="1234";// A static final variable to store the admin password (unchangeable)
    private static ArrayList<Transactions> adminTransactionHistory = new ArrayList<>();// A static list to store the transaction history for the admin
    public static String getAdmin_id() {// Getter method to retrieve the admin ID
        return admin_id;
    }
    public static String getAdmin_pass() {// Getter method to retrieve the admin password
        return admin_pass;
    }
    public static ArrayList<Transactions> getAdminTransactionHistory() {// Getter method to retrieve the admin's transaction history
        return adminTransactionHistory;
    }
}
