import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *  Title      : register.java
 *  Description: This class is the register class to achieve the register function of car park
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class register extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedReader br;
	private String buff,buf[],id;
	private JButton registration,addReceipt,cashCollection,monthlyBill;
	private JPanel panel1,panel2,panel3,panel4;
	private JLabel label1;
	private SimpleDateFormat df;
	
	/**
	 * Title      : register
	 * Description: initialize GUI of register for staff
	 * @return null
	 * @throws IOException 
	 */
	
	public register() throws IOException{
		registration=new JButton("Staff register");
		addReceipt=new JButton("Add blank receipt");
		cashCollection=new JButton("Cash collection");
		monthlyBill=new JButton("Generate monthlyBill");
		label1=new JLabel("Registration Office");
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		df=new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(137, Short.MAX_VALUE)
					.addComponent(label1)
					.addGap(150))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(157)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(registration, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(addReceipt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(cashCollection, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(monthlyBill, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
					.addGap(142))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1)
					.addGap(35)
					.addComponent(registration)
					.addGap(45)
					.addComponent(addReceipt)
					.addGap(46)
					.addComponent(cashCollection)
					.addGap(47)
					.addComponent(monthlyBill)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		registration.addActionListener(this);
		addReceipt.addActionListener(this);
		cashCollection.addActionListener(this);
		monthlyBill.addActionListener(this);
		setLayout(groupLayout);
		this.getContentPane().setBackground(SystemColor.activeCaption);
		panel1.setBackground(SystemColor.activeCaption);
		panel2.setBackground(SystemColor.activeCaption);
		panel3.setBackground(SystemColor.activeCaption);
		panel4.setBackground(SystemColor.activeCaption);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setSize(500, 400);
	    this.setLocation(400, 250);
	    File receipt=new File("receipt.txt");
	    File cashCollection=new File("cashCollection.txt");
	    if(!receipt.exists()){
	    	receipt.createNewFile();
	    }
	    if(!cashCollection.exists()){
	    	cashCollection.createNewFile();
	    }
	    registration.setName("registration");
	    monthlyBill.setName("monthlyBill");
	}
	
	/**
	 * Title      : statRegister
	 * Description: distribute new ID for new staff
	 * @param String
	 * @return null
	 * @throws IOException 
	 */
	
	public void startRegister(String name) throws IOException{
		br = new BufferedReader(new FileReader("staff.txt"));
		while((buff=br.readLine()) != null&&buff.trim().length()> 0){
			buf=buff.split(" ");
			id=buf[0];
		}
		JOptionPane.showMessageDialog(null, "You have allocated to be A"+(Integer.valueOf(id.substring(1,4))+1),"Hint", JOptionPane.INFORMATION_MESSAGE);
		FileWriter fw = new FileWriter("staff.txt",true);
		fw.write("\r\nA"+Integer.toString(Integer.valueOf(id.substring(1,4))+1)+" "+name);
		fw.flush();
		fw.close();
	}
	
	/**
	 * Title      : actionPerformed
	 * Description: check which function has been chosen
	 * @param ActionEVent
	 * @return null
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource()==registration){
				String inputValue=null;
				inputValue=JOptionPane.showInputDialog("Please type in your name"); 
				if(inputValue!=null){				
					startRegister(inputValue);
				}
			}
			if(e.getSource()==addReceipt){
				String inputValue = JOptionPane.showInputDialog("Please type in the number of receipt you want to add:(Remaining:"+getReceiptNumber()+")"); 
				if(!(inputValue==null))
					setReceiptNumber(Integer.parseInt(inputValue)+getReceiptNumber());
			}
			if(e.getSource()==cashCollection){
				clearCash();
				JOptionPane.showMessageDialog(null, "You have collected "+String.valueOf(getCashNumber())+" pounds in total","Success", JOptionPane.INFORMATION_MESSAGE);
			}
			if(e.getSource()==monthlyBill){
				String inputValue = JOptionPane.showInputDialog("Please type in the staff ID:"); 
				id=inputValue;
				if(userIDCheck()){
					generateMonthlyBill(inputValue);
					JOptionPane.showMessageDialog(null, "Your monthly bill has been produced.");
				}
			}
		} catch (IOException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}

	/**
	 * Title      : getReceiptNumber
	 * Description: get receipt's number in receipt.txt 
	 * @return null
	 * @throws IOException 
	 */
	
	public int getReceiptNumber() throws IOException{
		int receiptNumber=0;
		BufferedReader br = new BufferedReader(new FileReader("receipt.txt")); 
		if((buff=br.readLine())!=null&&buff.trim().length()> 0){
			receiptNumber=Integer.parseInt(buff);
		}
		br.close();
		return receiptNumber;
	}
	
	/**
	 * Title      : setReceiptNumber
	 * Description: set receipt's number in receipt.txt
	 * @param  int
	 * @return null
	 * @throws IOException 
	 */
	
	public void setReceiptNumber(int receiptNumber) throws IOException{
		FileWriter fw;
		String line;
		if(receiptNumber>=0){
			line=String.valueOf(receiptNumber);
			fw = new FileWriter("receipt.txt");
			fw.write(line,0,line.length());
			fw.flush();
			fw.close();
		}
		else
			JOptionPane.showMessageDialog(null, "No receipt left", "alert", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Title      : getCashNumber
	 * Description: get the sum of cash
	 * @return int
	 * @throws IOException 
	 */
	
	public double getCashNumber() throws NumberFormatException, IOException{
		double totalCashNumber=0;
		BufferedReader br = new BufferedReader(new FileReader("cashCollection.txt")); 
		while((buff=br.readLine())!=null&&buff.trim().length()> 0){
			buf=buff.split(" ");
			totalCashNumber+=Double.parseDouble(buf[0]);
		}
		br.close();
		return totalCashNumber;
	}
	
	/**
	 * Title      : clearCash
	 * Description: collect cash and set in cashCollection
	 * @return null
	 * @throws IOException,NumberFormaException 
	 */
	
	public void clearCash() throws NumberFormatException, IOException{
		FileWriter fw;
		String line;
		line=String.valueOf(getCashNumber())+" "+df.format(new Date())+"\r\n";
		fw = new FileWriter("cashCollection.txt");
		fw.write(line,0,line.length());
		fw.flush();
		fw.close();
	}
	
	/**
	 * Title      : addCash
	 * Description: provide interface for other class to add payment in cashCollection.txt
	 * @param parking
	 * @return null
	 * @throws IOException 
	 */
	
	public void addCash(double totalExpense) throws NumberFormatException, IOException{
		FileWriter fw;
		String line;
		if(getCashNumber()==0){
			fw=new FileWriter("cashCollection.txt");
			line=totalExpense+" "+df.format(new Date())+" "+"\r\n";
			fw.write(line);
			fw.flush();
			fw.close();
		}
		else
		{
			fw=new FileWriter("cashCollection.txt",true);
			line=totalExpense+" "+df.format(new Date())+" "+"\r\n";
			fw.write(line);
			fw.flush();
			fw.close();
		}
	}
	
	
	
	public void generateMonthlyBill(String userID) throws IOException, ParseException{
		monthlyBill monthlyBill1=new monthlyBill(userID);
	}
	
	/**
	 * Title      : userIDCheck
	 * Description: check userID
	 * @return boolean
	 * @throws IOException,HealessException
	 */
	
	public boolean userIDCheck() throws HeadlessException, IOException{
		BufferedReader br = new BufferedReader(new FileReader("staff.txt"));
		while((buff=br.readLine()) != null&&buff.trim().length()> 0){
			buf=buff.split(" ");
			if(buf[0].equals(id)){
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "No staff matched", "alert", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}
