
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Title      : paymentStation.java
 *  Description: This class is the payment class to achieve the payment function of car park
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class paymentStation {

	JFrame frame;
	entrance entrance1;
	parking parking1;
	register register1;
	private JTextField textField;
	private String userID,departureTime;

	/**
	 * Title      : paymentStation
	 * Description: get instance
	 * @param entrance,parking,register
	 */
	
	public paymentStation(entrance entrance1,parking parking1,register register1) {
		this.entrance1=entrance1;
		this.parking1=parking1;
		this.register1=register1;
		initialize();
	}
	
	/**
	 * Title      : getDate
	 * Description: ger formate of date
	 * @param Date
	 * @return SimpleDateFormat
	 */
	
	public String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * Title      : initialize
	 * Description: set GUI of payment 
	 * @return null
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setName("paymentFrame");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel("");
		
		JLabel lblPleaseChooseThe = new JLabel("Please enter the user ID:");
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		
		textField = new JTextField();
		textField.setName("textField");
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					int keyCode=arg0.getKeyCode();
					if(keyCode==KeyEvent.VK_ENTER){
						userID=textField.getText();
						departureTime=getDate(new Date());	
						frame.setVisible(false);
						if(!parking1.userIDCheck(userID)){
							JOptionPane.showMessageDialog(null, "Wrong userID!", "alert", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(textField.getText().subSequence(0,1).equals("B")){
							Public_use window;
							window = new Public_use(entrance1,userID,departureTime,register1);
							window.frame.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "Wrong userID!", "alert", JOptionPane.ERROR_MESSAGE);
						textField.setText(null);
					}
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textField.setColumns(10);
		frame.setLayout(new GridLayout(2,0));
		frame.add(panel1);
		frame.add(panel2);
		panel1.add(lblPleaseChooseThe);
		panel2.add(textField);
		panel1.setBackground(SystemColor.activeCaption);
		panel2.setBackground(SystemColor.activeCaption);
	}
}
