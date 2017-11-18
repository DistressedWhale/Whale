import java.net.*;
import java.io.*;
//ID: 1f60082d34864c39b752ace10f5ff00d
//Secret: b933f2341509494da1a6e878e0338e5e
//curl -X GET "https://accounts.spotify.com/authorize?client_id=1f60082d34864c39b752ace10f5ff00d&response_type=code&redirect_uri=http://localhost:8888/callback"
//OAUTH: BQCah9nJ7Zxvq1UpaCggT0jn5Jzf1EVGOeWzOuTsFfbdgdtsqvKIQtdr7CamCwyEuj7ZpMDGw772Exm3KVMTAlDNQwXArsej75dRTHsdd2qKW4X4Lip7TJYDCUPMnAnq-HHTeEb0rUdiSasR

public class NextSong {
  public static String httpGET(String inputURL) throws Exception{
    URL getURL = new URL(inputURL);
    URLConnection connection = getURL.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

    String inputLine;
    String outputLine = "";

    while ((inputLine = in.readLine()) != null) { //get each line
      outputLine = outputLine + inputLine + "\n";
      }

    in.close(); //closes connection
    return outputLine.substring(0,outputLine.length()-1); //snips off the extra newline at the end
    }

  public static void main(String[] args) throws Exception{
    String OAuth = "BQCah9nJ7Zxvq1UpaCggT0jn5Jzf1EVGOeWzOuTsFfbdgdtsqvKIQtdr7CamCwyEuj7ZpMDGw772Exm3KVMTAlDNQwXArsej75dRTHsdd2qKW4X4Lip7TJYDCUPMnAnq-HHTeEb0rUdiSasR";
    System.out.println(httpGET("https://accounts.spotify.com/authorize?client_id=1f60082d34864c39b752ace10f5ff00d&response_type=code&redirect_uri=http://localhost:8888/callback"));
    //System.out.println(httpGET("\"https://api.spotify.com/v1/recommendations?min_energy=0.4&market=US&seed_tracks=0c6xIDDpzE81m2q797ordA&seed_artists=4NHQUGzhtTLFvgF5SZesLK&min_popularity=50&Accept=application/json&Authorization=BQCah9nJ7Zxvq1UpaCggT0jn5Jzf1EVGOeWzOuTsFfbdgdtsqvKIQtdr7CamCwyEuj7ZpMDGw772Exm3KVMTAlDNQwXArsej75dRTHsdd2qKW4X4Lip7TJYDCUPMnAnq-HHTeEb0rUdiSasR"));
    }

  }
