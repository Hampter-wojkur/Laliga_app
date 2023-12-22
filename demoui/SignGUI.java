package demoui;

import demoui.ui.SignUpUI;

import javax.swing.*;

public class SignGUI {
    public static void createGUI() {
        SignUpUI ui = new SignUpUI();
        JPanel jp = ui.getRootPanel();
        JFrame jf = new JFrame("Sign up");

        jf.setContentPane(jp);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}


