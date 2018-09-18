import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *  Title      : Mainboard.java
 *  Description: This class is the Mainboard class to set main GUI 
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class Mainboard implements ActionListener{

	private JFrame frame;
	private entrance entrance1;
	private paymentStation paymentStation1;
	private register register1;
	private parking parking1;
	private exit exit1;
	private BufferedReader br;
	private String buff,id,buf[];
	JButton entrance,registration,parking,paymentStation,exit;
	
	/**
	 * Title      : main
	 * Description: Launch the application.
	 * @param String[]
	 * @return null
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainboard window = new Mainboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Title      : Mainboard
	 * Description: Create the application.
	 * @return null
	 * @throws IOException 
	 */
	public Mainboard() throws IOException {
		initialize();
	}

	/**
	 * Title      : initialize
	 * Description: Initialize the contents of the frame.
	 * @return null
	 * @throws IOException 
	 */
	private void initialize() throws IOException{
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setForeground(SystemColor.window);
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSystem = new JLabel("Car Park Control System");
		lblSystem.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
		
		JLabel lblWelcomeToOur = new JLabel("Welcome to our system!");
		
		JLabel lblPleaseChooseA = new JLabel("Please choose a service.");
		
		entrance = new JButton("");
		entrance.setIcon(new ImageIcon(Mainboard.class.getResource("/images/Enterance.jpg")));
		
		registration = new JButton("");
		registration.setIcon(new ImageIcon(Mainboard.class.getResource("/images/Register office.jpg")));
		
		parking = new JButton("");
		parking.setIcon(new ImageIcon(Mainboard.class.getResource("/images/Parking space.jpg")));
		
		paymentStation = new JButton("");
		paymentStation.setIcon(new ImageIcon(Mainboard.class.getResource("/images/Payment station.jpg")));
		
		exit = new JButton("");
		exit.setIcon(new ImageIcon(Mainboard.class.getResource("/images/Exit.jpg")));
		
		JLabel lblEnterance = new JLabel("Enterance");
		
		JLabel lblRegisterOffice = new JLabel("Register office");
		
		JLabel lblParkingSpace = new JLabel("Parking space");
		
		JLabel lblPaymentStation = new JLabel("Payment station");
		
		JLabel lblExit = new JLabel("Exit");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(126)
							.addComponent(lblSystem))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(173)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPleaseChooseA)
								.addComponent(lblWelcomeToOur)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(entrance, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterance, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(registration, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(parking, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(paymentStation, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(exit, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblRegisterOffice)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblParkingSpace)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPaymentStation)
									.addGap(18)
									.addComponent(lblExit)))))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSystem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWelcomeToOur)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPleaseChooseA)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(exit, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(entrance, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
							.addComponent(registration, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(paymentStation, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(parking, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblParkingSpace)
						.addComponent(lblPaymentStation)
						.addComponent(lblExit)
						.addComponent(lblEnterance)
						.addComponent(lblRegisterOffice))
					.addGap(51))
		);
		frame.getContentPane().setLayout(groupLayout);
		entrance.addActionListener(this);
		registration.addActionListener(this);
		parking.addActionListener(this);
		paymentStation.addActionListener(this);
		exit.addActionListener(this);
		register1=new register();
		parking1=new parking();
		entrance1=new entrance(parking1);
		paymentStation1=new paymentStation(entrance1,parking1,register1);
		exit1=new exit(parking1,entrance1);
		BufferedReader br = new BufferedReader(new FileReader("tariffs.txt")); 
		ArrayList<String> tariffBufer;
		ArrayList<ArrayList<String>> tariffList;
		int tariffsCounter=0;
		tariffList=new ArrayList<ArrayList<String>>();
		while((buff=br.readLine()) != null&&buff.trim().length()> 0){
			tariffsCounter++;
			buf=buff.split(" ");
			tariffBufer=new ArrayList<String>();
			tariffBufer.add(buf[0]);
			tariffBufer.add(buf[1]);
			tariffBufer.add(buf[2]);
			tariffList.add(tariffBufer);
		}
		br.close();
		JOptionPane.showMessageDialog(null, "Welcome to this parking lot!\r\nToday is the "+getDayOfWeek()+" day of the week.\r\n"
				+"Our parking lot is not avaliable to visitors in weekdays\r\n"+
				"There are "+parking1.counter()+" positions left in the parking lot.\r\n"
				+"The tariff information is:\r\n"
				+"From    To    Expense\r\n"
				+tariffList.get(0).get(0)+"          "+tariffList.get(0).get(1)+"        "+tariffList.get(0).get(2)+"\r\n"
				+tariffList.get(1).get(0)+"          "+tariffList.get(1).get(1)+"        "+tariffList.get(1).get(2)+"\r\n"
				+tariffList.get(2).get(0)+"          "+tariffList.get(2).get(1)+"        "+tariffList.get(2).get(2)+"\r\n"
				+tariffList.get(3).get(0)+"          "+tariffList.get(3).get(1)+"      "+tariffList.get(3).get(2)+"\r\n"
				+tariffList.get(4).get(0)+"        "+tariffList.get(4).get(1)+"      "+tariffList.get(4).get(2)+"\r\n");
	}
	
	/**
	 * Title      : getDayOfWeek
	 * Description: get date.
	 * @return String
	 */
	
	public int getDayOfWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		return calendar.get(Calendar.DAY_OF_WEEK)-1;		
	}
	
	/**
	 * Title      : actionPerformed
	 * Description: check ID for register
	 * @param ActionEvent
	 * @return null
	 */
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==entrance)
			entrance1.setVisible(true);
		if(e.getSource()==paymentStation){
			paymentStation1.frame.setVisible(true);
		}
		if(e.getSource()==parking){
			try {
				parking1=new parking();
				parking1.showLayout();
				parking1.setVisible(true);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==exit)
			exit1.setVisible(true);
		if(e.getSource()==registration){
			String inputValue=JOptionPane.showInputDialog("Please type in your staff ID:");
			if(inputValue.equals("A000"))
				register1.setVisible(true);
			else
				JOptionPane.showMessageDialog(null, "You don't have the privilege");
		}
	}
	
}
