import login.Login;

public class Test {
    public static void main(String args[]){
        Login.setUsername("test"); // to handler
        Login.setPassword("password"); // to handler
        Login.initAccounts(); // this to thread
        Login.processLogin(); // this to handler
    }
}
