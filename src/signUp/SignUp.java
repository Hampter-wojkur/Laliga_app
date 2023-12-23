package signUp;
import account.Account;
import account.AccountsParser;
import account.AccountsWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;
public class SignUp {

    private static final Logger logger = LogManager.getLogger(SignUp.class.getName());
    private static String username;
    private static String password;
    private static String confirmationPassword;

    public static void setUsername(String tempUsername){
        username = tempUsername;
    }

    public static void setPassword(String tempPassword){
        password = tempPassword;
    }

    public static void setConfirmationPassword(String tempConfirmationPassword){
        confirmationPassword = tempConfirmationPassword;
    }

    private static boolean isAccountNotExist(String username) {
        ArrayList<Account> accounts = AccountsParser.getAccounts();
        for (Account account : accounts) {
            logger.info("username: "+username);
            logger.info("username: "+account.getCredentials()[1]);
            if (account.getCredentials()[1].equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean processSignUp() {
        logger.info("Started signUp");
        if(username.isEmpty() || password.isEmpty() || confirmationPassword.isEmpty() || isAccountNotExist(username) || !confirmationPassword.equals(password)){
            logger.error("Account exist or fields are not the same");
            return false;
        }

        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());

        return AccountsWriter.writeAccount(username,hashedPassword);
    }
}