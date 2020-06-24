import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class AtmControllerTest {

    @Test
    public void testAtmController() {
        DummyBank bank = new DummyBank();
        AtmController atmController = new AtmController(bank);
        Set<String> accountIDS = atmController.getAccountIDSForUser(); //test unauthenticated user
        assert accountIDS == null;

        boolean accountResult = atmController.chooseAccount("YWOX3028");  //test unauthenticated user
        assert accountResult == false;

        Integer balance = atmController.withdraw(232);  //test unauthenticated user
        assert balance == null;

        balance = atmController.deposit(21550);  //test unauthenticated user
        assert balance == null;

        balance = atmController.view();  //test unauthenticated user
        assert balance == null;

        boolean result = atmController.insertCardAndVerifyUser("GJWP32042", 3256);  //test wrong pin
        assert result == false;
        result = atmController.insertCardAndVerifyUser("GJWP32042", 4251);  //test right pin
        assert result == true;
        accountIDS = atmController.getAccountIDSForUser();
        assert accountIDS != null;
        assert accountIDS.containsAll(new ArrayList<>(Arrays.asList("YWOX3028", "IBPJ3284", "IRHP31110", "IWGU20045")));
        accountResult = atmController.chooseAccount("YWOX3028");
        assert accountResult == true;
        atmController.withdraw(42); //testing successful withdrawal
        assert atmController.view() == 307;
        atmController.withdraw(32030);  //testing that when you try to withdraw to go into negative balance, balance isn't changed
        assert atmController.view() == 307;
        atmController.deposit(302); //testing successful deposit
        assert atmController.view() == 609;
        atmController.deposit(-323);    //testing nothing happens when you deposit negative amount which shouldn't be allowed
        assert atmController.view() == 609;
        atmController.withdraw(-33);   //testing nothing happens when you withdraw negative amount which shouldn't be allowed
        assert atmController.view() == 609;

        //testing second person
        result = atmController.insertCardAndVerifyUser("HIJF11936", 9405);  //test right pin
        assert result == true;
        accountIDS = atmController.getAccountIDSForUser();
        assert accountIDS.contains("UQFB2411");
        accountResult = atmController.chooseAccount("UQFB2411");
        assert accountResult == true;
        assert atmController.view() == -41194;

    }
}
