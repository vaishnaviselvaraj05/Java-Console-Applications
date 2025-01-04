package ATMAPP;

public class Accounts {
    public static String id;
    public static String pass;
    public Accounts(String id,String pass){
        this.id=id;
        this.pass=pass;
    }
    public static String getId() {
        return id;
    }
    public static String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass=pass;
    }
}
