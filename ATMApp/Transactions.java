package ATMApp;

import java.util.Date;

public class Transactions {
    public String id;       // A variable id is declared
    public  String action;    // The type of transaction (e.g., "DEPOSIT", "WITHDRAW")
    public  long amount;    // The amount involved in the transaction
    public Date date;    // The date and time when the transaction occurred

    public Transactions(String id,String action,long amount){   // Constructor is created to initialize
        this.id=id;   // Setting the id
        this.date=new Date();  // Automatically setting the current date and time
        this.action=action;     // Setting the transaction type
        this.amount=amount;    // Setting the transaction amount
    }

    public String getId(){  // Gets id

        return id;  // Returns id

    }

    public Date getDate(){  //Gets date and time
        return date;   //Returns date and time
    }

    public String getAction(){    // Gets action

        return action;  // Returns action

    }

    public long getAmount(){   // Gets amount

        return amount;  // Returns amount

    }

}