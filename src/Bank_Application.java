import java.util.ArrayList;
import java.util.Scanner;

public class Bank_Application {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private static BankAccount loggedUser = null;

    public static void main(String[] args) {

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
