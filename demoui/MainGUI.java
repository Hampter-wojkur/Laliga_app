package demoui;

import demoui.ui.MainUi;
import org.json.JSONException;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainGUI {
    public static void createGUI() throws JSONException, IOException, URISyntaxException, InterruptedException {
        MainUi ui = new MainUi();
        JPanel jp = ui.getRootPanel();
        JFrame jf = new JFrame("Football stats");

        jf.setContentPane(jp);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createGUI();
                } catch (JSONException | IOException | URISyntaxException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
