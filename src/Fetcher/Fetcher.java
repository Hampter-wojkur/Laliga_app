package Fetcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.API;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Fetcher extends Thread {
    private JTable table;
    private String leagueId;
    private static final Logger logger = LogManager.getLogger(Fetcher.class.getName());

    private JLabel loadingBar;

    public Fetcher(JTable tableProp,JLabel loadingBarProp, String leagueIdProp){
        table = tableProp;
        leagueId = leagueIdProp;
        loadingBar = loadingBarProp;
    }

    @Override
    public void run() {
        try {
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
            logger.debug("Finished fetching everything");
            loadingBar.setText("");
        }
        catch (Exception e) {
            logger.error("Error !");
        }

    }
}
