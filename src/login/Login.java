package login;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Login {
//    private static final Logger logger =
    private static String username;
    private static String password;

    private static final Logger logger = LogManager.getLogger(Login.class.getName());
    public static void setUsername(String tempUsername){
        username = tempUsername;
    }

    public static void setPassword(String tempPassword){
        password = tempPassword;
    }

    public static boolean processLogin(){
        logger.info("Start of logging!");
        logger.debug("testt");
        return true;
    }
}
