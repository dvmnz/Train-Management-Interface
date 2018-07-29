package si.um.opj.David_Moniz.logic.cargo;

import java.awt.EventQueue;
import java.util.Vector;

import si.um.opj.David_Moniz.logic.transport.FreightTrain;


public class Test {

	
	public static void main(String[] args) {
		
		Container c1 = new Container("sdfwevdf",4,43);
		Container c2 = new Container("sdwefvdf",4,4);
		Container c3 = new Container("sdsfvdf",4,43);
		Container c4 = new Container("sdwqf",43,43);
		
		Container[] containerArray = new Container[] {c1,c2,c3,c4};
		
		FreightTrain ft = new FreightTrain("David", 3232, 2340, 243);
		
		System.out.println("Starting first one thread only");
		Crane crane1 = new Crane(ft,containerArray);
		crane1.run();
		System.out.println("Finished");
		
		System.out.println("Starting second thread only");
		Crane crane2 = new Crane(ft,containerArray);
		crane2.run();
		System.out.println("Finished");
		
		System.out.println("Starting multi thread");
		Crane crane3 = new Crane(ft,containerArray);
		crane3.start();
		
		Crane crane4 = new Crane(ft,containerArray);
		crane4.start();
	}
	
}
