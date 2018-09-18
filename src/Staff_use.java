
import java.awt.SystemColor;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *  Title      : Staff_use.java
 *  Description: This class is the Staff_use class to set GUI for staff
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class Staff_use {

	JFrame frame;
	entrance entrance1;
	parking parking1;
	private JLabel lblYourParkingExpense;
	private JLabel lblFromYourMonthly;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private String userID,departureTime;

	/**
	 * Title      : Staff_use
	 * Description: get instance
	 * @param entrance,parking,String,
	 * @return null
	 * @throws IOException 
	 */
	
	public Staff_use(entrance entrance1,parking parking1,String userID,String departureTime) throws IOException {
		this.entrance1=entrance1;
		this.parking1=parking1;
		this.userID=userID;
		this.departureTime=departureTime;
		entrance1.addDepartureTime(userID,departureTime);
		initialize();
	}

	/**
	 * Title      : initialize
	 * Description: initialize GUI to show staff record information
	 * @return null
	 * @throws IOException 
	 */
	
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setName("staffFrame");
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblYouEnteredOur = new JLabel("Time of your arrival:");
		
		JLabel lblYouLeaveOur = new JLabel("Time of your departure:");
		
		lblYourParkingExpense = new JLabel("Your parking expense will be deducted");
		
		lblFromYourMonthly = new JLabel("from your monthly salary at \uFFE11 per day.");
		
		lblNewLabel = new JLabel(entrance1.getEnterTime(userID));
		
		lblNewLabel_1 = new JLabel(departureTime);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblYouEnteredOur, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblYouLeaveOur, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(110))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addComponent(lblFromYourMonthly, GroupLayout.PREFERRED_SIZE, 330, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(105)
					.addComponent(lblYourParkingExpense, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(107))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYouEnteredOur, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYouLeaveOur)
						.addComponent(lblNewLabel_1))
					.addGap(68)
					.addComponent(lblYourParkingExpense, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblFromYourMonthly, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(46))
		);
		frame.getContentPane().setLayout(groupLayout);
		lblYourParkingExpense.setBackground(SystemColor.activeCaption);
		lblFromYourMonthly.setBackground(SystemColor.activeCaption);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
	}
}
