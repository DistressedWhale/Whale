import com.mashape.unirest.http.Unirest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;


public class NextSong {
    static final String ID = "1f60082d34864c39b752ace10f5ff00d";
    static final String Secret = "b933f2341509494da1a6e878e0338e5e";

    public NextSong(String initFirstSongID) {
        String firstSongID = initFirstSongID;
    }

    public static String giveMeNextSongInfo(String lastSongID, String OAuth) throws Exception {
        String myURL = "https://api.spotify.com/v1/recommendations?min_energy=0.4&market=US&seed_tracks=0c6xIDDpzE81m2q797ordA&seed_artists=4NHQUGzhtTLFvgF5SZesLK&limit=1&min_popularity=90";
        return Unirest.get(myURL)
                .header("Authorization", "Bearer "+OAuth)
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asString()
                .getBody();
    }

    public static String giveMeAuth() throws Exception {
        //String literalDumpster = "https://requestb.in/qznm79qz";
        String myURL = "https://accounts.spotify.com/api/token";
        ObjectMapper mappyMcMapface = new ObjectMapper();

        byte[] encodedBytes = (ID+":"+Secret).getBytes(StandardCharsets.UTF_8);
        Encoder encode = Base64.getEncoder();
        String encodedString = encode.encodeToString(encodedBytes);

        String result = Unirest.post(myURL)
                .header("Authorization", "Basic " + encodedString)
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("grant_type=client_credentials")
                .asString()
                .getBody();

        AuthResponseModel foo = mappyMcMapface.readValue(result, AuthResponseModel.class);
        return foo.getAccessToken();
    }

    public static String stripOutPreviewURL(String inputURL) {
        int startIndex = inputURL.indexOf("preview_url") + 16; //get location of preview URL
        int endIndex = inputURL.indexOf("\"", startIndex+16);
        return inputURL.substring(startIndex, endIndex);
    }

    public static String[] stripOutSeeds(String inputURL) {
        int seedIndex = inputURL.indexOf("seeds");

        int firstIDIndex = inputURL.indexOf("id", seedIndex);
        int firstSeedStart = inputURL.indexOf("\"", firstIDIndex+4) + 1;
        int firstSeedEnd = inputURL.indexOf("\"", firstSeedStart+1);

        int secondIDIndex = inputURL.indexOf("id", firstSeedEnd);
        int secondSeedStart = inputURL.indexOf("\"", secondIDIndex+4) + 1;
        int secondSeedEnd = inputURL.indexOf("\"", secondSeedStart+1);

        String firstSeed = inputURL.substring(firstSeedStart, firstSeedEnd);
        String secondSeed = inputURL.substring(secondSeedStart, secondSeedEnd);

        String[] wrapup = {firstSeed, secondSeed};
        return wrapup;
    }

    public static void main(String[] args) throws Exception {
        String OAuth = giveMeAuth();
        System.out.println("OAuth: " + OAuth);
        
        String s = giveMeNextSongInfo("hi", OAuth);
        System.out.println(s);

        System.out.println(stripOutPreviewURL(s));

    }
}