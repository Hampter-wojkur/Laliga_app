package demoui.ui;

import com.sun.tools.javac.Main;
import demoui.MainGUI;
import demoui.SignGUI;
import demoui.StartGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                mainUi.createGUI();
            }
        });
    }
}
