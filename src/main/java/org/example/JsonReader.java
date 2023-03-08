package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.components.*;
import org.example.exceptions.QueryLimitException;
import org.example.exceptions.QueryOffsetException;
import org.example.exceptions.SeasonException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


@Getter
@Setter
public class JsonReader {

    private final static String BASE_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/{REQUEST}.json?limit={LIMIT}&offset={OFFSET}";

    private final static String USER_AGENT = "Mozilla/5.0";
    private final static String DRIVERS = "drivers";
    private final static String QUALIFYING = "qualifying";
    private final static String DRIVER_STANDINGS = "driverStandings";
    private final static String CIRCUITS = "circuits";
    private final static String SEASONS = "seasons";
    private final static String STATUS = "status";
    private final static String LAP_TIMES = "laps";
    private final static String RESULTS = "results";

    private String series;
    private int season;
    private int limit;
    private int offset;

    private int round;

    public final static int NO_SEASON = -1;
    public final static int DEFAULT_LIMIT = 3;
    public final static int DEFAULT_OFFSET = 0;
    public final static int NO_ROUND = -1;

    public JsonReader(int season, int round, int limit, int offset) {
        setLimit(limit);
        setOffset(offset);
        this.season = season;
        this.series = "f1";
    }

    public JsonReader() {
        this.offset = DEFAULT_OFFSET;
        this.limit = DEFAULT_LIMIT;
        this.season = NO_SEASON;
        this.series = "f1";
    }



    public List<Drivers> getDrivers() throws IOException {
        String url = buildUrl(DRIVERS, NO_ROUND);
        String json = getJson(url);
        return new Parser<>(json, new String[]{"DriverTable", "Drivers"}, Drivers.class).parse();
    }

    public List<Circuits> getCircuits() throws IOException {
        String url = buildUrl(CIRCUITS, NO_ROUND);
        String json = getJson(url).replace("long", "lng");
        return new Parser<>(json, new String[]{"CircuitTable", "Circuits"}, Circuits.class).parse();
    }

    public List<Season> getSeasons() throws IOException {
        String url = buildUrl(SEASONS, NO_ROUND);
        String json = getJson(url);
        return new Parser<>(json, new String[]{"SeasonTable", "Seasons"}, Season.class).parse();
    }

    public List<Qualification> getQualificationResults(int round) throws IOException {
        if (this.season == NO_SEASON) {
            throw new SeasonException("Qualification results requires season to be mentioned");
        }

        String url = buildUrl(QUALIFYING, round);
        String json = getJson(url);
        return new Parser<>(json, new String[]{"RaceTable", "Races", "QualifyingResults"}, Qualification.class).parse();
    }

    public List<RaceResult> getRaceResults(int round) throws IOException {
        if (this.season == NO_SEASON) {
            throw new SeasonException("Race results requires season to be mentioned");
        }

        String url = buildUrl(RESULTS, round);
        String json = getJson(url);
        return new Parser<>(json, new String[]{"RaceTable", "Races", "Results"}, RaceResult.class).parse();
    }

    public List<DriverStandings> getDriverStandings(int round) throws IOException {
        if (this.season == NO_SEASON) {
            throw new SeasonException("Driver standing requires season to be mentioned");
        }

        String url = buildUrl(DRIVER_STANDINGS, round);
        String json = getJson(url);
        return new Parser<>(json, new String[]{"StandingsTable", "StandingsLists", "DriverStandings"}, DriverStandings.class).parse();
    }




    private String buildUrl(String request, int round) {
        String url = BASE_REQ.
                replace("{SERIES}", this.series).
                replace("{SEASON}", this.season == NO_SEASON ? "" : String.valueOf(this.season)).
                replace("{LIMIT}", String.valueOf(this.limit)).
                replace("{OFFSET}", String.valueOf(this.offset)).
                replace("{REQUEST}", request).
                replace(this.series + "//", this.series + "/").
                replace("{ROUND}/", round == NO_ROUND ? "" : String.valueOf(round) + "/").
                replace("/.json", ".json");

        StringBuilder builder = new StringBuilder();
        builder.append("Build url: ").append(url);

        return url;
    }

    private String getJson(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public void setLimit(int limit) {
        if (limit > 1000) {
            throw new QueryLimitException("Limit requires to be no large than 1000");
        } else if (limit < 0) {
            throw new QueryLimitException("Limit requires to be a positive number");
        }

        this.limit = limit;
    }

    public void setOffset(int offset) {
        if (offset < 0) {
            throw new QueryOffsetException("Offset requires to be a positive number");
        }
        this.offset = offset;
    }

}