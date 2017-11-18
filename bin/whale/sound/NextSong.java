import com.mashape.unirest.http.Unirest;
import java.lang.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;


public class NextSong {
    static final String OAuth = "BQCah9nJ7Zxvq1UpaCggT0jn5Jzf1EVGOeWzOuTsFfbdgdtsqvKIQtdr7CamCwyEuj7ZpMDGw772Exm3KVMTAlDNQwXArsej75dRTHsdd2qKW4X4Lip7TJYDCUPMnAnq-HHTeEb0rUdiSasR";
    static final String ID = "1f60082d34864c39b752ace10f5ff00d";
    static final String Secret = "b933f2341509494da1a6e878e0338e5e";

    public static void main(String[] args) throws Exception{


        String literalDumpster = "https://requestb.in/qznm79qz";
        String myURL = "https://accounts.spotify.com/api/token";

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

        System.out.println(result);
    }
}
