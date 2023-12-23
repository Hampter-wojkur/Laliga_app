package demoui.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Year;

public class API {

    private HttpResponse<String> getRequest(String leagueId) throws IOException, InterruptedException, URISyntaxException {


        HttpClient httpClient = HttpClient.newHttpClient();

        //Body params
        String url = "https://v3.football.api-sports.io/standings";
        String season = Integer.toString(Year.now().getValue());

        URI uri = new URI(url + "?league=" + leagueId + "&season=" + season);

        HttpRequest request = HttpRequest.newBuilder()
                .header("x-rapidapi-key", "bd33c4b47emsh3d13f151611d742p15c0dbjsnf90f321bb94f")
                .header("Accept", "application/json")
                .uri(uri)
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        API api = new API();
        HttpResponse<String> response = api.getRequest("140");
        System.out.println("Response Body: " + response.body());
    }
}
