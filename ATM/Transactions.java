package ATM;
// Importing the Date class for recording transaction timestamps
import java.util.Date;
public class Transactions {
    private String action;// The type of transaction (e.g., "DEPOSIT", "WITHDRAW")
    private Date date;// The date and time when the transaction occurred
    private double amount;// The amount involved in the transaction
    private String performer;// The ID of the user who performed the transaction
     // Constructor to initialize a transaction with specific details.
     // @param action The type of the transaction (e.g., "DEPOSIT", "WITHDRAW")
     // @param amount The amount involved in the transaction
     // @param performer The ID of the user who performed the transaction
    public Transactions(String action, double amount, String performer) {
        this.action = action;// Setting the transaction type
        this.date = new Date();// Automatically setting the current date and time
        this.amount = amount;// Setting the transaction amount
        this.performer = performer;// Setting the user who performed the transaction
    }
    public String getAction() {// Getter method to retrieve the action
        return action;//return The action type as a string.
    }
    public Date getDate() {// Getter method to retrieve the date
        return date;//return The transaction's date and time.
    }
    public double getAmount() {// Getter method to retrieve the transaction amount
        return amount;//return The amount involved in the transaction.
    }
    public String getPerformer() {// Getter method to retrieve the performer of the transaction
        return performer;//return The ID of the user who performed the transaction.
    }
    @Override// Overriding the toString() method to provide a readable format for transaction details
    public String toString() {
        return "Action: " + action + " Date: " + date + " Amount: " + amount + " Performer: " + performer;//return A string representation of the transaction details
    }
}
