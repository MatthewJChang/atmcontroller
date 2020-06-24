import java.util.HashMap;
import java.util.Map;

//dummy implementation of BankIntegrationAdapter for testing purposes
public class DummyBank implements BankIntegrationAdapter {

    private Map<String, Integer> userPinCombos;
    private Map<String, String[]> accountInfo;

    public DummyBank() {
        userPinCombos = new HashMap<>();
        userPinCombos.put("GJWP32042", 4251);
        userPinCombos.put("GWRH92253", 1290);
        userPinCombos.put("HIJF11936", 9405);
        userPinCombos.put("ABCD14295", 3081);

        accountInfo = new HashMap<>();
        String[] accounts = new String[]{"YWOX3028,Checking,349", "IBPJ3284,Checking,32", "IRHP31110,Savings,500", "IWGU20045,CD,3019"};
        accountInfo.put("GJWP32042", accounts);
        accounts = new String[]{"YABB3314,Checking,9944"};
        accountInfo.put("GWRH92253", accounts);
        accounts = new String[]{"UQFB2411,Checking,-41194", "AAAP4390,Savings,0"};
        accountInfo.put("HIJF11936", accounts);
        accounts = new String[]{};      //simulating bizarre scenario where user is authenticated but has no accounts
        accountInfo.put("ABCD14295", accounts);
    }

    public String[] authenticateUser(String cardNumber, int pin) {

        if (userPinCombos.containsKey(cardNumber)) {
            if (userPinCombos.get(cardNumber) != pin) {
                return null;
            }
            String[] accounts = accountInfo.get(cardNumber);
            if (accounts.length > 0)
                return accounts;
        }
        return null;
    }
}
