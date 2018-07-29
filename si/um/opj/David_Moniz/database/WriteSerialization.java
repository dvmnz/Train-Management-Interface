package si.um.opj.David_Moniz.database;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import si.um.opj.David_Moniz.logic.transport.FreightTrain;

public class WriteSerialization {

	public WriteSerialization(Vector<FreightTrain> ft) {
		if(ft!=null) {
				try {
			         FileOutputStream fileOut = new FileOutputStream("src/si/um/opj/David_Moniz/FreightTrain.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.reset();
			         out.writeObject(ft);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in FreightTrain.ser");
			      }
				catch (IOException i) {
			         i.printStackTrace();
			         new WriteSystemEvents("CRITICAL",i.getMessage());
			      }
		}
		else {
			System.out.println("Freight Train Management is empty");
		}
	}	
	
	public WriteSerialization() {
				try {
			         FileOutputStream fileOut = new FileOutputStream("src/si/um/opj/David_Moniz/FreightTrain.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.reset();
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in FreightTrain.ser");
			      }
				catch (IOException i) {
			         i.printStackTrace();
			         new WriteSystemEvents("CRITICAL",i.getMessage());
			   }
	}
}
