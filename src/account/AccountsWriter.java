package account;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AccountsWriter {
    private static final String path="./src/Data/accounts.sikora";
    private static final Logger logger = LogManager.getLogger(AccountsWriter.class.getName());

    private static FileWriter fileStream;
    private static BufferedWriter buffer;
    private static PrintWriter writer;

    private static int getLatestId(){
        ArrayList<Account> accounts = AccountsParser.getAccounts();
        String id = accounts.get(accounts.size()-1).getCredentials()[0];
        return Integer.parseInt(id);
    }

    private static boolean initStreams(){
        try {
            fileStream = new FileWriter(path,true);
            buffer = new BufferedWriter(fileStream);
            writer = new PrintWriter(buffer);
            return true;
        }
        catch (Exception e){
            logger.error("init streams failed "+ e);
            return false;
        }
    }
    public static boolean writeAccount(String username,String hashedPassword){
        if(initStreams()){
            try{
                writer.println("[");
                writer.println("id:"+(getLatestId()+1));
                writer.println("username:"+username);
                writer.println("password:"+hashedPassword);
                writer.println("]");
                writer.close();
                buffer.close();
                fileStream.close();
                return true;
            }catch (Exception e){
                logger.error("Can't close streams" + e);
                return false;
            }

        }
        return false;
    }
}
