package demoui.ui;

import demoui.SignGUI;

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

    public MainUi(){
        createTable();
        createComboBox();
        performLogOutButton();
        comboBoxClicked();
    }
    public JPanel getRootPanel(){
        return jp;
    }
    // funckja w klasie MainUi bierze instancje api z klasy api, jako parametr w api funkcja get request
    private void createTable(){
        Object[][] data = {{"1", "Girona", "17", "14", "2", "1", "41:20", "21", "44"},
                {"2", "Real Madrid", "17", "13", "3", "1", "38:11", "27", "42"}};
        table.setModel(new DefaultTableModel(
                data,
                new String[]{"#", "Team", "M", "W", "T", "L", "G", "GD", "PTS"}
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
                SignGUI signGUI = new SignGUI();
                signGUI.createGUI();
            }
        });
    }

    private void comboBoxClicked(){
        leagueCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String leagueId = (String) leagueCombo.getSelectedItem();

                    switch (leagueId){
                        case LaLigaID:
                            break;
                        case PremierLeagueID:
                            break;
                        case SeriaAID:
                            break;
                        case BundesligaID:
                            break;
                        case Ligue1ID:
                            break;
                        default:
                    }
                }
            }
        });
    }
}
