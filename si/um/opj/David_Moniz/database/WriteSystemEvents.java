package si.um.opj.David_Moniz.database;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteSystemEvents {

        private String fileName = "src/si/um/opj/David_Moniz/FreightTrainSystemEvents.txt";
        private String log;
        
        public WriteSystemEvents(String eventType,String eventMessage) {
	        try {
	            FileWriter fileWriter = new FileWriter(fileName,true);
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.newLine();
	            
	            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        	Date date = new Date();
	        	System.out.println(dateFormat.format(date));
	        	
	        	log = dateFormat.format(date)+" - " + eventType + " - " + eventMessage;
	            bufferedWriter.append(log);
	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error writing to file '"
	                + fileName + "'");
	        }
        }
    
}

