//all banks integrating with the ATM in the future must implement this adapter interface (for instance, authenticateUser method defined here
//will call the bank's specific API's for verifying the user based on card number and PIN and getting account info in return
public interface BankIntegrationAdapter {

    //to keep things simple, we can have a contract with all future banks which takes in card number and PIN in order to verify the user,
    //and returns account information represented by a String array where each element is a comma separated string with the format:
    //AccountId, AccountName, Balance
    //Otherwise, a null String array will be returned
    //This could represent 3 scenarios:
    // 1)card number was invalid
    // 2) card number valid but PIN was wrong
    // 3) for some bizarre reason the user was authenticated but has no accounts
    // I assume the ATM doesn't need to know the exact reason why someone is denied access for our purposes

    //ex. ["YWOX3028, Checking, 349", "IBPJ3284, Checking, 32", "IRHP31110, Savings, 500", "IWGU20045, CD, 3019"]
    //(this user has two checking accounts)
    public String[] authenticateUser(String cardNumber, int pin);

    //can add more methods in the future below to get information regarding authenticated users which can then go in the Users domain object
}
