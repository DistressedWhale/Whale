import java.util.Scanner;

public class NextSongRunner {
    public static void main(String[] args) throws Exception{
        //hardcoded initial data
        String[] initialSeeds = {"26VFTg2z8YR0cCuwLzESi2", "44n97yHySt0Z9rqPaXgjCK"};
        SongMetadata currentSong = new SongMetadata(initialSeeds, "");
        NextSong songPicker = new NextSong();

        //Some stuff for the loop
        String userInput = "";
        String info;
        String previewURL;
        String[] seeds;
        Scanner scan = new Scanner(System.in);

        //Main loop
        while (!(userInput.equals("quit"))) {
            info = songPicker.giveMeNextSongInfo(currentSong);
            previewURL = songPicker.stripOutPreviewURL(info);
            seeds = songPicker.stripOutSeeds(info);

            currentSong = new SongMetadata(seeds, previewURL);

            if (currentSong.previewURL.contains("https://p.scdn.co/mp3-preview")) {
                System.out.println("Preview URL: " + currentSong.previewURL + "\n" +
                        "Artist seed: " + currentSong.artistSeed + "\n" +
                        "Song seed: " + currentSong.songSeed);

                System.out.println("Another song? > ");
                userInput = scan.nextLine();
            }
        }
    }
}
