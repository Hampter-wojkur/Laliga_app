package LeagueData;

public class LeagueData {
    private String id;
    private String[][] data;

    public LeagueData(String idProp,String[][] dataProp) {
        id = idProp;
        data = dataProp;
    }

    public String[][] getData(){
        return data;
    }

    public String getId(){
        return id;
    }
}
