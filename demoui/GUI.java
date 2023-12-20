package demoui;

import demoui.ui.MainUi;

import javax.swing.*;

public class GUI {
    public static void createGUI() {
        MainUi ui = new MainUi();
        JPanel jp = ui.getRootPanel();
        JFrame jf = new JFrame("Football stats");

        jf.setLocationRelativeTo(null);
        jf.setContentPane(jp);
        jf.pack();
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
