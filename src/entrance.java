import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *  Title      : entrance.java
 *  Description: This class is the entrance class to achieve the entrance function of car park
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class entrance extends JFrame implements ActionListener, ItemListener{

	
	tariff tariff1;
	parking parking1;
	private int userCounter=0;
	private JToggleButton button2,button3;
	private JPanel panel1,panel2,panel3,panel4,panel5;
	private JLabel label1,label2;
	private JTextField textField1,textField2;
	private String id,line,buff,buf[],name,parkingID;
	private JButton button1;
	private File file1;
	private FlowLayout  flow;
	private FileWriter fw = null;
	private SimpleDateFormat df;
	private ArrayList<String> bufer;
	private ArrayList<ArrayList<String>> userRecord;
	private ButtonGroup group;
	private static final long serialVersionUID = 1L;
	private Calendar calendar;
	private File staffRecord;

	/**
	 * Title      : getCalendar
	 * Description: This method gets a instance of calendar class
	 * @param args
	 * @return Calendar
	 * @throws null
	 */

	public static Calendar getCalendar(){                
		Calendar calendar1=Calendar.getInstance();
		return calendar1;
	}

	/**
	 * Title      : entrance
	 * Description: This method sets GUI
	 * @param parking
	 * @return null
	 * @throws IOException 
	 */
	
	public entrance(parking parking1) throws IOException{
		this.parking1=parking1;
		this.setTitle("Entrance");
		calendar=getCalendar();
		calendar.setTime(new Date());  
		tariff1=new tariff(id,parking1,this);
		df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		button2=new JToggleButton("For school staff");
		button3=new JToggleButton("For visitor");
		button2.setName("button2");
		button3.setName("button3");
		label1=new JLabel("Please type in your ID");
		label2=new JLabel("Please type in your name");
		label1.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
		label2.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
		label1.setBorder (BorderFactory.createRaisedBevelBorder());
		label2.setBorder (BorderFactory.createRaisedBevelBorder());
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		group=new ButtonGroup();
		textField1=new JTextField(5);
		textField2=new JTextField(5);
		textField1.setName("textField1");
		textField2.setName("textField2");
		button1=new JButton("Check");
		button1.setName("button1");
		flow=new FlowLayout(0);
		this.setLayout(new GridLayout(5,0));
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		panel1.add(button2);
		panel2.add(label1,"West");
		panel2.add(textField1,"East");
		panel3.add(button3);
		panel4.add(label2,"West");
		panel4.add(textField2,"East");
		panel5.add(button1);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		group.add(button2);
		group.add(button3);
		panel1.setBackground(SystemColor.activeCaption);
		panel2.setBackground(SystemColor.activeCaption);
		panel3.setBackground(SystemColor.activeCaption);
		panel4.setBackground(SystemColor.activeCaption);
		panel5.setBackground(SystemColor.activeCaption);
		this.getContentPane().setBackground(SystemColor.activeCaption);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setSize(500, 400);
	    this.setLocation(400, 250);
	}
	
	/**
	 * Title      : actionPerformed
	 * Description: This method does function call to store visitor or staff record
	 * @param ActionEvent
	 * @return null
	 * @throws null 
	 */
	
	public void actionPerformed(ActionEvent e){
		try{
			if(e.getSource()==button2){
				panel4.setVisible(false);
				panel2.setVisible(true);
			}
			if(e.getSource()==button3){
				panel2.setVisible(false);
				panel4.setVisible(true);
			}
			if(e.getSource()==button1){
				staffRecord=new File("staffRecord.txt");
				if(!staffRecord.exists())
					staffRecord.createNewFile();
				if(button2.isSelected()){
					id=textField1.getText();
					textField1.setText(null);
					textField2.setText(null);
					this.setVisible(false);
					BufferedReader br = new BufferedReader(new FileReader("staff.txt"));
					while((buff=br.readLine()) != null&&buff.trim().length()> 0){
						buf=buff.split(" ");
						if(buf[0].equals(id)){
							JOptionPane.showMessageDialog(null, "Identity recognized","Success", JOptionPane.INFORMATION_MESSAGE);
							if(parking1.userIDCheck(id)==true){
								JOptionPane.showMessageDialog(null, "You have already entered.","Hint", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							if((parkingID=parking1.enter(id,this))!=null&&parkingID!=null){
								JOptionPane.showMessageDialog(null, "You are allocated at "+parkingID,"Welcome, "+id, JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(null, "Sorry, there is no more space for parking.","Hint", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							return;
							}
					}
					JOptionPane.showMessageDialog(null, "No staff matched", "alert", JOptionPane.ERROR_MESSAGE);
				}
				if(button3.isSelected()){
					if(getDayOfWeek() && openingTimeCheck()){
						JOptionPane.showMessageDialog(null, "Sorry,Visitor is only allowed during weekends and specific holidays!","alert", JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
						return;
					}
					name=textField2.getText();
					textField1.setText(null);
					textField2.setText(null);
					this.setVisible(false);
					tariff1=new tariff(name,parking1,this);
					id=tariff1.getUserID();
					if(id==null){
						JOptionPane.showMessageDialog(null, "Sorry, there is no more space for parking.","Hint", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					tariff1.setVisible(true);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	}


	public void itemStateChanged(ItemEvent e){
	}
	
	/**
	 * Title      : getEnterTime
	 * Description: This method gives a interface for other class to read entering time in visitorRecord.txt or staffRecord.txt through userID
	 * @param String
	 * @return String
	 * @throws IOException 
	 */
	
	public String getEnterTime(String userID) throws IOException{
		buf=new String[4];
		String enterTime=null;
		if((userID.subSequence(0,1).equals("B"))){
			BufferedReader br = new BufferedReader(new FileReader("visitorRecord.txt"));
			while((buff=br.readLine()) != null){
				buf=buff.split(" ");
				if(buf[0].equals(userID)){
					return buf[3];
				}
			}
			return null;
		}
		else if((userID.subSequence(0,1).equals("A"))){
			BufferedReader br = new BufferedReader(new FileReader("staffRecord.txt"));
			while((buff=br.readLine()) != null&&buff.trim().length() > 0){
				buf=buff.split(" ");
				if(buf[0].equals(userID)){
					enterTime=buf[1];
				}
			}
		}
		return enterTime;
	}
	
	/**
	 * Title      : getDayOfWeek
	 * Description: This method judges if today is weekend
	 * @param null
	 * @return boolean
	 * @throws null 
	 */
	
	public boolean getDayOfWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		
		if((calendar.get(Calendar.DAY_OF_WEEK)-1)!=0&&(calendar.get(Calendar.DAY_OF_WEEK)-1)!=6)
			return true;
		else	
			return false;	
		
	}
	
	/**
	 * Title      : openingTimeCheck
	 * Description: This method reads openningTime.txt to find it today is opening day
	 * @param null
	 * @return boolean
	 * @throws null
	 */
	
	public boolean openingTimeCheck(){
		ArrayList<String> currentInfo = new ArrayList<String>();
		String time = getCurrentTime();
		int flag = 0;
		int month_input = Integer.parseInt(time.substring(5,7));
		int day_input = Integer.parseInt(time.substring(8,10));
		File file = new File("openingTime.txt");
		try{
			 Scanner input = new Scanner(file);
			 while(input.hasNext())
				 currentInfo.add(input.next());
			input.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		for(int i = 0; i < currentInfo.size(); i++){
			if(i % 2 == 0){
				int month1 = Integer.parseInt(currentInfo.get(i).substring(0,2));
				int day1 = Integer.parseInt(currentInfo.get(i).substring(3,5));
				if(month_input>month1 ||(month_input == month1 && day_input>=day1 )){
					int month2 = Integer.parseInt(currentInfo.get(i+1).substring(0,2));
					int day2 = Integer.parseInt(currentInfo.get(i+1).substring(3,5));
					if(month_input<month2 ||(month_input == month2 && day_input<=day2 )){
						flag = 1;
						break;
					}
				}		
			}
		}
		if(flag == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Title      : getCurrentTim
	 * Description: This method gets format of date
	 * @param null
	 * @return String
	 * @throws null
	 */
	
	/*获取当前时间的字符串表达形式*/
	private String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String line= df.format(new Date());
		return line;
	}

	/**
	 * Title      : addDepartureTime
	 * Description: This method gives a interface for other class to add leaving time in visitorRecord.txt or staffRecord.txt through userID
	 * @param String
	 * @return null
	 * @throws IOException
	 */
	
	public void addDepartureTime(String userID,String departureTime) throws IOException{
		BufferedReader br;
		int bufCounter;
		userRecord=new ArrayList<ArrayList<String>>();
		if(userID.subSequence(0,1).equals("B")){
			br = new BufferedReader(new FileReader("visitorRecord.txt"));
			while(!((buff=br.readLine())==null)&&buff.trim().length()>0){
				buf=buff.split(" ");
				bufer=new ArrayList<String>();
				userCounter++;
				bufer.add(buf[0]);
				bufer.add(buf[1]);
				bufer.add(buf[2]);
				bufer.add(buf[3]);
				bufer.add("0");
				userRecord.add(bufer);
			}
			br.close();
			for(bufCounter=0;bufCounter<=userCounter-1;bufCounter++){
				if(userRecord.get(bufCounter).get(0).equals(userID)){
					userRecord.get(bufCounter).set(4,departureTime);
					break;
				}
			}
			fw = new FileWriter("visitorRecord.txt");
			for(bufCounter=0;bufCounter<=userCounter-1;bufCounter++){
				line=userRecord.get(bufCounter).get(0)+" "+userRecord.get(bufCounter).get(1)
						+" "+userRecord.get(bufCounter).get(2)+" "+userRecord.get(bufCounter).get(3)+
								" "+userRecord.get(bufCounter).get(4)+"\r\n";
				fw.write(line,0,line.length());
			}
			fw.close();
		}
		else if(userID.subSequence(0,1).equals("A")){
			br = new BufferedReader(new FileReader("staffRecord.txt"));
			while(!((buff=br.readLine())==null)&&buff.trim().length()>0){
				buf=buff.split(" ");
				bufer=new ArrayList<String>();
				bufer.add(buf[0]);
				bufer.add(buf[1]);
				if(buf.length<3)
					bufer.add("0");
				else{
					bufer.add(buf[2]);
				}
				userRecord.add(bufer);
				userCounter++;
			}
			for(bufCounter=0;bufCounter<=userCounter-1;bufCounter++){
				if(userRecord.get(bufCounter).get(0).equals(userID)&&userRecord.get(bufCounter).get(2).equals("0")){
					userRecord.get(bufCounter).set(2,departureTime);
					break;
				}
			}
			fw = new FileWriter("staffRecord.txt");
			for(bufCounter=0;bufCounter<=userCounter-1;bufCounter++){
				line=userRecord.get(bufCounter).get(0)+" "+userRecord.get(bufCounter).get(1)
						+" "+userRecord.get(bufCounter).get(2)+"\r\n";
				fw.write(line,0,line.length());
			}
			fw.close();
		}
		userCounter=0;
	}

	/**
	 * Title      : writeStaffRecord
	 * Description: This method adds record in staffRecord.txt 
	 * @param null
	 * @return null
	 * @throws IOException
	 */
	
	public void writeStaffRecord() throws IOException{
		if(staffRecord.length()==0){
			fw=new FileWriter("staffRecord.txt");
			line=id+" "+df.format(new Date())+" "+"0"+"\r\n";
			fw.write(line);
			fw.flush();
			fw.close();
		}
		else
		{
			fw=new FileWriter("staffRecord.txt",true);
			line=id+" "+df.format(new Date())+" "+"0"+"\r\n";
			fw.write(line);
			fw.flush();
			fw.close();
		}
		return;
	}
}
