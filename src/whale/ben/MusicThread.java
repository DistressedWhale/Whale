package whale.ben;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import whale.sound.*;

public class MusicThread extends Thread {
	private BlockingQueue<Double> q;
	private Main main;
	
	public MusicThread(Main main) {
		this.q = main.getQueue();
		this.main = main;
		
	}

	public Double getChange() {
		
		return main.giveChange(); //returns a double - 1.1 means 10% increase; 0.8 means 20% decrease
	}
	
	public void run() {
        try {
			System.out.println("running musicthread");
        	String userInput = "";
			String info;
			String previewURL;
			String[] seeds;
			//String[] initialSeeds = {"26VFTg2z8YR0cCuwLzESi2", "44n97yHySt0Z9rqPaXgjCK"};
			String[] initialSeeds = {"6XyY86QOPPrYVGvF9ch6wz","60a0Rd6pjrkxjPbaKzXjfq"};
			SongMetadata currentSong = new SongMetadata(initialSeeds, "");
			NextSong songPicker = new NextSong();

			Double mode;
			while (true){
				Double val = getChange();
				Double rel = main.computeRelative(val);
				System.out.println("Decimal percent change of " + val.toString());
				//System.out.println("relative value is" + main.computeRelative(val));
				if(val < 1) mode = 0.0;
				else mode = 1.0;
			    do {
				info = songPicker.giveMeNextSongInfo(currentSong, val, rel, mode);
			    previewURL = songPicker.stripOutPreviewURL(info);
			    seeds = songPicker.stripOutSeeds(info);

			    currentSong = new SongMetadata(seeds, previewURL);
			    } while (!currentSong.previewURL.contains("https://p.scdn.co/mp3-preview"));
			    System.out.println("Song Found - beginning playback.");
			    //System.out.println("Preview URL: " + currentSong.previewURL + "\n" +
			    //		"Artist seed: " + currentSong.artistSeed + "\n" +
			    //        "Song seed: " + currentSong.songSeed);

			    SoundPlayer player = new SoundPlayer(currentSong.previewURL);
			    player.play();
			    }
			
			
			///

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playSong(String url) {
		
		SoundPlayer c = new SoundPlayer(url);
		c.play();
	}
}
