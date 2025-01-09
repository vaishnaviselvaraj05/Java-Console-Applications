package ATMApp;

import java.util.Scanner;
public class ATMActions {
    public static void start(Scanner scan) throws CloneNotSupportedException {      // Method to start the ATMApp.ATM system
        while(true){
            System.out.println("1) ATMApp.Admin\n2) ATMApp.User\n3) Exit\nEnter your Choice: ");     // ATMApp.Main loop for the ATMApp.ATM system where the user can choose to log in as an admin or user
            int choice=Integer.parseInt(scan.nextLine());           // Gets the user choice as int
            switch (choice){                 // Switch case to handle the user's choice
                case 1:
                    Admin admin = (Admin) AdminActions.adminLogin(scan);   // Attempt admin login and if successful, show the admin menu
                    if(admin==null)       // If null then it breaks
                    {
                        System.out.println("No admins found");
                        break;
                    }
                    ATMActions.adminOptions(scan,admin);           // Go to admin menu if login successful
                    break;
                case 2:
                    User user = (User) UserActions.userLogin(scan);   // Attempt user login and if successful, show the user menu
                    if(user==null)         // If null it breaks
                    {
                        break;
                    }
                    ATMActions.useroptions(scan,user);     // Go to user menu if login successful;
                case 3:
                    System.out.println("Exiting System...");   // Exit the program
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");     // Handle invalid input
                    break;
            }
        }
    }

    public static void adminOptions(Scanner scan, Accounts admin) throws CloneNotSupportedException {   // Method to display the admin menu and handle admin actions
        while(true){     // Loop to keep the admin menu active until the admin chooses to exit
            System.out.println("ATMApp.Admin Menu"); // Display the admin menu options
            System.out.println("1) Add ATMApp.User\n2) Delete ATMApp.User\n3) ATMApp.Admin Deposit\n4) View all ATMApp.User\n5) View all ATMApp.Transactions\n6) Exit\n Enter option: ");  // Prints
            int adminchoice=Integer.parseInt(scan.nextLine());        // Gets user choice
            switch (adminchoice) {     // Switch case to handle the admin's choice
                case 1:
                    AdminActions.addUser(scan);     // ATMApp.Admin adds a new user
                    break;
                case 2:
                    AdminActions.deleteUser(scan);   // ATMApp.Admin deletes a user
                    break;
                case 3:
                    AdminActions.depositMoney(scan,admin);   // ATMApp.Admin deposits money into the ATMApp.ATM
                    break;
                case 4:
                    AdminActions.viewAllUsers(scan);     // Calls method viewalluser
                    break;
                case 5:
                    AdminActions.viewAllTransactionHistory(scan,admin);    // ATMApp.Admin views transaction history
                    break;
                case 6:
                    System.out.println("Exiting ATMApp.Admin menu...");  // Exit the admin menu
                    ATMActions.start(scan);          // Calls method start
                    break;
                default:
                    System.out.println("Invalid choice. Please try again ");    // Handle invalid input
            }
        }
    }

    public static void useroptions(Scanner scan, User currentuser) throws CloneNotSupportedException {   // Method to display the user menu and handle user actions

        while(true) {    // Loop to keep the user menu active until the user chooses to exit
            System.out.println("ATMApp.User Menu");// Display the user menu options
            System.out.println("1)Check Balance\n2)Deposit\n3)Withdraw\n4)Change Pin\n5)View Transaction\n6)Exit\nEnter your choice: ");  // Prints
            int userchoice = Integer.parseInt(scan.nextLine());             // Gets user choice
            switch (userchoice){   // Switch case to handle the user's choice
                case 1:
                    System.out.println("Currebt Balance is: "+ currentuser.getBalance());   // ATMApp.User checks their balance
                    break;
                case 2:
                    UserActions.depositAmount(scan,currentuser);      // ATMApp.User deposits money into their account
                    break;
                case 3:
                    UserActions.userWithdraw(scan,currentuser);    // ATMApp.User withdraws money from their account
                    break;
                case 4:
                    UserActions.changePassword(scan,currentuser);    // ATMApp.User changes their password
                    break;
                case 5:
                    UserActions.viewTransactionHistory(scan,currentuser);    // ATMApp.User views their transaction history
                    break;
                case 6:
                    System.out.println("Logging out...");   // ATMApp.User logs out and exits the menu
                    return;// Exit user menu
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }
}

