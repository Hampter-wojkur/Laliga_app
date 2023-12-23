package signUp;
import account.Account;
import account.AccountsParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;

public class SignUp {

    private static final Logger logger = LogManager.getLogger(SignUp.class.getName());
    private static String username;
    private static String password;
    private static String confirmationPassword;

    private static boolean checkIfAccountExist(String username) {
        ArrayList<Account> accounts = AccountsParser.getAccounts();
        for (Account account : accounts) {
            if (account.getCredentials()[1].equals(username)) {
                return true;
            }
        }

        return false;
    }

//    private static String encryptPassword(String password){
//
//    }
//    private static boolean addAccount(String username, String hashedPassword){
//
//    }

    public static boolean processSignUp() {
        if(!username.isEmpty() && !password.isEmpty() && !confirmationPassword.isEmpty() && !checkIfAccountExist(username)){
            return false;
        }

        return true;
    }
}