package si.um.opj.David_Moniz.logic.cargo;

import si.um.opj.David_Moniz.logic.transport.FreightTrain;

public class Crane extends Thread {
	
	private FreightTrain train;
	private Container[] fieldOfContainers;
	
	public Crane(FreightTrain train, Container[] fieldOfContainers) {
		this.train = train;
		this.fieldOfContainers = fieldOfContainers;
	}

	@Override
	public void run() {
		prepareCraneForWork();
		System.out.println("Freight train has " + fieldOfContainers + " containers.");
	}
	
	private void prepareCraneForWork() {
		int numCont = fieldOfContainers.length;
		
		for(int i = 0;i<numCont;i++) {
			
			synchronized(this) {
					try {
						train.loadContainer(fieldOfContainers[i]);
						System.out.println("Loading " + fieldOfContainers[i]);
					} catch (FreightTrainFullException | MaximumWeightExceededException e1) {
						e1.printStackTrace();
					}
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			
		}
	}
	
	private int startTransfer(int n) {
		return (int) Math.random() * n;
	}

}
