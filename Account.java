public class Account {
    private String FirstName, SurName;
    // private int min = 1;
    // private int max = 9;
    // private int range = max - min;
    // private int balance = (int)(Math.random() * range) + min;
    private int Balance = 0;
    private int AccNo;
    private int Pin;
    private int Bills = 10000;

    public Account(String f, String s, int AccNo, int pin){
        this.FirstName = f;
        this.SurName = s;
        this.AccNo = AccNo;
        this.Pin = pin;
    }

    public int getAccNo(){
        return this.AccNo;
    }
    
    public String getFirstName(){
        return this.FirstName;
    }

    public String getSurName(){
        return this.SurName;
    }

    public int getBalance(){
        return this.Balance;
    }

    public int getBills(){
        return this.Bills;
    }

    public int getPin(){
        return this.Pin;
    }

    public void deposit(int amount){
        Balance += amount;        
    }

    public void Withdrawal(int amount){
        Balance -= amount;        
    }

    public void pay(int amount){
        if (amount > this.Bills){
            this.Balance -= Bills;
            this.Bills = 0;            
        } else {
            this.Bills -= amount;
            this.Balance -= amount;
        }
        
    }
    

}