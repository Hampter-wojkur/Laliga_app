package demoui.ui;

import demoui.StartGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public SignUpUI(){
        performCreateAccButton();
    }

    public JPanel getRootPanel(){
        return jp;
    }

    private void performCreateAccButton(){
        createAccButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                StartGUI startGUI= new StartGUI();
                startGUI.createGUI();
            }
        });
    }
}
