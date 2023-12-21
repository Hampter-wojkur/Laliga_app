package demoui;

import demoui.ui.LogInUI;

import javax.swing.*;

public class StartGUI {
    public static void createGUI() {
        LogInUI ui = new LogInUI();
        JPanel jp = ui.getRootPanel();
        JFrame jf = new JFrame("Log in");

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
