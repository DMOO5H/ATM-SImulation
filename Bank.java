import java.util.ArrayList;

public class Bank {
    private String bankName;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public Bank(String bName){
        this.bankName = bName;
    }

    public void addAccount (Account newAccount){
        accounts.add(newAccount);
    }

    public String getBankName(){
        return this.bankName;
    }

    public ArrayList<Account> getListAccount(){
        return this.accounts;
    }

    public Account getAccount(int accNo, int pin){
        for (Account account : accounts) {
            if (account.getAccNo() == accNo && account.getPin() == pin){
                return account;
            }
        };
        return null;
    }

    public Account getAccountAdmin(int accNo){
        for (Account account : accounts) {
            if (account.getAccNo() == accNo){
                return account;
            }
        };
        return null;
    }

    public void delAccount(Account account){
        accounts.remove(account);
    }
}