/**
 * 
 */
package whale.ben;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import whale.sound.HowJSay;

/**
 * @author benwh
 *
 */
public class Main {
	
	public Double prev;
	public Double total;
	public int dataPoints;
	public BlockingQueue<Double> q;
	
	public Main() {
		prev = 0.0;
		total = 0.0;
		dataPoints = 0;
		q = new LinkedBlockingQueue<Double>();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		
		DataThread dt = new DataThread(q);
		MusicThread mt = new MusicThread(q,main);
		dt.start();
		mt.start();
		
		while (true){
		
		//update the object which MT has access to which holds the % change
			//ok so the q has been updated, I need to poll the queue, and hold the data for the average ready to pass it on and wipe it when I do so.
		try {
			System.out.println(q.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	
	}
	public synchronized Double giveChange() {
		
		if(total != 0) {
			
			return prev/(total/dataPoints); //should give a decimal percent change
			
			
		} else {
			return null;
		}
	}
}
