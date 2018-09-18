
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

/**
 *  Title      : Public_use.java
 *  Description: This class is the Public_use class to set public GUI
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class Public_use{

	JFrame frame;
	entrance entrance1;
	parking parking1;
	register register1;
	private int tariffCounter=0;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPanel panel;
	private JButton btnAdd,btnAdd_1,btnAdd_2,btnCheck;
	private JLabel lblPaymentStation,lblYourArrivalTime,lblYourDepartureTime,lblYourTotalExpense,
	lblNewLabel,lblNewLabel_1,lblNewLabel_2,label,label_1,label_2,lblNewLabel_3,lblNewLabel_4,lblNewLabel_5,
	lblYourTotalMoney,lblNewLabel_6;
	private String userID,enterTime,departureTime;
	private int stayTime,num1=0,num2=0,num3=0;
	private double totalExpense;
	private ArrayList<ArrayList<String>> tariffList;
	private SimpleDateFormat sdf;

	/**
	 * Title      : Public_use 
	 * Description: get instance
	 * @param entrance, String, register
	 * @throws IOException, ParseException
	 */
	
	public Public_use(entrance entrance1,String userID,String departureTime,register register1) throws IOException, ParseException{
		this.entrance1=entrance1;
		this.parking1=parking1;
		this.userID=userID;
		this.register1=register1;
		this.departureTime=departureTime;
		enterTime=entrance1.getEnterTime(userID);
		initialize();
	}

	/**
	 * Title      : initialize
	 * Description: initialize GUI of payment for visitor
	 * @return null
	 * @throws parseException 
	 */
	
	private void initialize() throws ParseException {
		frame = new JFrame();
		lblPaymentStation = new JLabel("Payment Station");
		lblPaymentStation.setFont(new Font("SimSun-ExtB", Font.BOLD, 16));
		lblYourArrivalTime = new JLabel("Your arrival time:");
		lblYourDepartureTime = new JLabel("Your departure time:");
		lblYourTotalExpense = new JLabel("Your total expense:");
		lblNewLabel = new JLabel(enterTime);
		lblNewLabel_1 = new JLabel();
		lblNewLabel_2 = new JLabel();
		label = new JLabel("\uFFE10.5");
		label_1 = new JLabel("\uFFE11");
		label_2 = new JLabel("\uFFE12");
		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_4 = new JLabel("0");
		lblNewLabel_5 = new JLabel("0");
		btnAdd = new JButton("Add");
		btnAdd_1 = new JButton("Add");
		btnAdd_2 = new JButton("Add");
		btnAdd.setName("btnAdd");
		btnAdd_1.setName("btnAdd_1");
		btnAdd_2.setName("btnAdd_2");
		lblYourTotalMoney = new JLabel("Your total money is");
		lblNewLabel_6 = new JLabel("0");
		btnCheck = new JButton("check");
		btnCheck.setName("btnCheck");
		tariffList=new ArrayList<ArrayList<String>>();
		panel = new JPanel();
		sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		if(enterTime.equals("null")||departureTime.equals("null"))
			stayTime=0;
		else
			stayTime=(int)((sdf.parse(departureTime).getTime()-sdf.parse(enterTime).getTime())/(1000*3600));
		lblNewLabel_1.setText(departureTime);
		tariffList=entrance1.tariff1.getTariff();
		while(true){
			if(tariffCounter>=tariffList.size()){
				break;
			}
			else if(stayTime>=Integer.parseInt(tariffList.get(tariffCounter).get(0))&&stayTime<Integer.
					parseInt(tariffList.get(tariffCounter).get(1))){
				totalExpense=Double.parseDouble(tariffList.get(tariffCounter).get(2));
			}
			tariffCounter++;
		}
		if(totalExpense!=0)
			lblNewLabel_2.setText(String.valueOf(totalExpense));
		else
			lblNewLabel_2.setText("The time must be around 0 to 24 hour!");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(212, Short.MAX_VALUE)
					.addComponent(lblPaymentStation)
					.addGap(207))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblYourDepartureTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblYourArrivalTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblYourTotalExpense, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd_2)
						.addComponent(btnAdd_1)
						.addComponent(btnAdd))
					.addContainerGap(51, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(107)
					.addComponent(lblYourTotalMoney)
					.addGap(30)
					.addComponent(lblNewLabel_6)
					.addGap(34)
					.addComponent(btnCheck, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(117, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPaymentStation)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourArrivalTime)
						.addComponent(lblNewLabel)
						.addComponent(label)
						.addComponent(lblNewLabel_3)
						.addComponent(btnAdd))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourDepartureTime)
						.addComponent(lblNewLabel_1)
						.addComponent(label_1)
						.addComponent(lblNewLabel_4)
						.addComponent(btnAdd_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourTotalExpense)
						.addComponent(lblNewLabel_2)
						.addComponent(label_2)
						.addComponent(lblNewLabel_5)
						.addComponent(btnAdd_2))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourTotalMoney)
						.addComponent(lblNewLabel_6)
						.addComponent(btnCheck))
					.addGap(45))
		);
		afterCheck ac=new afterCheck();
		btnCheck.addActionListener(ac);
		btnAdd.addActionListener(ac);
		btnAdd_1.addActionListener(ac);
		btnAdd_2.addActionListener(ac);
		frame.getContentPane().setLayout(groupLayout);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Title      : afterCheck
	 * Description: calculate payment
	 * @param ActionEvent
	 * @return null
	 */
	
	public class afterCheck implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==btnCheck){
				int a = num1;
				int b = num2;
				int c = num3;
				if ((0.5 * a + b + 2 * c) >= totalExpense) {
					try {
						entrance1.addDepartureTime(userID, departureTime);
						register1.setReceiptNumber(register1.getReceiptNumber()-1);
						register1.addCash(0.5 * a + b + 2 * c);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,"Wrong!Please try again.","Aint",JOptionPane.ERROR_MESSAGE);
					num1=0;
					num2=0;
					num3=0;
					updateNum();
					return;
				}
				frame.setVisible(false);
				JOptionPane.showMessageDialog(null, "You can leave the parking lot now.","Success", JOptionPane.INFORMATION_MESSAGE);
			}
			if(event.getSource()==btnAdd){
				num1++;
				lblNewLabel_3.setText(String.valueOf(num1));
			}
			if(event.getSource()==btnAdd_1){
				num2++;
				lblNewLabel_4.setText(String.valueOf(num2));
			}
			if(event.getSource()==btnAdd_2){
				num3++;
				lblNewLabel_5.setText(String.valueOf(num3));
			}
			lblNewLabel_6.setText(String.valueOf(0.5*num1+1*num2+2*num3));
		}
	}
	
	/**
	 * Title      : updateNum
	 * Description: show the money visitor pay in GUI
	 * @return null
	 */
	public void updateNum(){
		lblNewLabel_3.setText(String.valueOf(num1));
		lblNewLabel_4.setText(String.valueOf(num2));
		lblNewLabel_5.setText(String.valueOf(num3));
		lblNewLabel_6.setText(String.valueOf(0.5*num1+1*num2+2*num3));
	}
}
