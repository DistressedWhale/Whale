package whale.ben;

import java.util.concurrent.BlockingQueue;

public class DataThread extends Thread {
	private DataInput data;
	private boolean hasNewData = false;
	private String[] line;
	private BlockingQueue<Double> q;
	
	public DataThread(Main m) {
		
		
		data = new DataInput();
		this.q = m.getQueue();
	}

	public void run() {
		
		while(true) {
		line = data.getNextMinute();
		try {
			q.put(Double.parseDouble(line[4])-2700.0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	/**
	 * Gives the % change +/- since last polled
	 */
	public double getChange() {
		
		return 0.0;
	}
	
}
