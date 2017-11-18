package whale.ben;

import java.util.concurrent.LinkedBlockingQueue;

public class MusicThread extends Thread {
	private LinkedBlockingQueue<Double> q;
	private Main main;
	
	public MusicThread(LinkedBlockingQueue<Double> q, Main main) {
		this.q = q;
		this.main = main;
		
	}

	public Double getChange() {
		
		return main.giveChange(); //returns a double - 1.1 means 10% increase; 0.8 means 20% decrease
	}
	
	public void run() {
		
		System.out.println("retrieved value of" + getChange().toString());
		try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
