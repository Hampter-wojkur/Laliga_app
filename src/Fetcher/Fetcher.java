package Fetcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.API;
import LeagueData.LeagueData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

public class Fetcher extends Thread {
    private JTable table;
    private String leagueId;
    private static final Logger logger = LogManager.getLogger(Fetcher.class.getName());
    private JLabel loadingBar;

    private static ArrayList<LeagueData> leaguesData = new ArrayList<LeagueData>(); // to easily fetch all data !

    public Fetcher(JTable tableProp,JLabel loadingBarProp, String leagueIdProp){
        table = tableProp;
        leagueId = leagueIdProp;
        loadingBar = loadingBarProp;
    }

    private boolean isLeagueDataExist(){
        for(LeagueData leagueData: leaguesData){
            if(leagueData.getId().equals(leagueId)) {
                return true;
            }
        }
        return false;
    }

    private String[][] getDataFromLeague(){
        for(LeagueData leagueData: leaguesData){
            if(leagueData.getId().equals(leagueId)){
                return leagueData.getData();
            }
        }
        return null;
    }

    private void setTable(String[][] data){
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
        loadingBar.setText("");
    }

    @Override
    public void run() {
        if(!isLeagueDataExist()){
            try {
                logger.debug("Sending request");
                String[][] data = API.getTable(leagueId);

                LeagueData leagueData = new LeagueData(leagueId,data);
                leaguesData.add(leagueData); // adding league to array... later you can fast access this data without sending request
                setTable(data);
                logger.debug("Finished fetching everything");

            }
            catch (Exception e) {
                logger.error("Error !");
            }
        }
        else { // fast access straight from array ;)
            String[][] data = getDataFromLeague();
            logger.debug("Getting straight from array");
            setTable(data);
            logger.debug("Finished getting from array");
        }
    }
}
