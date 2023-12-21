package demoui.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

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
}
