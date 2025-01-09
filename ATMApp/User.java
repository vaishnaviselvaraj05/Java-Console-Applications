package ATMApp;

public class User extends Accounts {
    private double balance;    // Declared a variable to store balance

    public User(String id,String pass,Double balance){      // Created a constructor to initialize userId,userPass and balance

        super(id,pass);  // Call the constructor of the superclass (ATMApp.Accounts) to set id and password
        this.balance =balance;    // Assigns balance

    }

    public double getBalance(){   // Gets balance

        return balance;   // Returns balance

    }
    public void setBalance(double balance){  // Sets balance

        this.balance=balance;   // Assigns balance

    }

}