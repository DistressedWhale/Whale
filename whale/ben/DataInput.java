/**
 * 
 */
package whale.ben;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sun.misc.Queue;

/**
 * @author benwh
 *
 */
public class DataInput {
	
	Queue<String[]> dataQueue = new Queue<String[]>();

	public DataInput() {
		
		String csvFile = "BrumHack.csv";
		BufferedReader br = null;
		String line = "";
        String cvsSplitBy = ",";		
				
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] minute = line.split(cvsSplitBy);

                dataQueue.enqueue(minute);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

	}
	
	public String[] getNextMinute() {
		
		try {
			return dataQueue.dequeue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
}
