package si.um.opj.David_Moniz.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JList;
import java.awt.TextField;
import java.awt.Panel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import si.um.opj.David_Moniz.database.ReadSerialization;
import si.um.opj.David_Moniz.database.WriteSerialization;
import si.um.opj.David_Moniz.database.WriteSystemEvents;
import si.um.opj.David_Moniz.logic.cargo.Container;
import si.um.opj.David_Moniz.logic.cargo.FreightTrainFullException;
import si.um.opj.David_Moniz.logic.cargo.Item;
import si.um.opj.David_Moniz.logic.cargo.MaximumWeightExceededException;
import si.um.opj.David_Moniz.logic.cargo.Portable;
import si.um.opj.David_Moniz.logic.cargo.ContainerFullException;
import si.um.opj.David_Moniz.logic.transport.FreightTrain;
import si.um.opj.David_Moniz.logic.transport.Train;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.SystemColor;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField ftName;
	private JTextField ftAvergaeSpeed;
	private JTextField ftMaxWeight;
	private JTextField ftMaxContainers;

	private JTable ft3Containers;
	private JTable ftTable1;
	private JTable ft3ItemsCont;
	private JTextField itemName;
	private JTextField itemWeight;
	private JTable ft3;
	private JTable ft2Containers;
	private JTextField contLabel;
	private JTextField contNumItems;
	private JTextField contMaxWeight;
	private JTable ft2;
	private JLabel messageLabel;
	private String status;
	private String message;
	
	JPanel section;
	JPanel freightTrainManagement;
	JPanel addingItemsToContainers;
	JPanel loadUnload;
		
	private Vector<FreightTrain> ft = new Vector<>();
	private final Object[] freightTrainColums =  {"Name","Speed","Weight","Containers"};
	private DefaultTableModel modelFreightTrainColums = newDefaultTableModel();
	
	private final Object[] containerColums =  {"Label","Weight","Items"};
	private DefaultTableModel modelContainerColums = newDefaultTableModel();
	private DefaultTableModel modelContainerColums2 = newDefaultTableModel();
	
	private final Object[] itemColums =  {"Name","Weight"};
	private DefaultTableModel modelItemColums = newDefaultTableModel();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					new WriteSystemEvents("CRITICAL",e.getMessage());
				}
			}
		});
	}

	
	public DefaultTableModel newDefaultTableModel() {//Makes all cells of the table non editable
		return new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
	}
	
	/**
	 * Create the frame.
	 */
	public Home() {
		modelFreightTrainColums.setColumnIdentifiers(freightTrainColums);
		modelContainerColums.setColumnIdentifiers(containerColums);
		modelContainerColums2.setColumnIdentifiers(containerColums);
		modelItemColums.setColumnIdentifiers(itemColums);
		
		
		
		ReadSerialization rs = new ReadSerialization();
		if(rs.getFt()!=null) {
			ft=rs.getFt();
		}
		
		for(int i = 0;i<ft.size();i++) {
			Object[] freightTrainRow =  {
					(String)ft.get(i).getname(),
					ft.get(i).getAverageSpeed(),
					ft.get(i).getMaxWeightOfCargo(),
					ft.get(i).getMaxWeightOfCargo()};
			modelFreightTrainColums.addRow(freightTrainRow);
		}

				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 798, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnFreight = new JButton("Freight Train Management");
		btnFreight.setBounds(0, 0, 246, 46);
		panel.add(btnFreight);
		
		JButton btnItems = new JButton("Adding Items to Containers");
		btnItems.setBounds(245, 0, 258, 46);
		panel.add(btnItems);
		
		JButton btnLoad = new JButton("Load / Unload Containers");
		btnLoad.setBounds(502, 0, 296, 46);
		panel.add(btnLoad);
		
		JPanel eventPanel = new JPanel();
		eventPanel.setBackground(SystemColor.control);
		eventPanel.setBounds(0, 379, 798, 33);
		contentPane.add(eventPanel);
		eventPanel.setLayout(null);
		
		messageLabel = new JLabel("Important messages will be displayed here!");
		messageLabel.setForeground(new Color(255, 0, 0));
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		messageLabel.setBounds(26, 11, 762, 14);
		eventPanel.add(messageLabel);
		
		section = new JPanel();
		section.setBounds(0, 46, 798, 333);
		contentPane.add(section);
		section.setLayout(new CardLayout(0, 0));
		
		freightTrainManagement = new JPanel();
		freightTrainManagement.setForeground(new Color(255, 0, 0));
		section.add(freightTrainManagement, "name_191604597577594");
		freightTrainManagement.setLayout(null);
		
		JLabel lbl1 = new JLabel("Name");
		lbl1.setBounds(521, 40, 245, 14);
		freightTrainManagement.add(lbl1);
		
		ftName = new JTextField();
		ftName.setBounds(521, 61, 245, 20);
		freightTrainManagement.add(ftName);
		ftName.setColumns(10);
		
		JLabel lbl2 = new JLabel("Average Speed(km):");
		lbl2.setBounds(521, 92, 245, 14);
		freightTrainManagement.add(lbl2);
		
		ftAvergaeSpeed = new JTextField();
		ftAvergaeSpeed.setColumns(10);
		ftAvergaeSpeed.setBounds(521, 113, 245, 20);
		freightTrainManagement.add(ftAvergaeSpeed);
		
		JLabel lbl3 = new JLabel("Maximum weight of Cargo(kg):");
		lbl3.setBounds(521, 144, 245, 14);
		freightTrainManagement.add(lbl3);
		
		ftMaxWeight = new JTextField();
		ftMaxWeight.setColumns(10);
		ftMaxWeight.setBounds(521, 165, 245, 20);
		freightTrainManagement.add(ftMaxWeight);
		
		JLabel lbl4 = new JLabel("Maximum number of Containers(units):");
		lbl4.setBounds(521, 196, 245, 14);
		freightTrainManagement.add(lbl4);
		
		ftMaxContainers = new JTextField();
		ftMaxContainers.setColumns(10);
		ftMaxContainers.setBounds(521, 217, 245, 20);
		freightTrainManagement.add(ftMaxContainers);
		
		JButton btn1 = new JButton("Create");
		btn1.addActionListener(new CreateFreightTrain());
		btn1.setBounds(521, 263, 80, 23);
		freightTrainManagement.add(btn1);
		
		JButton btn2 = new JButton("Edit");
		btn2.addActionListener(new EditFreightTrain());
		btn2.setBounds(611, 263, 71, 23);
		freightTrainManagement.add(btn2);
		
		JButton btn3 = new JButton("Remove");
		btn3.addActionListener(new DeleteFreightTrain());
		btn3.setBounds(681, 263, 85, 23);
		freightTrainManagement.add(btn3);
		
		JLabel label = new JLabel("My Freight Trains");
		label.setBounds(211, 40, 126, 14);
		freightTrainManagement.add(label);
		
		
		ftTable1 = new JTable();
		ftTable1.setModel(modelFreightTrainColums);
		ftTable1.setBounds(0, 0, 296, 176);
		ftTable1.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			       int row = (int) ftTable1.getSelectedRow();
			       if(row >= 0) {
			    	   	ftName.setText( String.valueOf(ftTable1.getValueAt(row, 0)));
			   			ftAvergaeSpeed.setText(String.valueOf(ftTable1.getValueAt(row, 1)));
			    	   	ftMaxWeight.setText(String.valueOf(ftTable1.getValueAt(row, 2)));
			    	   	ftMaxContainers.setText(String.valueOf(ftTable1.getValueAt(row, 3)));
			       }			     	        
		    }
		});
		JScrollPane scrollPaneft1 = new JScrollPane(ftTable1);
		scrollPaneft1.setBounds(34, 63, 452, 174);
		freightTrainManagement.add(scrollPaneft1);
		
		JPanel addingItemsToContainers_1 = new JPanel();
		section.add(addingItemsToContainers_1, "name_49616808479363");
		addingItemsToContainers_1.setLayout(null);
		
		ft3Containers = new JTable();
		ft3Containers.setBounds(0, 0, 166, 189);
		ft3Containers.setModel(modelContainerColums2);
		ft3Containers.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			     
		    	
		    	int row = (int) ft3.getSelectedRow();
				if(row >= 0) {
					int row2 = ft3Containers.getSelectedRow();
					if(row2 >= 0) {
							modelItemColums.setRowCount(0);
							System.out.println(ft.get(row).getContainer(row2));
							Vector<Item> p = ft.get(row).getContainer(row2).getArrayOfItems();
				    	  
				    	   for(int i=0;i<p.size();i++) {
					    		   Object[] itemsRow =  {p.get(i).returnNameOfTheItem(),p.get(i).returnWeightOfTheItem()};
					    		   modelItemColums.addRow(itemsRow);  		 
				    	   }	
					}
					else {
						System.out.println("No container row selected");
					}
				}
				else {
					System.out.println("No freight train selected");
				}	   
				    		   
		    }
		});
		JScrollPane scrollPaneft3Cont = new JScrollPane(ft3Containers);
		scrollPaneft3Cont.setBounds(249, 65, 166, 207);
		addingItemsToContainers_1.add(scrollPaneft3Cont);
		
		ft3ItemsCont = new JTable();
		ft3ItemsCont.setBounds(0, 0, 142, 189);
		ft3ItemsCont.setModel(modelItemColums);
		ft3ItemsCont.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int row = (int) ft3.getSelectedRow();
				if(row >= 0) {
					int row2 = ft3Containers.getSelectedRow();
					if(row2 >= 0) {
						int row3 = ft3ItemsCont.getSelectedRow();
						if(row3 >= 0) {
						itemName.setText(String.valueOf(ft3ItemsCont.getValueAt(row3, 0)));
			   			itemWeight.setText(String.valueOf(ft3ItemsCont.getValueAt(row3, 1)));	
						}
						else {
							System.out.println("No item row selected");
						}
					}
					else {
						System.out.println("No container row selected");
					}
				}
				else {
					System.out.println("No freight train selected");
				}
						     	        
		    }
		});
		JScrollPane scrollPaneft3ItemsCont = new JScrollPane(ft3ItemsCont);
		scrollPaneft3ItemsCont.setBounds(425, 65, 133, 207);
		addingItemsToContainers_1.add(scrollPaneft3ItemsCont);
		
		
		JLabel lblMyContainers = new JLabel("Freight Train Containers");
		lblMyContainers.setBounds(263, 29, 152, 14);
		addingItemsToContainers_1.add(lblMyContainers);
		
		JLabel lblItemsOfThe = new JLabel("Items of the Container");
		lblItemsOfThe.setBounds(437, 29, 142, 14);
		addingItemsToContainers_1.add(lblItemsOfThe);
		
		JLabel lblLabel = new JLabel("Name");
		lblLabel.setBounds(579, 100, 245, 14);
		addingItemsToContainers_1.add(lblLabel);
		
		itemName = new JTextField();
		itemName.setColumns(10);
		itemName.setBounds(579, 122, 198, 20);
		addingItemsToContainers_1.add(itemName);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new AddItemToContainer());
		btnAdd.setBounds(579, 220, 198, 23);
		addingItemsToContainers_1.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new DeleteItemFromContainer());
		btnDelete.setBounds(688, 249, 89, 23);
		addingItemsToContainers_1.add(btnDelete);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new EditItemFromContainer());
		btnEdit.setBounds(579, 249, 80, 23);
		addingItemsToContainers_1.add(btnEdit);
		
		JLabel lblMaximumWeightkg = new JLabel("Weight of the Item(kg):");
		lblMaximumWeightkg.setBounds(579, 155, 245, 14);
		addingItemsToContainers_1.add(lblMaximumWeightkg);
		
		itemWeight = new JTextField();
		itemWeight.setColumns(10);
		itemWeight.setBounds(579, 180, 198, 20);
		addingItemsToContainers_1.add(itemWeight);
		
		ft3 = new JTable();
		ft3.setBounds(0, 0, 174, 189);
		ft3.setModel(modelFreightTrainColums);
		ft3.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			       int row = (int) ft3.getSelectedRow();
			       if(row >= 0) {
			    	   modelContainerColums2.setRowCount(0);
			    	   modelItemColums.setRowCount(0);
			    	   Vector<Container> c = ft.get(row).getArrayOfContainers();
			    	  
			    	   for(int i=0;i<c.size();i++) {
				    		   Object[] ContainerRow =  {c.get(i).getLabel(),c.get(i).getMaxWeight(),c.get(i).getMaxNumberOfItems()};
				    		   modelContainerColums2.addRow(ContainerRow);  		 
			    	   }
			       }			     	        
		    }
		});
		JScrollPane scrollPaneft3 = new JScrollPane(ft3);
		scrollPaneft3.setBounds(27, 64, 212, 208);
		addingItemsToContainers_1.add(scrollPaneft3);
		
		JLabel lblMyFreightTrains = new JLabel("My Freight Trains");
		lblMyFreightTrains.setBounds(78, 29, 107, 14);
		addingItemsToContainers_1.add(lblMyFreightTrains);
		
		JPanel loadUnload_1 = new JPanel();
		loadUnload_1.setLayout(null);
		section.add(loadUnload_1, "name_60389486260232");
		
		ft2Containers = new JTable();
		ft2Containers.setBounds(0, 0, 212, 189);
		ft2Containers.setModel(modelContainerColums);
		ft2Containers.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int row = (int) ft2.getSelectedRow();
				if(row >= 0) {
					int row2 = ft2Containers.getSelectedRow();
					if(row2 >= 0) {
						contLabel.setText(String.valueOf(ft2Containers.getValueAt(row2, 0)));
			   			contMaxWeight.setText(String.valueOf(ft2Containers.getValueAt(row2, 1)));
			    	   	contNumItems.setText(String.valueOf(ft2Containers.getValueAt(row2, 2)));			    	   	
					}
					else {
						System.out.println("No container row selected");
					}
				}
				else {
					System.out.println("No freight train selected");
				}
						     	        
		    }
		});
		JScrollPane scrollPaneft2Cont = new JScrollPane(ft2Containers);
		scrollPaneft2Cont.setBounds(294, 65, 211, 232);
		loadUnload_1.add(scrollPaneft2Cont);
		
		JLabel lblContainersOfThe = new JLabel("Containers of the Freight Train");
		lblContainersOfThe.setBounds(324, 29, 195, 14);
		loadUnload_1.add(lblContainersOfThe);
		
		JLabel lblContainerLabel = new JLabel("Container Label");
		lblContainerLabel.setBounds(532, 65, 245, 14);
		loadUnload_1.add(lblContainerLabel);
		
		contLabel = new JTextField();
		contLabel.setColumns(10);
		contLabel.setBounds(532, 88, 243, 20);
		loadUnload_1.add(contLabel);
		
		JLabel label_4 = new JLabel("Maximum weight(kg):");
		label_4.setBounds(532, 119, 245, 14);
		loadUnload_1.add(label_4);
		
		contMaxWeight = new JTextField();
		contMaxWeight.setColumns(10);
		contMaxWeight.setBounds(533, 144, 242, 20);
		loadUnload_1.add(contMaxWeight);
		
		JLabel label_3 = new JLabel("Maximum number of Items(units):");
		label_3.setBounds(532, 175, 245, 14);
		loadUnload_1.add(label_3);
		
		contNumItems = new JTextField();
		contNumItems.setColumns(10);
		contNumItems.setBounds(532, 198, 242, 20);
		loadUnload_1.add(contNumItems);
		
		JButton btnContAdd = new JButton("Add Container");
		btnContAdd.addActionListener(new AddContainerToFreightTrain());
		btnContAdd.setBounds(532, 229, 242, 23);
		loadUnload_1.add(btnContAdd);
		
		JButton button_1 = new JButton("Delete");
		button_1.addActionListener(new DeleteContainerFromFreightTrain());
		button_1.setBounds(660, 263, 114, 23);
		loadUnload_1.add(button_1);
		
		JButton button_2 = new JButton("Edit");
		button_2.addActionListener(new EditContainerFromFreightTrain());
		button_2.setBounds(532, 263, 80, 23);
		loadUnload_1.add(button_2);
		
		
		
		ft2 = new JTable();
		ft2.setBounds(0, 0, 212, 189);
		ft2.setModel(modelFreightTrainColums);
		ft2.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			       int row = (int) ft2.getSelectedRow();
			       if(row >= 0) {
			    	   modelContainerColums.setRowCount(0);
			    	   Vector<Container> c = ft.get(row).getArrayOfContainers();
			    	  
			    	   for(int i=0;i<c.size();i++) {
				    		   Object[] ContainerRow =  {c.get(i).getLabel(),c.get(i).getMaxWeight(),c.get(i).getMaxNumberOfItems()};
				    		   modelContainerColums.addRow(ContainerRow);  		 
			    	   }
			       }			     	        
		    }
		}
		);
		JScrollPane scrollPaneft2 = new JScrollPane(ft2);
		scrollPaneft2.setBounds(26, 65, 258, 232);
		loadUnload_1.add(scrollPaneft2);
		
		
		JLabel label_5 = new JLabel("My Freight Trains");
		label_5.setBounds(108, 29, 107, 14);
		loadUnload_1.add(label_5);
		btnFreight.addActionListener(new ChangeLayout(freightTrainManagement));
		
		JPanel filePanel = new JPanel();
		filePanel.setBorder(new LineBorder(Color.GRAY));
		filePanel.setBounds(34, 248, 225, 62);
		freightTrainManagement.add(filePanel);
		filePanel.setLayout(null);
		
		JButton btnSaveAll = new JButton("SAVE");
		btnSaveAll.setBounds(10, 28, 93, 23);
		filePanel.add(btnSaveAll);
		
		JButton btnDelete_1 = new JButton("DELETE");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedOption = JOptionPane.showConfirmDialog(null, "\nThis will delete all the binary file. Are you sure?", "Choose", JOptionPane.YES_NO_OPTION);
		        if (selectedOption == JOptionPane.YES_OPTION) {
		        	new WriteSerialization();//Deletes the binary file
		        }
		        if (selectedOption == JOptionPane.NO_OPTION) {
		        }
			}
		});
		btnDelete_1.setBounds(122, 28, 93, 23);
		filePanel.add(btnDelete_1);
		
		JLabel lblFile = new JLabel("File Storage");
		lblFile.setBounds(89, 11, 126, 14);
		filePanel.add(lblFile);
		btnSaveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedOption = JOptionPane.showConfirmDialog(null, "\nThis will rewrite the binary code. Are you sure?", "Choose", JOptionPane.YES_NO_OPTION);
		        if (selectedOption == JOptionPane.YES_OPTION) {
		        	new WriteSerialization(ft);
		        }
		        if (selectedOption == JOptionPane.NO_OPTION) {
		        }
			}
		});
		btnItems.addActionListener(new ChangeLayout(addingItemsToContainers_1));
		btnLoad.addActionListener(new ChangeLayout(loadUnload_1));
	}
	
	private class CreateFreightTrain implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(!ftName.getText().isEmpty() && !ftAvergaeSpeed.getText().isEmpty() && !ftMaxWeight.getText().isEmpty() && !ftMaxContainers.getText().isEmpty()) {
					try{
							String name = ftName.getText();
							int avSp = Integer.parseInt(ftAvergaeSpeed.getText());
							int maxW = Integer.parseInt(ftMaxWeight.getText());
							int maxC = Integer.parseInt(ftMaxContainers.getText());
							ft.add(new FreightTrain(name,avSp,maxW,maxC));
							Object[] freightTrainRow =  {name,avSp,maxW,maxC};
							modelFreightTrainColums.addRow(freightTrainRow);
							
						} catch (NumberFormatException e1) {
						 System.out.println("Enter digits only on the digit inputs!");
						 status = "REMARK";
						 message = e1.getMessage()+" - Enter digits only on the digit inputs!";
						 messageLabel.setText(status + "!! " + message);
						 new WriteSystemEvents(status,message);
						}
				}
				else{
					System.out.println("Some fields are empty!");
				}
			}
	}
	
	private class EditFreightTrain implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				int row = ftTable1.getSelectedRow();
				if(row>=0) {
					System.out.println(ft.get(row));
						if(!ftName.getText().isEmpty() && !ftAvergaeSpeed.getText().isEmpty() && !ftMaxWeight.getText().isEmpty() && !ftMaxContainers.getText().isEmpty()) {
							try{
									String name = ftName.getText();
									int avSp = (int)Integer.parseInt(ftAvergaeSpeed.getText());
									int maxW = (int)Integer.parseInt(ftMaxWeight.getText());
									int maxC = (int)Integer.parseInt(ftMaxContainers.getText());
									
									ft.get(row).setname(name);
									ft.get(row).setAverageSpeed(avSp);
									ft.get(row).setMaxWeightOfCargo(maxW);
									ft.get(row).setMaxNumberOfContainers(maxC);
									
									modelFreightTrainColums.setValueAt(name, row, 0);
									modelFreightTrainColums.setValueAt(ft.get(row).getAverageSpeed(), row, 1);
									modelFreightTrainColums.setValueAt(ft.get(row).getMaxWeightOfCargo(), row, 2);
									modelFreightTrainColums.setValueAt(ft.get(row).getMaxNumberOfContainers(), row, 3);
							} catch (NumberFormatException e1) {
								 	System.out.println("Enter digits only on the digit inputs!");
								 	status = "REMARK";
									 message = e1.getMessage()+" - Enter digits only on the digit inputs!";
									 messageLabel.setText(status + "!! " + message);
									 new WriteSystemEvents(status,message);
							}
						}
						else{
							System.out.println("Some fields are empty!");
						}
				}
				else {
					System.out.println("No rows created or selected!");
				}
			}
		}
	
	
	private class DeleteFreightTrain implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int row = ftTable1.getSelectedRow();
			if(row>=0) {
				ft.remove(row);
				modelFreightTrainColums.removeRow(row);
				
			}
			else {
				System.out.println("No rows created!");
			}
		}
	}
	
	
	private class AddItemToContainer implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int row = (int) ft3.getSelectedRow();
			if(row >= 0) {
				int row2 = ft3Containers.getSelectedRow();
				if(row2 >= 0) {
					if(!itemName.getText().isEmpty() && !itemWeight.getText().isEmpty()) {
						try{
								String name = itemName.getText();
								int wgt = Integer.parseInt(itemWeight.getText());
								ft.get(row).getContainer(row2).addItem(new Item(name,wgt));
								Object[] itemRow =  {name,wgt};
								modelItemColums.addRow(itemRow);
								
							} catch (NumberFormatException e1) {
								System.out.println("Enter digits only on the digit inputs!");
								status = "REMARK";
								 message = e1.getMessage()+" - Enter digits only on the digit inputs!";
								 messageLabel.setText(status + "!! " + message);
								 new WriteSystemEvents(status,message);
							} catch (MaximumWeightExceededException e) {
								System.out.println(e.getMessage());
								status = "WARNING";
								message = e.getMessage();
								messageLabel.setText(status + "!! " + message);
								new WriteSystemEvents(status,message);
							} catch (ContainerFullException e) {
								System.out.println(e.getMessage());
								status = "WARNING";
								message = e.getMessage();
								messageLabel.setText(status + "!! " + message);
								new WriteSystemEvents(status,message);
							}
					}
					else{
						System.out.println("Some fields are empty!");
					}			    	   	
				}
				else {
					System.out.println("No container row selected");
				}
			}
			else {
				System.out.println("No freight train selected");
			}
			
		}
	}
	
	
	
	
	
	
private class DeleteItemFromContainer implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {	
	int row = (int) ft3.getSelectedRow();
	if(row >= 0) {
		int row2 = ft3Containers.getSelectedRow();
		if(row2 >= 0) {
			int row3 = ft3ItemsCont.getSelectedRow();
			if(row3 >= 0) {
						ft.get(row).getContainer(row2).getArrayOfItems().remove(row3);
						modelItemColums.removeRow(row3);
			}
			else {
				System.out.println("No item row selected");
			}
		}
		else {
			System.out.println("No container row selected");
		}
	}
	else {
		System.out.println("No freight train selected");
	}}
}
	
private class EditItemFromContainer implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {		
			int row = (int) ft3.getSelectedRow();//The selected Freight Train
			if(row >= 0) {
				int row2 = ft3Containers.getSelectedRow();//The selected Container of the freight train
				if(row2 >= 0) {
					int row3 = ft3ItemsCont.getSelectedRow();//The selected item of the container of the freight train
					if(row3 >= 0) {
							try {
								String name = itemName.getText();
								int wtg = Integer.parseInt(itemWeight.getText());
								
								ft.get(row).getContainer(row2).editItem(row3, new Item(name,wtg));
								modelItemColums.setValueAt(name, row3, 0);//Doesn't mean it will accept this value as a new value
								int realItemWeight = ft.get(row).getContainer(row2).getArrayOfItems().get(row3).returnWeightOfTheItem();
								modelItemColums.setValueAt(realItemWeight, row3, 1);
							} 
							catch (NumberFormatException e1) {
							 	System.out.println("Enter digits only on the digit inputs!");
							 	status = "REMARK";
								 message = e1.getMessage()+" - Enter digits only on the digit inputs!";
								 messageLabel.setText(status + "!! " + message);
								 new WriteSystemEvents(status,message);
							}
							catch (MaximumWeightExceededException e1) {
								System.out.println(e1.getMessage());
								status = "WARNING";
								message = e1.getMessage();
								messageLabel.setText(status + "!! " + message);
								new WriteSystemEvents(status,message);
							} 
							catch (ContainerFullException e1) {
								System.out.println(e1.getMessage());
								status = "WARNING";
								message = e1.getMessage();
								messageLabel.setText(status + "!! " + message);
								new WriteSystemEvents(status,message);
							}	
					}
					else {
						System.out.println("No item row selected");
					}
				}
				else {
					System.out.println("No container row selected");
				}
			}
			else {
				System.out.println("No freight train selected");
			}}
	}



private class AddContainerToFreightTrain implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int row = (int) ft2.getSelectedRow();
		       if(row >= 0) {
					if(!contLabel.getText().isEmpty() && !contMaxWeight.getText().isEmpty() && !contNumItems.getText().isEmpty()) {
						try{
								String name = contLabel.getText();
								int mxWeight = Integer.parseInt(contMaxWeight.getText());
								int nItems = Integer.parseInt(contNumItems.getText());
								ft.get(row).loadContainer(new Container(name,mxWeight,nItems));
								Object[] ContainerRow =  {name,mxWeight,nItems};
								modelContainerColums.addRow(ContainerRow);
							} 
						catch (NumberFormatException e1) {
									System.out.println("Enter digits only on the digit inputs!");
									status = "REMARK";
									 message = e1.getMessage()+" - Enter digits only on the digit inputs!";
									 messageLabel.setText(status + "!! " + message);
									 new WriteSystemEvents(status,message);
						} 
						catch (FreightTrainFullException e1) {
								System.out.println(e1.getMessage());
								status = "WARNING";
								message = e1.getMessage();
								messageLabel.setText(status + "!! " + message);
								new WriteSystemEvents(status,message);
							} 
						catch (MaximumWeightExceededException e1) {
								System.out.println(e1.getMessage());
								status = "WARNING";
								message = e1.getMessage();
								messageLabel.setText(status + "!! " + message);
								new WriteSystemEvents(status,message);
							}
						}
						else{
							System.out.println("Some fields are empty!");
						}
		       }
		       else {
		    	   System.out.println("No rows selected");
		       }
		}
}


private class DeleteContainerFromFreightTrain implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int row = (int) ft2.getSelectedRow();
			if(row >= 0) {
				int row2 = ft2Containers.getSelectedRow();
				if(row2 >= 0) {
					ft.get(row).getArrayOfContainers().remove(row2);
					modelContainerColums.removeRow(row2);
				}
				else {
					System.out.println("No container row selected");
				}
			}
			else {
				System.out.println("No freight train selected");
			}
		}
}

private class EditContainerFromFreightTrain implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		/*******************
		 * 
		 * Edits the containers of a certain freight train
		 * 
		 ********************/
		int row = ft2.getSelectedRow();//Selected Freight Train
		if(row>=0) {
			int row2 = ft2Containers.getSelectedRow();//Selected Container
			if(row2>=0) {
				if(!contLabel.getText().isEmpty() && !contMaxWeight.getText().isEmpty() && !contNumItems.getText().isEmpty()) {
						try{
							String name = contLabel.getText();
							int mxWeight = Integer.parseInt(contMaxWeight.getText());
							int nItems = Integer.parseInt(contNumItems.getText());
							
							int remainingWeight = ft.get(row).getRemaningWeightToEditCargo(ft.get(row).getArrayOfContainers().get(row2));
							if(remainingWeight >= mxWeight) {
								
								ft.get(row).getArrayOfContainers().get(row2).setLabel(name);
								modelContainerColums.setValueAt(name, row2, 0);
								
								ft.get(row).getArrayOfContainers().get(row2).setMaxWeight(mxWeight);
								int realMaxW = ft.get(row).getArrayOfContainers().get(row2).getMaxWeight();
								modelContainerColums.setValueAt(realMaxW, row2, 1);//Not always it will change the weight like the input				
								
								ft.get(row).getArrayOfContainers().get(row2).setMaxNumberOfItems(nItems);
								int realNumItems = ft.get(row).getArrayOfContainers().get(row2).getMaxNumberOfItems();
								modelContainerColums.setValueAt(realNumItems, row2, 2);
							}
							else {
								System.out.println("The new container's maximum weight is above the remaning weight of the freight train's cargo. Reduce the value");
							}
						} catch (NumberFormatException e1) {
							 	System.out.println("Enter digits only on the digit inputs!");
							 	status = "REMARK";
								 message = e1.getMessage()+" - Enter digits only on the digit inputs!";
								 messageLabel.setText(status + "!! " + message);
								 new WriteSystemEvents(status,message);
						}
				}
				else{
					System.out.println("Some fields are empty!");
				}
			}
			else {
				System.out.println("No container row selected");
			}
		}
		else {
			System.out.println("No freight train selected");
		}
	}
}


private class ChangeLayout implements ActionListener {
	private JPanel layout;
	
	ChangeLayout(JPanel layout) {
		this.layout = layout;
	}
	
	public void actionPerformed(ActionEvent e) {
		section.removeAll();
		section.add(this.layout);
		section.repaint();
		section.revalidate();
	}
}
}
	

