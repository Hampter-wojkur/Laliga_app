package demoui.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainUi {
    private JPanel jp;
    private JTable table;
    private JComboBox leagueCombo;
    private JButton logOutButton;

    public MainUi(){
        createTable();
    }
    public JPanel getRootPanel(){
        return jp;
    }

    private void createTable(){
        Object[][] data = {{"1", "Girona", "17", "14", "2", "1", "41:20", "21", "44"},
                {"2", "Real Madrid", "17", "13", "3", "1", "38:11", "27", "42"}};
        table.setModel(new DefaultTableModel(
                data,
                new String[]{"#", "Team", "M", "W", "T", "L", "G", "GD", "PTS"}
        ));
    }
}
