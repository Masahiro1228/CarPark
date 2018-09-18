import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *  Title      : exit.java
 *  Description: This class is the exit class to achieve the exit function of car park
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class exit extends JFrame implements ActionListener{
	private JButton button1;
	private JLabel label1;
	private JPanel panel1,panel2,panel3;
	private JTextField textField1;
	private parking parking1;
	private FileWriter fw;
	private String buff,buf[],userID,line;
	private BufferedReader br;
	private ArrayList<String> bufer;
	private int userCounter=0,bufCounter=0;
	private ArrayList<ArrayList<String>> userRecord;
	private entrance entrance1;
	private monthlyBill monthlyBill1;
	private SimpleDateFormat df;
	private Calendar calendar;
	
	/**
	 * Title      : exit
	 * Description: This method sets GUI
	 * @param parking,entrance
	 * @return null
	 * @throws null
	 */
	
	public exit(parking parking1,entrance entrance1){
		this.parking1=parking1;
		this.entrance1=entrance1;
		textField1=new JTextField();
		textField1.setName("textField1");
		label1=new JLabel("Please type in your userID");
		df=new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		button1=new JButton("OK");
		button1.setName("button1");
		button1.addActionListener(this);
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		textField1.setColumns(10);
		this.setLayout(new GridLayout(3,0));
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		panel1.add(label1,BorderLayout.CENTER);
		panel2.add(textField1,BorderLayout.CENTER);
		panel3.add(button1,BorderLayout.CENTER);
		panel1.setBackground(SystemColor.activeCaption);
		panel2.setBackground(SystemColor.activeCaption);
		panel3.setBackground(SystemColor.activeCaption);
		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setBackground(SystemColor.activeCaption);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	
	/**
	 * Title      : actionPerformed
	 * Description: This method records leaving time and refresh car park information
	 * @param ActionEven
	 * @return null
	 * @throws null 
	 */
	
	public void actionPerformed(ActionEvent e) {
		try{
			if(e.getSource()==button1){
				userID=textField1.getText();
				userRecord=new ArrayList<ArrayList<String>>();
				if(userID.subSequence(0,1).equals("A")){
					if(parking1.userIDCheck(userID)==false){
						JOptionPane.showMessageDialog(null, "Wrong userID.","Hint", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					String departureTime=df.format((new Date()));
					Staff_use window = new Staff_use(entrance1,parking1,userID,departureTime);
					parking1.leave(userID);
					window.frame.setVisible(true);
					calendar=Calendar.getInstance();
					int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				    int now = calendar.get(Calendar.DAY_OF_MONTH);
				}
				else if(parking1.userIDCheck(userID)){
					br = new BufferedReader(new FileReader("visitorRecord.txt"));
					while(!((buff=br.readLine())==null)&&buff.trim().length()>0){
						buf=buff.split(" ");
						bufer=new ArrayList<String>();
						userCounter++;
						bufer.add(buf[0]);
						bufer.add(buf[1]);
						bufer.add(buf[2]);
						bufer.add(buf[3]);
						if(buf.length==5){
							bufer.add(buf[4]);
						}
						else
							bufer.add("0");
						userRecord.add(bufer);
					}
					br.close();
					fw = new FileWriter("visitorRecord.txt");
					for(bufCounter=0;bufCounter<=userCounter-1;bufCounter++){
						if(userID.equals(userRecord.get(bufCounter).get(0))){
							if(userRecord.get(bufCounter).get(1).equals("0")){
								JOptionPane.showMessageDialog(null, "Wrong userID.","Alert", JOptionPane.ERROR_MESSAGE);
								line=userRecord.get(bufCounter).get(0)+" "+userRecord.get(bufCounter).get(1)
										+" "+userRecord.get(bufCounter).get(2)+" "+userRecord.get(bufCounter).get(3)+
												" "+userRecord.get(bufCounter).get(4)+"\r\n";
								fw.write(line,0,line.length());
							}
							else if(userRecord.get(bufCounter).get(4).equals("0")){
								JOptionPane.showMessageDialog(null, "Please go to the payment station first.","Alert", JOptionPane.ERROR_MESSAGE);
								line=userRecord.get(bufCounter).get(0)+" "+userRecord.get(bufCounter).get(1)
										+" "+userRecord.get(bufCounter).get(2)+" "+userRecord.get(bufCounter).get(3)+
												" "+userRecord.get(bufCounter).get(4)+"\r\n";
								fw.write(line,0,line.length());
							}
							else{
								line=userID+" 0 null null 0\r\n";
								fw.write(line,0,line.length());
								parking1.leave(userID);
								JOptionPane.showMessageDialog(null, "Welcome for your next visiting.","Success", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else{
							line=userRecord.get(bufCounter).get(0)+" "+userRecord.get(bufCounter).get(1)
									+" "+userRecord.get(bufCounter).get(2)+" "+userRecord.get(bufCounter).get(3)+
											" "+userRecord.get(bufCounter).get(4)+"\r\n";
							fw.write(line,0,line.length());
						}
					}
					fw.close();
					userCounter=0;
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong userID.","Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
			textField1.setText(null);
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setVisible(false);
	}
}
