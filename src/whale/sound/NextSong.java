package whale.sound;

import com.mashape.unirest.http.Unirest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;


public class NextSong {
    //ID and password for Auth
    private static final String ID = "1f60082d34864c39b752ace10f5ff00d";
    private static final String Secret = "b933f2341509494da1a6e878e0338e5e";
    public static String OAuth;
    public static Float energyValue = 0.5f;
    public static Float valenceValue = 0.5f;
    public static int mode = 1;

    //bare bones constructor, it only needs to know the first song and artist to get rolling.
    public NextSong() throws Exception {
        OAuth = giveMeAuth();
    }

    //Gets the next song, based off a song.
    public String giveMeNextSongInfo(SongMetadata song, Double energyChange, Double valenceChange, Double modeChange) throws Exception {
        //Change energy value
        energyValue = (float) ((energyChange - 0.75)*2);

        if (energyValue > 1.0f) {
            energyValue = 1.0f;
        } else if (energyValue < 0f) {
            energyValue = 0f;
        }

        Float maxEnergy = energyValue + 0.1f;
        if (maxEnergy > 1.0f) {
            maxEnergy = 1.0f;
        }
        if(Double.isNaN(valenceChange)) valenceChange = 0.5;
        valenceValue = valenceChange.floatValue();
        
        Float maxValence = valenceValue + 0.1f;
        if (maxValence > 1.0f) {
            maxValence = 1.0f;
        }

        if (modeChange >= 1) {
            mode = 1;
        }
        else {
            mode = 0;
        }

        String myURL = "https://api.spotify.com/v1/recommendations?" +
                "min_energy=" + energyValue.toString() + "&" +
                "max_energy=" + maxEnergy.toString() + "&" +
                "min_valence=" + valenceValue.toString() + "&" +
                "max_valence=" + maxValence.toString() + "&" +
                "mode=" + Integer.toString(mode) + "&" +
                "market=US&" +
                "seed_tracks=" + song.songSeed + "&" +
                "seed_artists=" + song.artistSeed + "&" +
                "limit=1&";
        
        System.out.println("Energy: "+energyValue.toString()+"Valence: " +valenceValue.toString() +" mode:" +Integer.toString(mode));
        
        return Unirest.get(myURL)
                .header("Authorization", "Bearer " + OAuth)
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asString()
                .getBody();
    }

    public String giveMeNextSongInfo(SongMetadata song) throws Exception {
        String myURL = "https://api.spotify.com/v1/recommendations?" +
                "min_energy=" + energyValue.toString() + "&" +
                "market=US&" +
                "seed_tracks=" + song.songSeed + "&" +
                "seed_artists=" + song.artistSeed + "&" +
                "limit=1&" +
                "min_popularity=50";

        return Unirest.get(myURL)
                .header("Authorization", "Bearer " + OAuth)
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asString()
                .getBody();
    }

    //A function for auth, returning an OAuth string
    public String giveMeAuth() throws Exception {
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

    //Gets the song preview URL that Spotify spits out somewhere in the JSON
    public String stripOutPreviewURL(String inputURL) {
        int startIndex = inputURL.indexOf("preview_url") + 16; //get location of preview URL
        int endIndex = inputURL.indexOf("\"", startIndex+16);
        return inputURL.substring(startIndex, endIndex);
    }

    //gets the recommended artist and song URLs
    public String[] stripOutSeeds(String inputURL) {

        //Strip out recommended artist
        int firstHTTPIndex = inputURL.indexOf("\"spotify\" : \"https://open.spotify.com/artist");
        int firstSeedStart = inputURL.indexOf("\"", firstHTTPIndex+10) + 1;
        int firstSeedEnd = inputURL.indexOf("\"", firstSeedStart+1);

        //Strip out recommended track
        int secondHTTPIndex = inputURL.indexOf("\"spotify\" : \"https://open.spotify.com/track");
        int secondSeedStart = inputURL.indexOf("\"", secondHTTPIndex+10) + 1;
        int secondSeedEnd = inputURL.indexOf("\"", secondSeedStart+1);

        //Gets substring. +32/31 strips out the URL part and just gives the ID
        String firstSeed = inputURL.substring(firstSeedStart+32, firstSeedEnd);
        String secondSeed = inputURL.substring(secondSeedStart+31, secondSeedEnd);

        String[] wrapup = {firstSeed, secondSeed};
        return wrapup;
    }
}