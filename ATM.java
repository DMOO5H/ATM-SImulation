import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    static Bank myBank = new Bank("HSBC");
    Scanner in = new Scanner(System.in);
    int accNo;    

    public static void main(String[] args) {
        myBank.addAccount(new Account("Admin", "Admin", 0, 0000));
        new ATM().run();
    }

    private int mainMenu() {
        do {
            System.out.println();
            System.out.println("Welcome to " + myBank.getBankName());
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Delete");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Please Insert: ");

            int option = in.nextInt();

            if (option >= 1 && option <= 4) {
                return option;
            }

            System.out.println("Error Occured. Please Enter a Valid Input" + "\n");
        } while (true);
    }

    private int accountMenu() {
        do {
            System.out.println();
            System.out.println("Welcome to " + myBank.getBankName());
            System.out.println("1. Account Info");
            System.out.println("2. Make a Deposit");
            System.out.println("3. Create a Withdrawal");
            System.out.println("4. Transfer Between Accounts");
            System.out.println("5. Pay Bills");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Please Insert: ");
            int option = in.nextInt();
            System.out.println();

            if (option >= 1 && option <= 8) {
                return option;
            }

            System.out.println("Error Occured. Please Enter a Valid Input" + "\n");
        } while (true);
    }

    private int adminMenu() {
        do {
            System.out.println();
            System.out.println("Welcome to " + myBank.getBankName());
            System.out.println("1. List Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Please Insert: ");

            int option = in.nextInt();

            if (option >= 1 && option <= 3) {
                return option;
            }

            System.out.println("Error Occured. Please Enter a Valid Input" + "\n");
        } while (true);
    }

    private void run() {
        do {
            int option = mainMenu();
            switch (option) {
                case 1:
                    Account account = login();
                    if (account != null) {
                        if (account.getAccNo() == 0) {
                            runAdmin();
                        } else {
                            runAccount(account);
                        }
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    return;

            }
        } while (true);
    }

    private void runAccount(Account account) {
        do {
            int option = accountMenu();
            switch (option) {
                case 1:
                    System.out.println("Name            : " + account.getFirstName() + " " + account.getSurName());
                    System.out.println("Account Number  : " + account.getAccNo());
                    System.out.println("Balance         : " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Insert Amount to Deposit: ");
                    account.deposit(in.nextInt());
                    System.out.println("Deposit Successful!");
                    break;
                case 3:
                    System.out.print("Insert Amount for Withdrawal: ");
                    account.Withdrawal(in.nextInt());
                    System.out.println("Withdrawal Successful!");
                    break;
                case 4:
                    transfer(account);
                    System.out.println("Transfer Successful!");
                    break;
                case 5:
                    payBills(account);
                    System.out.println("Bills Successfully Paid!");
                    System.out.println("Your Bills: " + account.getBills());
                    break;
                case 6:
                    return;

            }
        } while (true);
    }

    public void runAdmin() {
        do {
            int option = adminMenu();
            switch (option) {
                case 1:
                    listAccount();
                    break;
                case 2:
                    deleteAccount();
                    break;
                case 3:
                    return;
            }

        } while (true);

    }

    private void register() {
        System.out.print("Insert First Name: ");
        String firstName = in.next();
        System.out.print("Insert Surname: ");
        String surName = in.next();
        System.out.print("Insert PIN: ");
        int pin = in.nextInt();

        Account account = new Account(firstName, surName, ++accNo, pin);
        myBank.addAccount(account);
        System.out.println("Account Number: " + account.getAccNo());
    }

    private Account login() {
        do {
            System.out.print("Insert Account Number: ");
            int accNo = in.nextInt();
            System.out.print("Insert PIN: ");
            int pin = in.nextInt();

            Account account = myBank.getAccount(accNo, pin);

            if (account != null) {
                return account;
            } else {
                System.out.println();
                System.out.println("Invalid Account number or PIN");
                System.out.print("Would You Like to Exit ? (Y/N)");
                if (in.next().equals("Y")) {
                    return null;
                }
            }
        } while (true);

    }

    private void delete() {
        do {
            System.out.print("Insert Account Number: ");
            int accNo = in.nextInt();
            System.out.print("Insert PIN: ");
            int pin = in.nextInt();
            Account account = myBank.getAccount(accNo, pin);
            if (account != null) {
                myBank.delAccount(account);
                System.out.println("Account Successfully Expunged");
                return;
            } else {
                System.out.println("Invalid Account number or PIN");
                System.out.print("Would You Like to Exit ? (Y/N)");
                if (in.next().equals("Y")) {
                    return;
                }
            }
        } while (true);
    }

    private void deleteAccount() {
        do {
            System.out.print("Insert Account Number: ");
            int accNo = in.nextInt();
            Account account = myBank.getAccountAdmin(accNo);
            if (account != null) {
                myBank.delAccount(account);
                System.out.println("Account Successfully Expunged");
                return;
            } else {
                System.out.println("Invalid Account Number");
                System.out.print("Would You Like to Exit ? (Y/N)");
                if (in.next().equals("Y")) {
                    return;
                }
            }
        } while (true);
    }

    private void listAccount() {
        ArrayList<Account> accounts = myBank.getListAccount();
        for (Account account : accounts) {
            System.out.println("Name            : " + account.getFirstName() + " " + account.getSurName());
            System.out.println("Account Number  : " + account.getAccNo());
            System.out.println("Balance         : " + account.getBalance());
            System.out.println();
        }

    }

    private void transfer(Account sender) {
        do {
            System.out.print("Please Insert Account Number: ");
            int accNo = in.nextInt();
            Account account = myBank.getAccountAdmin(accNo);
            if (account != null) {
                do {
                    System.out.print("Please Insert Mmount to Transfer: ");
                    int amount = in.nextInt();
                    if (sender.getBalance() > amount) {
                        sender.Withdrawal(amount);
                        account.deposit(amount);
                        return;
                    } else {
                        System.out.println("The Amount You Entered Exceeds Your Existing Funds");
                        System.out.print("Exit ? (y/n)");
                        if (in.next().equals("y")) {
                            return;
                        }
                    }
                } while (true);
            } else {
                System.out.println("Invalid Account Number");
                System.out.print("Would You Like to Exit ? (Y/N)");
                if (in.next().equals("Y")) {
                    return;
                }
            }

        } while (true);

    }

    private void payBills(Account account) {
        do {
            System.out.println("Your Bills: " + account.getBills());
            System.out.print("Please Insert the Amount You Want to Pay: ");
            int amount = in.nextInt();
            if (amount > account.getBalance()) {
                System.out.println("The Amount You Entered Exceeds Your Existing Funds");
                System.out.print("Would You Like to Exit ? (Y/N)");
                if (in.next().equals("Y")) {
                    return;
                }
            } else {
                account.pay(amount);
                return;
            }
        } while (true);
    }
}