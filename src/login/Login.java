package login;
import account.Account;
import account.AccountsParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Login {
//    private static final Logger logger =
    private static String username;
    private static String password;

    private static String errorMsg;
    private static ArrayList<Account> accounts;

    private static final Logger logger = LogManager.getLogger(Login.class.getName());
    public static void setUsername(String tempUsername){
        username = tempUsername;
        logger.info("Set username for "+ username);
    }

    public static void setPassword(String tempPassword){
        password = tempPassword;
        logger.info("Set password for " + password);
    }

    public static String getErrorMessage(){
        return errorMsg;
    }

    public static void initAccounts(){
        accounts = AccountsParser.getAccounts();
    }
    public static boolean processLogin(){
        logger.info("Start process of login");
        if(username == null || password == null){
            errorMsg = "Username or password is not set!";
            logger.error(errorMsg);
            return false;
        }
        AccountsParser.printAllAccounts();

        return true;
    }
}
