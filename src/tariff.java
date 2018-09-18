import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *  Title      : tariff.java
 *  Description: This class is the tariff class to show tariff
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class tariff extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -206326513632954317L;
	private JPanel panel1,panel2,panel3,panel4,panel5;
	private String id,buff,buf[],line,name,parkingID;
	private ArrayList<String> bufer,tariffBufer;
	private ArrayList<ArrayList<String>> visitorRecord,tariffList;
	private int tariffsCounter=0,visitorCounter=0;
	private GridLayout grid1,grid2;
	private JButton button1;
	private JLabel buffLabel,showLabel;
	private FileWriter fw = null;
	private SimpleDateFormat df;
	private parking parking1;
	private entrance entrance1;
	//MyCanvas  canvas;
	
	/**
	 * Title      : tariff
	 * Description: initialize GUI to show tariff
	 * @param parking,entrance
	 * @return null
	 * @throws IOException 
	 */
	
	public tariff(String name,parking parking1,entrance entrance1) throws IOException{
		//canvas = new MyCanvas();
		this.setName("tariffFrame");
		this.name=name;
		this.parking1=parking1;
		this.entrance1=entrance1;
		df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		showLabel=new JLabel("Please check the tariffs");
		tariffList=new ArrayList<ArrayList<String>>();
		grid1=new GridLayout(0,4);
		grid2=new GridLayout(0,3);
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		button1=new JButton("OK");
		button1.setName("OKbutton");
		panel1.setLayout(grid1);
		panel2.setLayout(grid2);
		this.setLayout(new GridLayout(4,0));
		this.add(panel5);
		this.add(panel4);
		this.add(panel3);
		panel3.add(button1);
		panel5.add(showLabel);
		BufferedReader br = new BufferedReader(new FileReader("tariffs.txt")); 
		while((buff=br.readLine()) != null&&buff.trim().length()> 0){
			tariffBufer=new ArrayList<String>();
			tariffsCounter++;
			buf=buff.split(" ");
			panel1.add(new JLabel());
			panel1.add(buffLabel=new JLabel(buf[0]));
			buffLabel.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
			buffLabel.setBorder (BorderFactory.createLoweredBevelBorder());
			panel1.add(buffLabel=new JLabel("to"));
			buffLabel.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
			buffLabel.setBorder (BorderFactory.createLoweredBevelBorder());
			panel1.add(buffLabel=new JLabel(buf[1]));
			buffLabel.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
			buffLabel.setBorder (BorderFactory.createLoweredBevelBorder());
			panel2.add(buffLabel=new JLabel(buf[2]));
			buffLabel.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
			buffLabel.setBorder (BorderFactory.createLoweredBevelBorder());
			panel2.add(buffLabel=new JLabel("pounds"));
			buffLabel.setFont(new Font("SimSun-ExtB", Font.PLAIN, 20));
			buffLabel.setBorder (BorderFactory.createLoweredBevelBorder());
			panel2.add(new JLabel());
			tariffBufer.add(buf[0]);
			tariffBufer.add(buf[1]);
			tariffBufer.add(buf[2]);
			tariffBufer.add("0");
			tariffList.add(tariffBufer);
		}
		br.close();
		panel4.setLayout(new GridLayout(0,2));
		panel4.add(panel1);
		panel4.add(panel2);
		button1.addActionListener(this);
		panel1.setBackground(SystemColor.activeCaption);
		panel2.setBackground(SystemColor.activeCaption);
		panel3.setBackground(SystemColor.activeCaption);
		panel5.setBackground(SystemColor.activeCaption);
		this.getContentPane().setBackground(SystemColor.activeCaption);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setSize(500, 500);
	    this.setBackground(SystemColor.activeCaption);
	    this.setLocation(600, 100);
	}
	
	/**
	 * Title      : actionPerformed
	 * Description: record entering time in visitorrecord.txt
	 * @param ActionEvent
	 * @return null
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			visitorRecord=new ArrayList<ArrayList<String>>();
			if(e.getSource()==button1){
				dispose();
				File file1=new File("visitorRecord.txt");
				if(!file1.exists()){
					file1.createNewFile();
				}
				BufferedReader br = new BufferedReader(new FileReader("visitorRecord.txt"));
				while((buff=br.readLine()) != null&&buff.trim().length()> 0){
					bufer=new ArrayList<String>();
					visitorCounter++;
					buf=buff.split(" ");
					bufer.add(buf[0]);
					bufer.add(buf[1]);
					bufer.add(buf[2]);
					bufer.add(buf[3]);
					visitorRecord.add(bufer);
				}
				br.close();
				int bufCounter;
				for(bufCounter=0;bufCounter<=visitorCounter-1;bufCounter++){
					if(visitorRecord.get(bufCounter).get(1).equals("0")){
						id=visitorRecord.get(bufCounter).get(0);
						visitorRecord.get(bufCounter).set(1,"1");
						visitorRecord.get(bufCounter).set(2,name);
						visitorRecord.get(bufCounter).set(3,df.format(new Date()));
						break;
					}
				}
				fw = new FileWriter("visitorRecord.txt");
				for(bufCounter=0;bufCounter<=visitorCounter-1;bufCounter++){
					line=visitorRecord.get(bufCounter).get(0)+" "+visitorRecord.get(bufCounter).get(1)
							+" "+visitorRecord.get(bufCounter).get(2)+" "+visitorRecord.get(bufCounter).get(3)+
									" "+"\r\n";
					fw.write(line,0,line.length());
				}
				fw.flush();
				fw.close();
			}
			visitorCounter=0;
			if((parkingID=parking1.enter(id,entrance1))!=null&&parkingID==null){
				JOptionPane.showMessageDialog(null, "Sorry, there is no more space for parking.","Hint", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "You are allocated at "+getPakingID(),"Welcome "+id, JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	/**
	 * Title      : getTariff
	 * Description: get tariff 
	 * @return ArrayList
	 */
	public ArrayList<ArrayList<String>> getTariff(){
		return tariffList;
	}
	
	/**
	 * Title      : gerUserID
	 * Description: get a available userID for a visitor
	 * @return String
	 * @throws IOException 
	 */
	public String getUserID() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("visitorRecord.txt"));
		visitorRecord=new ArrayList<ArrayList<String>>();
		while((buff=br.readLine()) != null&&buff!=null){
			bufer=new ArrayList<String>();
			visitorCounter++;
			buf=buff.split(" ");
			bufer.add(buf[0]);
			bufer.add(buf[1]);
			bufer.add(buf[2]);
			bufer.add(buf[3]);
			visitorRecord.add(bufer);
		}
		int bufCounter;
		for(bufCounter=0;bufCounter<=visitorCounter-1;bufCounter++){
			if(visitorRecord.get(bufCounter).get(1).equals("0")){
				id=visitorRecord.get(bufCounter).get(0);
				break;
			}
		}
		visitorCounter=0;
		return id;
	}
	
	
	public String getPakingID(){
		return parkingID;
	}
}
