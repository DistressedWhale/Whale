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
			String[] initialSeeds = {"26VFTg2z8YR0cCuwLzESi2", "44n97yHySt0Z9rqPaXgjCK"};
			SongMetadata currentSong = new SongMetadata(initialSeeds, "");
			NextSong songPicker = new NextSong();

			
			while (true){
				Double val = getChange();
				System.out.println("retrieved value of" + val.toString());
				System.out.println("relative value is" + main.computeRelative(val));

			    do {
				info = songPicker.giveMeNextSongInfo(currentSong);
			    previewURL = songPicker.stripOutPreviewURL(info);
			    seeds = songPicker.stripOutSeeds(info);

			    currentSong = new SongMetadata(seeds, previewURL);
			    } while (!currentSong.previewURL.contains("https://p.scdn.co/mp3-preview"));
			    
			    System.out.println("Preview URL: " + currentSong.previewURL + "\n" +
			    		"Artist seed: " + currentSong.artistSeed + "\n" +
			            "Song seed: " + currentSong.songSeed);

			    CancerSounds player = new CancerSounds(currentSong.previewURL);
			    player.play();
			    }
			
			
			///

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playSong(String url) {
		
		CancerSounds c = new CancerSounds(url);
		c.play();
	}
}
