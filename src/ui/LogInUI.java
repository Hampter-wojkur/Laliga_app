package ui;

import GUI.StartGUI;
import GUI.SignGUI;
import GUI.MainGUI;
import login.Login;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInUI {
    private JPanel jp;
    private JPanel buttonPanel;
    private JPanel textFieldPanel;
    private JPanel logoPanel;
    private JButton logButton;
    private JButton signButton;
    private JTextField usernameField;
    private JLabel imageLabel;
    private JPasswordField passwordPasswordField;
    private JLabel errorLabel;
    private JPanel errorPanel;
    private JLabel passwdLabel;
    private JLabel usernameLabel;

    private static final Logger logger = LogManager.getLogger(LogInUI.class.getName());

    public LogInUI() {
        performListeners();
        Login.initAccounts();
        Login.resetFields();
        createErrorLabel();
    }


    private void moveToSignUpGUI(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        SignGUI signGUI = new SignGUI();
        try {
            signGUI.createGUI();
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void moveToMainGUI(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        MainGUI mainUi = new MainGUI();
        try {
            mainUi.createGUI();
        } catch (JSONException | IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public JPanel getRootPanel() {
        return jp;
    }

    private void performListeners() {
        performLogInButton();
        performSignUpButton();
    }

    private void createErrorLabel(){
        errorLabel.setVisible(false);
    }
    private String getTextFromUsernameField(){
        return usernameField.getText();
    }

    private String getTextFromPasswordField(){
        return new String(passwordPasswordField.getPassword());
    }

    private void performSignUpButton() {
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToSignUpGUI(e);
            }
        });
    }

    private void handleError(){
        errorLabel.setVisible(true);
    }

    private void performLogInButton() {
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Setting Login class");
                Login.setUsername(getTextFromUsernameField());
                Login.setPassword(getTextFromPasswordField());
                if(Login.processLogin()){
                    logger.debug("Succesfully logged");
                    moveToMainGUI(e);
                }
                else {
                    logger.debug("Wrong Credentials!");
                    handleError();
                }
            }
        });
    }
}
