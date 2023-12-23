package login;

public class Account {
    private String username;
    private String id;
    private String password;
    public Account(String id,String username,String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String[] getCredentials(){
        String[] credentials = {this.username,this.password};
        return credentials;
    }
}
