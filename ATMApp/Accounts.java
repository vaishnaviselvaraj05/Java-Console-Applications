package ATMApp;

import java.util.ArrayList;

public class Accounts{
    public String id;        //To store id
    public String pass;        //To store password

    public ArrayList<Transactions> Transactions=new ArrayList<>();    //To store admin and user transactions

    public ArrayList<Transactions> getTransactions() {       //To get transactions in transaction arraylist

        return Transactions;

    }

    public Accounts(String id, String pass){      // Constructor to initialize values

        this.id=id;     //Sets id
        this.pass=pass;     //Sets password

    }
    public String getId(){    //To get name

        return id;

    }
    public String getPass(){     //To get password

        return pass;

    }

    public void setPass(String pass) {     // To set new password

        this.pass = pass;

    }
}