package whale.ben;

import java.util.concurrent.BlockingQueue;

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
		
		while (true){
			Double val = getChange();
		System.out.println("retrieved value of" + val.toString());
		System.out.println("relative value is" + main.computeRelative(val));
		try {
			sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			break;
		}
		}
	}

}
