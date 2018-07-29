package si.um.opj.David_Moniz.logic;

import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import si.um.opj.David_Moniz.logic.cargo.Container;
import si.um.opj.David_Moniz.logic.cargo.FreightTrainFullException;
import si.um.opj.David_Moniz.logic.cargo.MaximumWeightExceededException;
import si.um.opj.David_Moniz.logic.transport.FreightTrain;
import si.um.opj.David_Moniz.logic.transport.PassengerTrain;
import si.um.opj.David_Moniz.logic.transport.PassengerTrainType;
import si.um.opj.David_Moniz.logic.transport.Train;

public class Launcher {
	public ArrayList<Integer> ft = new ArrayList(5);
	
	public static void main(String[] args) {
		Launcher l = new Launcher();
		l.ft.add(3);
		l.ft.add(3);
		l.ft.add(3);
		l.ft.add(3);
		l.ft.add(3);
		l.ft.add(3);
		/* 
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Online Train Staion.\nEnter Country destination:");  
		String countryD = sc.next();  
		System.out.println("Enter City destination:");  
		String cityD = sc.next(); 
		City city1 = new City(cityD,countryD);
		
		System.out.println("Enter Country origin:");  
		String countryO = sc.next();  
		System.out.println("Enter City origin:");  
		String cityO = sc.next(); 
		City city2 = new City(cityO,countryO);
		
		Route route1 = new Route(city2,city1);
		
		System.out.println("Enter Route Distance:");  
		float routeDistance = sc.nextFloat();
		route1.setRouteDistance(routeDistance);
		
		System.out.println("Enter Day:");  
		int day = sc.nextInt();
		System.out.println("Enter Month:");  
		int month = sc.nextInt();
		System.out.println("Enter Year:");  
		int year = sc.nextInt();
		LocalDate ld = LocalDate.of(year,month,day);
		route1.setStartDate(ld);
		
		System.out.println("Enter Train name:");  
		String trainName = sc.next(); 
		Train train1 = new Train(trainName);
		
		System.out.println("Enter train average speed:");  
		sc.nextLine();
		float trainSpeed = sc.nextFloat();
		train1.setAverageSpeed(trainSpeed);
		route1.setTrain(train1);
		
		route1.calculateDateOfTheArrival();
		System.out.println(route1);
		*/  
		
		/*
		String countryD = "Slovenia";
		String cityD = "Maribor"; 
		City city1 = new City(cityD,countryD);
		  
		String countryO = "Croatia"; 
		String cityO = "Zagreb";
		City city2 = new City(cityO,countryO);
		
		Route route1 = new Route(city2,city1);
		  
		float routeDistance = 5000;
		route1.setRouteDistance(routeDistance);
		
		int day = 4;
		int month = 4;
		int year = 2018;
		LocalDate ld = LocalDate.of(year,month,day);
		route1.setStartDate(ld);
		
		String trainName = "trainer";
		Train train1 = new Train(trainName);
		  
		float trainSpeed = 80;
		train1.setAverageSpeed(trainSpeed);
		route1.setTrain(train1);
		
		route1.calculateDateOfTheArrival();
		
		//System.out.println(route1);
		
		Container c = new Container("Box",4);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		c.addItem(city1);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		System.out.println(c.returnTheNumberOfItems());
		c.addItem(city2);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		System.out.println(c.returnTheNumberOfItems());
		c.addItem(city1);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		System.out.println(c.returnTheNumberOfItems());
		c.addItem(city2);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		System.out.println(c.returnTheNumberOfItems());
		c.addItem(city2);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		
		
		System.out.println("---------Removing");
		c.removeItem(train1);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		c.removeItem(city1);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		System.out.println(c.returnTheNumberOfItems());
		c.removeItem(city2);
		System.out.println(Arrays.toString(c.getArrayOfItems()));
		
		System.out.println(c.itemExists(train1));
		System.out.println(c.itemExists(city1));
		
		*/
		
		/*
		PassengerTrain t1 = new PassengerTrain("Trainer1",120,40,20,29,10,PassengerTrainType.LOCAL);
		System.out.println(t1.toString());	
		System.out.println(t1.getOcupancy());
			
		FreightTrain t2 = new FreightTrain("Trainer2",120,300,4);
		
		Ball b = new Ball();
		City city1 = new City();
		
		Container c = new Container("Box",100,3);
		c.addItem(b);
		//c.addItem(city1);
		
		try {
			t2.loadContainer(c);
			t2.loadContainer(c);
			t2.loadContainer(c);
		} catch (FreightTrainFullException e) {
			e.printStackTrace();
		} catch (MaximumWeightExceededException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(t2.toString());
		
		
		Route r = new Route();
		//r.setRouteDistance(-2);
		
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TrainFrame f = new TrainFrame("hello");
				f.setSize(500, 400);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);
			}
		});
		
		
		
		*/
		
	}

}
