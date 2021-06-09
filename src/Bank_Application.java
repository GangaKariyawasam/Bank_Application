import java.util.ArrayList;
import java.util.Scanner;

public class Bank_Application {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private static BankAccount loggedUser = null;

    public static void main(String[] args) {

        System.out.println("Welcome to Ganga's Bank");
        System.out.println();
        System.out.println("A : Create new bank account");
        System.out.println("B : Login to your account");

        final char c = scanner.next().charAt(0);
        if(c == 'A'){
            createNewAccount();
        }else {
            login();
        }

    }

    private static void createNewAccount(){

        System.out.println("Please follow the steps below to create your account");
        System.out.println("------------------------------------");
        System.out.println();
        System.out.print("Enter your Name: ");
        final String name = scanner.next();
        System.out.print("Enter your Id: ");
        final String id = scanner.next();
        String pwd = null;
        String confirmPwd = null;
        do {
            System.out.print("Enter your password: ");
            pwd = scanner.next();
            System.out.print("Retype your password: ");
            confirmPwd = scanner.next();

            if(!pwd.equals(confirmPwd)){
                System.out.println("Password mismatched! Failed to create a account");
                System.out.println("-------------------------------------");
                System.out.println();
            }
        }while (!pwd.equals(confirmPwd));

        BankAccount bankAccount = new BankAccount(name,id,pwd);
        bankAccounts.add(bankAccount);

        System.out.println("Successfully Created the bank account");
        System.out.println("-----------------------------------");
        System.out.println();
        login();
    }

    private static void login(){
        System.out.println("Login to Your Bank Account");
        System.out.println("----------------------------------");
        System.out.println();
        boolean isLogged = false;
        do {
            System.out.print("Please enter your id: ");
            String id = scanner.next();
            System.out.print("Please enter your password: ");
            String pwd = scanner.next();
            if(id != null && !id.isEmpty()){
                if(bankAccounts.size()<1){
                    System.out.println("No Account found! Please create a new Account");
                    createNewAccount();
                }
                for (BankAccount bankAccount : bankAccounts) {
                    if(bankAccount.customerId.equals(id)){
                        if(pwd.equals(bankAccount.password)){
                            loggedUser = bankAccount;
                            isLogged = true;
                        }
                    }
                    else {
                        System.out.println("Sorry! No User found");
                        System.out.println();
                        System.out.println("A : Create new bank account");
                        System.out.println("B : Login to Another account");

                        final char c = scanner.next().charAt(0);
                        if(c == 'A'){
                            createNewAccount();
                        }else {
                            login();
                        }
                    }
                }
            }
        }while (!isLogged);

        System.out.println("Welcome"+ " "+loggedUser.customerName);
        System.out.println("Your ID: "+""+loggedUser.customerId);
        System.out.println();

        showmenu();

    }

    private static void showmenu(){

        char option = '\0';

        System.out.println("A : Create new Account");
        System.out.println("B : Log as a another User");
        System.out.println("C : Check your balance");
        System.out.println("D : Deposit");
        System.out.println("E : Withdraw");
        System.out.println("F : previous transaction");
        System.out.println("G : Log out");
        System.out.println("H : Exit the System");

        do {
            System.out.println("...............................");
            System.out.println("Enter Your Option");
            System.out.println("...............................");
            option = scanner.next().charAt(0);
            System.out.println();

            switch (option){

                case 'A':
                    System.out.println("...............................");
                    createNewAccount();
                    break;

                case 'B':
                    System.out.println("...............................");
                    login();
                    break;

                case 'C':
                    System.out.println("...............................");
                    System.out.println("Balance = "+loggedUser.balance);
                    System.out.println("...............................");
                    System.out.println();
                    break;

                case 'D':
                    System.out.println("...............................");
                    System.out.println("Enter an amount to deposit");
                    System.out.println("...............................");

                    int amount = scanner.nextInt();
                    loggedUser.deposit(amount);
                    System.out.println();
                    break;

                case 'E':
                    System.out.println("...............................");
                    System.out.println("Enter an amount to withdraw");
                    System.out.println("...............................");

                    int amount1 = scanner.nextInt();
                    loggedUser.withdrawal(amount1);
                    System.out.println();
                    break;

                case 'F':
                    System.out.println("...............................");
                    loggedUser.getPreviousTransaction();
                    System.out.println("...............................");
                    break;

                case 'G':
                    System.out.println("...............................");
                    loggedUser = null;
                    System.out.println("Logged Out!");
                    login();
                    break;

                case 'H':
                    System.out.println("................................");
                    break;

                default:
                    System.out.println("Invalid option! Please enter correct option");
                    break;
            }
        }while (option != 'H');
        System.out.println("Thank you for using our services");
        System.exit(0);
    }

    static class BankAccount{
        int balance;
        int previousTransaction;
        String customerName;
        String customerId;
        String password;

        BankAccount(String cName, String cId, String pwd){
            this.customerName = cName;
            this.customerId = cId;
            this.password = pwd;
        }

        void deposit(int amount){
            if (amount!= 0){
                balance = balance + amount;
                previousTransaction = balance;
            }
        }

        void withdrawal(int amount){
            if (amount!= 0){
                balance = balance - amount;
                previousTransaction = -amount;
            }
        }

        void getPreviousTransaction(){
            if (previousTransaction>0){
                System.out.println("Deposited: " + previousTransaction);
            }else if (previousTransaction<0){
                System.out.println("Withdraw: " + Math.abs(previousTransaction));
            }
            else {
                System.out.println("No Transsaction Occured");
            }
        }
    }
}
