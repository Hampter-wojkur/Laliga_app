package ui;

import GUI.StartGUI;
import GUI.SignGUI;
import GUI.MainGUI;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

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

    public LogInUI(){
        performSignUpButton();
        performLogInButton();
    }

    public JPanel getRootPanel(){
        return jp;
    }

    private void performSignUpButton(){
        signButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                SignGUI signGUI = new SignGUI();
                signGUI.createGUI();
            }
        });
    }

    private void performLogInButton(){
        logButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
        });
    }
}
