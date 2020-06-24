import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AtmController {

    private User currentUser;   //current user
    private Map<String, Account> currentUserAccounts;  //accounts associated with the current user, maps accountId to Account domain object
    private Account currentAccount;     //account the authenticated user is currently looking at
    private BankIntegrationAdapter bank;
    private boolean authenticated;

    public AtmController(BankIntegrationAdapter bank) {
        this.bank = bank;
    }

    //UI should call this function with the card number and PIN in order to verify the user
    //since we are not implementing any REST API's, our contract with the implementer of the UI can be that if the verification
    //is successful, we return true in this function, and otherwise return false (instead of HTTP 200/404 if implementing REST API's)
    public boolean insertCardAndVerifyUser(String cardNumber, int pin) {
        String[] accounts = bank.authenticateUser(cardNumber, pin);
        if (accounts == null)
            return false;

        //authenticated at this point
        authenticated = true;
        //create user domain object
        currentUser = new User(cardNumber);

        //create account domain objects
        currentUserAccounts = new HashMap<>();
        for (int i=0; i < accounts.length; i++) {
            String[] accountInfo = accounts[i].split(",");
            Account account = new Account(accountInfo[0], accountInfo[1], Integer.parseInt(accountInfo[2]));
            currentUserAccounts.put(accountInfo[0], account);
        }
        return true;
    }

    //UI is to call this function after successful authentication to get the account ID's for the authenticated user
    //returns null if user not authenticated
    public Set<String> getAccountIDSForUser() {
        if (!authenticated) {
            return null;
        }
        return currentUserAccounts.keySet();
    }

    //UI is to call this function passing in the accountID to choose an account for the authenticated user
    //if successfully chosen, return true, otherwise (or if user not authenticated in the first place), return false
    public boolean chooseAccount(String accountID) {
        if (!authenticated) {
            return false;
        }
        if (currentUserAccounts.keySet().contains(accountID)) {
            currentAccount = currentUserAccounts.get(accountID);
            return true;
        }
        return false;
    }

    //If withdrawal is taking place, UI is to call this function passing in the amount to withdraw, and returns the resulting balance
    //returns null if user not authenticated
    public Integer withdraw(int amount) {
        if (!authenticated) {
            return null;
        }
        currentAccount.withdraw(amount);
        return currentAccount.view();
    }

    //If deposit is taking place, UI is to call this function passing in the amount to deposit, and returns the resulting balance
    //returns null if user not authenticated
    public Integer deposit(int amount) {
        if (!authenticated) {
            return null;
        }
        currentAccount.deposit(amount);
        return currentAccount.view();
    }

    //If user wants to view the balance, UI is to call this function; returns null if user not authenticated
    public Integer view() {
        if (!authenticated) {
            return null;
        }
        return currentAccount.view();
    }
}
