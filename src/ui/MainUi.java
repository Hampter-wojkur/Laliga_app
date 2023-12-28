package ui;

import GUI.StartGUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import Fetcher.Fetcher;

public class MainUi {
    private JPanel jp;

    private static final Logger logger = LogManager.getLogger(MainUi.class.getName());
    private final String LaLigaID = "140";
    private final String PremierLeagueID = "39";
    private final String SeriaAID = "135";
    private final String BundesligaID = "78";
    private final String Ligue1ID = "61";

    private String currentLeagueId;

    private ArrayList<String[]> leaguesData;
    private JTable table;
    private JComboBox leagueCombo;
    private JButton logOutButton;
    private JLabel leagueLabel;
    private JLabel loadingBar;
    private JButton refreshButton;

    private void setCurrentLeague(String leagueId){
        currentLeagueId = leagueId;
    }
    private void setLoading(){
        loadingBar.setText("Loading...");
    }
    public MainUi() throws JSONException, IOException, URISyntaxException, InterruptedException {
        // createTable("140");
        setLoading();
        new Thread(new Fetcher(table,loadingBar,"140")).start();
        leagueLabelChange("LaLiga", LaLigaID);
        createComboBox();
        performLogOutButton();
        performRefreshButton();
        comboBoxClicked();
    }
    public JPanel getRootPanel(){
        return jp;
    }

    private void createComboBox(){
        String[] leagues = { "LaLiga", "Premier League", "Bundesliga", "Serie A", "Ligue 1" };
        leagueCombo.setModel(new DefaultComboBoxModel(leagues));

    }

    private void leagueLabelChange(String leagueName, String leagueId){
        leagueLabel.setText(leagueName);
        Icon icon = new ImageIcon("./src/"+leagueId + ".png");
        leagueLabel.setIcon(icon);
    }

    private void performRefreshButton(){
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                refreshLeague();
            }
        });
    }
    private void performLogOutButton(){
        logOutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                StartGUI startGUI = new StartGUI();
                startGUI.createGUI();
            }
        });
    }

    private void refreshLeague(){
        setLoading();
        new Thread(new Fetcher(table,loadingBar,currentLeagueId,true)).start();
    }

    private void comboBoxClicked(){
        leagueCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String league = (String) leagueCombo.getSelectedItem();
                    logger.debug("Switching league to "+ league);
                    try {
                        switch (league) {
                            case "LaLiga":
                                setLoading();
                                new Thread(new Fetcher(table,loadingBar,LaLigaID)).start();
                                setCurrentLeague(LaLigaID);
                                leagueLabelChange("LaLiga", LaLigaID);
                                break;
                            case "Premier League":
                                setLoading();
                                new Thread(new Fetcher(table,loadingBar,PremierLeagueID)).start();
                                setCurrentLeague(PremierLeagueID);
                                leagueLabelChange("Premier League", PremierLeagueID);
                                break;
                            case "Bundesliga":
                                setLoading();
                                new Thread(new Fetcher(table,loadingBar,BundesligaID)).start();
                                setCurrentLeague(BundesligaID);
                                leagueLabelChange("Bundesliga", BundesligaID);
                                break;
                            case "Serie A":
                                setLoading();
                                new Thread(new Fetcher(table,loadingBar,SeriaAID)).start();
                                setCurrentLeague(SeriaAID);
                                leagueLabelChange("Serie A", SeriaAID);
                                break;
                            case "Ligue 1":
                                setLoading();
                                new Thread(new Fetcher(table,loadingBar,Ligue1ID)).start();
                                setCurrentLeague(Ligue1ID);
                                leagueLabelChange("Ligue 1", Ligue1ID);
                                break;
                        }
                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
