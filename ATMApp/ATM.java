package ATMApp;

import ListOfNotes.*;
import Notes.Notes;
import java.util.ArrayList;
import java.util.Arrays;
public class ATM {
    public static double atmBalance;     // Static variable to store the ATMApp.ATM's overall balance
    public static ArrayList<Notes> notesList = new ArrayList<>(Arrays.asList(new Note2000("2000", 0), new Note500("500", 0), new Note200("200", 0), new Note100("100", 0)));   //Static list to store availab;e notes in the ATMApp.ATM(2000,500,200,100)
    public static ArrayList<Accounts> accounts = new ArrayList<>();     //Static list to store all admins and users in the ATMApp.ATM system

    public static ArrayList<Accounts> getAccounts() {  // Getter method to return the list of admins and users

        return accounts;

    }

    public static ArrayList<Notes> getNotesList() {   // Getter method to return the list of available notes in the ATMApp.ATM

        return notesList;   // Returns notelist

    }

    public static void setNotesList(ArrayList<Notes> note) {   // Setter method to set the list of notes in the ATMApp.ATM

        ATM.notesList = note;   // Assigns notelist

    }

    public static double getAtmBalance() {  // Getter method to return the ATMApp.ATM's current balance

        return atmBalance;   // returns atmbalance

    }

    public static void setAtmBalance(double balance) {   // Setter method to set the ATMApp.ATM's balance

        atmBalance = balance;  // Assigns atmbalance
    }
}