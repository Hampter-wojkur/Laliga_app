package demoui.ui;

import demoui.SignGUI;
import demoui.StartGUI;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainUi {
    private JPanel jp;

    private final String LaLigaID = "140";
    private final String PremierLeagueID = "39";
    private final String SeriaAID = "135";
    private final String BundesligaID = "78";
    private final String Ligue1ID = "61";
    private JTable table;
    private JComboBox leagueCombo;
    private JButton logOutButton;

    public MainUi() throws JSONException, IOException, URISyntaxException, InterruptedException {
        createTable("140");
        createComboBox();
        performLogOutButton();
        comboBoxClicked();
    }
    public JPanel getRootPanel(){
        return jp;
    }
    private void createTable(String leagueId) throws JSONException, IOException, URISyntaxException, InterruptedException {

        String[][] data = API.getTable(leagueId);
//        Object[][] data = {{"1", "Girona", "17", "14", "2", "1", "41:20", "21", "44"},
//                {"2", "Real Madrid", "17", "13", "3", "1", "38:11", "27", "42"}};
        table.setModel(new DefaultTableModel(
                data,
                new String[]{"#", "Team", "M", "W", "D", "L", "G", "GD", "PTS"}
        ));
        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(0).setPreferredWidth(30);
        columns.getColumn(1).setPreferredWidth(200);

        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);

        columns.getColumn(0).setCellRenderer(centerRender);
        for (int i = 2; i < 9; i++){
            columns.getColumn(i).setCellRenderer(centerRender);
        }
    }

    private void createComboBox(){
        String[] leagues = { "LaLiga", "Premier League", "Bundesliga", "Serie A", "Ligue 1" };
        leagueCombo.setModel(new DefaultComboBoxModel(leagues));

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

    private void comboBoxClicked(){
        leagueCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String league = (String) leagueCombo.getSelectedItem();

                    try {
                        switch (league) {
                            case "LaLiga":
                                createTable(LaLigaID);
                                break;
                            case "Premier League":
                                createTable(PremierLeagueID);
                                break;
                            case "Bundesliga":
                                createTable(BundesligaID);
                                break;
                            case "Serie A":
                                createTable(SeriaAID);
                                break;
                            case "Ligue 1":
                                createTable(Ligue1ID);
                                break;
                        }
                    } catch (JSONException | IOException | URISyntaxException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
