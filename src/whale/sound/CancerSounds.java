package whale.sound;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class CancerSounds{
	
	
	public static void main(String[] args)
	{
	try {
		String mp3Url = "https://p.scdn.co/mp3-preview/4e6c03963fa6a6720648540282afc75f4df744c9";
		URL url = new URL(mp3Url);
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		
		Player player = new Player(is);
		
		player.play();
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JavaLayerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	
}