package account;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountsParser{
    private static FileReader fileStream;

    private static final Logger logger = LogManager.getLogger(AccountsParser.class.getName());
    private static BufferedReader reader;
    private static final String path="./src/Data/accounts.sikora";

    public static void printAllAccounts(){ // debug function
        for(Account temp: getAccounts()){
            String[] credentials = temp.getCredentials();
            logger.debug(credentials[0]+" "+ credentials[1]+" "+ credentials[2]);
        }
    }

    public static ArrayList<Account> getAccounts(){
        if(initStreams()) {
            String line;
            try {
                ArrayList<Account> accountsList = new ArrayList<Account>();
                while ((line = reader.readLine()) != null) {
                    if(line.equals("[")){
                        line = reader.readLine();
                        String id = line.split(":")[1];
                        line = reader.readLine();
                        String login = line.split(":")[1];
                        line = reader.readLine();
                        String hashedPassword = line.split(":")[1];
                        accountsList.add(new Account(id,login,hashedPassword));
                    }
                }
                return accountsList;
            }

            catch (Exception e){
                logger.error("Can't read file!");
                return null;
            }
        }
        return null;
    }
    private static boolean initStreams() {
        try {
            logger.info("Init streams!");
            fileStream = new FileReader(path);
            reader = new BufferedReader(fileStream);
            return true;
        }
        catch(Exception e){
            logger.error("Cannot init stream file!"+e);
            return false;
        }
    }
}
