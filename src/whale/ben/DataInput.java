/**
 * 
 */
package whale.ben;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author benwh
 *
 */
public class DataInput {
	
	Queue<String[]> dataQueue = new LinkedList<String[]>();

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

                dataQueue.add(minute);

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
		
		return dataQueue.poll();
		
	}
	
	
	
	
	
	
}
