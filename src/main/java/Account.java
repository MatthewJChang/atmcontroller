//domain class for an account
public class Account {

    private String accountId;   //unique identifier of account; generated at creation
    private String accountName; //checking, savings, CD, etc.

    private int balance;


    public Account(String accountId, String accountName, int startingBalance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = startingBalance;
    }

    public void deposit(int amount) {
        if (amount >= 0) {
            this.balance = this.balance + amount;
        }
    }

    //rejects the transaction if withdrawing causes you to go negative
    public void withdraw(int amount) {
        if (amount >= 0) {
            int temp = this.balance - amount;
            if (temp > 0) {
                this.balance = temp;
            }
        }
    }

    public int view() {
        return this.balance;
    }
}
