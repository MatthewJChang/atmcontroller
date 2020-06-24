import java.util.List;

//domain class for a user in the system
public class User {
    private String cardNumber;
    private List<Account> accounts;

    public User(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    //in the future, can add more methods relating to user information
}
