package ui;

import GUI.StartGUI;
import signUp.SignUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpUI {
    private JPanel jp;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JLabel signLabel;
    private JPanel dataPanel;
    private JPanel labelPanel;
    private JLabel usernameLabel;
    private JLabel repeatPasswordLabel;
    private JLabel passworddLabel;
    private JPanel createAccPanel;
    private JButton createAccButton;
    private static final Logger logger = LogManager.getLogger(SignUpUI.class.getName());


    public SignUpUI(){
        performCreateAccButton();
    }

    public JPanel getRootPanel(){
        return jp;
    }

    private void moveToMainGUI(ActionEvent e){
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        StartGUI startGUI= new StartGUI();
        startGUI.createGUI();
    }

    private String getUsernameFromField(){
        return usernameField.getText();
    }

    private String getPasswordFromField(){
        return new String(passwordField.getPassword());
    }

    private String getConfirmationPasswordFromField(){
        return new String(repeatPasswordField.getPassword());
    }

    private void performCreateAccButton(){
        createAccButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp.setUsername(getUsernameFromField());
                SignUp.setPassword(getPasswordFromField());
                SignUp.setConfirmationPassword(getConfirmationPasswordFromField());
                if(SignUp.processSignUp()){
                    moveToMainGUI(e);
                }
                else {
                    logger.debug("Something went wrong!");
                }
            }
        });
    }
}
