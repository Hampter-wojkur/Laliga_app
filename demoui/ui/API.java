package demoui.ui;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Year;
import java.util.Arrays;

public class API {

    public static HttpResponse<String> getRequest(String leagueId) throws IOException, InterruptedException, URISyntaxException {


        HttpClient httpClient = HttpClient.newHttpClient();

        //Body params
        String url = "https://v3.football.api-sports.io/standings";
        String season = Integer.toString(Year.now().getValue());

        URI uri = new URI(url + "?league=" + leagueId + "&season=" + season);

        HttpRequest request = HttpRequest.newBuilder()
                .header("x-rapidapi-key", "2ed2eab0980df47f8bd6dbc9d764527d")
                .header("x-rapidapi-host", "v3.football.api-sports.io")
                .header("Accept", "application/json")
                .uri(uri)
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static String[][] getTable(String leagueId) throws IOException, URISyntaxException, InterruptedException, JSONException {

        HttpResponse<String> response = getRequest(leagueId);
        String responseBody = response.body();

        JSONObject obj_JSONObject = new JSONObject(responseBody);
        JSONArray arr_JSONArray = obj_JSONObject.getJSONArray("response");
        JSONObject obj_JSONObject2 = arr_JSONArray.getJSONObject(0);
        JSONObject obj_JSONObject3 = obj_JSONObject2.getJSONObject("league");
        JSONArray arr_JSONArray2 = obj_JSONObject3.getJSONArray("standings");
        JSONArray arr_JSONArray3 = arr_JSONArray2.getJSONArray(0);
        int rows = arr_JSONArray3.length();
        int columns = 9;

        String[][] table = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            String place = Integer.toString(arr_JSONArray3.getJSONObject(i).getInt("rank"));
            String team = arr_JSONArray3.getJSONObject(i).getJSONObject("team").getString("name");
            String matches = Integer.toString(arr_JSONArray3.getJSONObject(i).getJSONObject("all").getInt("played"));
            String wins = Integer.toString(arr_JSONArray3.getJSONObject(i).getJSONObject("all").getInt("win"));
            String draws = Integer.toString(arr_JSONArray3.getJSONObject(i).getJSONObject("all").getInt("draw"));
            String loses = Integer.toString(arr_JSONArray3.getJSONObject(i).getJSONObject("all").getInt("lose"));
            String points = Integer.toString(arr_JSONArray3.getJSONObject(i).getInt("points"));
            String goalsDiff = Integer.toString(arr_JSONArray3.getJSONObject(i).getInt("goalsDiff"));

            String goalsFor = Integer.toString(arr_JSONArray3.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for"));
            String goalsAgainst = Integer.toString(arr_JSONArray3.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against"));
            String goalComp = goalsFor + ":" + goalsAgainst;

            String[] params = {place, team, matches, wins, draws, loses, goalComp, goalsDiff, points};

            for (int j = 0; j < columns; j++) {
                table[i][j] = params[j];
            }
        }

        return table;
    }

//    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, JSONException {
//        System.out.println(Arrays.deepToString(getTable("140")));
//    }
}
