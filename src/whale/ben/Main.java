/**
 * 
 */
package whale.ben;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import whale.sound.*;
/**
 * @author benwh
 *
 */
public class Main {
	
	public Double prev;
	public Double total;
	public int dataPoints;
	public BlockingQueue<Double> q;
	public Queue<Double> rolling;
	public Double ans;
	
	public Main() {
		prev = 43.0;
		total = 43.0;
		dataPoints = 1;
		this.q = new LinkedBlockingQueue<Double>();
		this.rolling = new LinkedList<Double>();
		
	}
	public BlockingQueue<Double> getQueue() {
		
		return q;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		
		DataThread dt = new DataThread(main);
		MusicThread mt = new MusicThread(main);
		dt.start();
		mt.start();
		
		while (true){
		
		//update the object which MT has access to which holds the % change
			//ok so the q has been updated, I need to poll the queue, and hold the data for the average ready to pass it on and wipe it when I do so.
		try {
			Double d = main.q.take();
			if(main.prev == 0.0) {
				main.prev = d;
			}
			
			System.out.println("Stock Value: "+d.toString());
			main.total += d;
			main.dataPoints ++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	
	}
	public synchronized Double giveChange() {
		
		if(total != 0) {
			
			 ans = (total/dataPoints)/prev; //should give a decimal percent change
			 if(Double.isNaN(ans)) return 1.0;
			 else return ans;
			
		} else {
			return 1.0;
		}
		
		
	}
	public synchronized Double computeRelative(Double current) {
		
		if(rolling.size() >= 20) {
			rolling.remove();
		}
		rolling.add(total/dataPoints);
		Double min, max;
		min = rolling.peek();
		max = 0.0;
		for(Double val: rolling) {
			if(val < min) min = val;
			if(val > max) max = val;	
		}
		Double range = max-min;
		Double pos = (total/dataPoints)-min;
		
		prev = total/dataPoints;
		total = 0.0;
		dataPoints = 0;
		
		return pos/range;
	}
}
