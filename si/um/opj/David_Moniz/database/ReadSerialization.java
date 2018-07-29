package si.um.opj.David_Moniz.database;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import si.um.opj.David_Moniz.logic.transport.FreightTrain;

public class ReadSerialization {
	
	private Vector<FreightTrain> ft = null;
	
	public ReadSerialization() {
	      try {
	         FileInputStream fileIn = new FileInputStream("src/si/um/opj/David_Moniz/FreightTrain.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         ft = (Vector<FreightTrain>) in.readObject();
	         in.close();
	         fileIn.close();
	      } 
	      catch (IOException i) {
	         System.out.println(i.getMessage());
	         new WriteSystemEvents("CRITICAL",i.getMessage());
	      } 
	      catch (ClassNotFoundException c) {
	         System.out.println(c.getMessage() + "Freight Train class not found");
	         new WriteSystemEvents("CRITICAL",c.getMessage() + "Freight Train class not found");
	         return;
	      }
	}

	public Vector<FreightTrain> getFt() {
		return ft;
	}	
}