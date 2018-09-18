import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *  Title      : monthlyBill.java
 *  Description: This class is the monthlyBill class generate mothly bill for staffs
 *  Copyright  : Copyright (c) 2014 
 *  @author  Group 52
 *  @version 1.0
 */

public class monthlyBill {
	private String buff,line,userID;
	private String[] buf;
	private ArrayList<String> bufer;
	private ArrayList<ArrayList<String>> userRecord;
	private FileWriter fw;
	private int i;
	private SimpleDateFormat df;
	
	/**
	 * Title      : monthlyBill
	 * Description: generate monthly bill through userID
	 * @param String
	 * @return null
	 * @throws IOException 
	 */
	
	public monthlyBill(String userID) throws IOException, ParseException{
		this.userID=userID;
		BufferedReader br = new BufferedReader(new FileReader("staffRecord.txt"));
		df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		userRecord=new ArrayList<ArrayList<String>>();
		while((buff=br.readLine()) != null&&buff.trim().length() > 0){
			buf=buff.split(" ");
			if(buf[0].equals(userID)&&((int)(new Date()).getTime()-df.parse(buf[1]).getTime())/(1000*3600*24)<=31){
				bufer=new ArrayList<String>();
				bufer.add(buf[0]);
				bufer.add(buf[1]);
				bufer.add(buf[2]);
				userRecord.add(bufer);
			}
		}
		File monthlyBill=new File("monthlyBill.txt");
		if(!monthlyBill.exists())
			monthlyBill.createNewFile();
		if(monthlyBill.length()==0){
			fw=new FileWriter("monthlyBill.txt");
			for(i=0;i<userRecord.size();i++){
				line=userID+" "+userRecord.get(i).get(1)+" "+userRecord.get(i).get(2)+"\r\n";
				fw.write(line);
			}
			
			fw.flush();
			fw.close();
		}
		else
		{
			fw=new FileWriter("monthlyBill.txt");
			for(i=0;i<userRecord.size();i++){
				line=userID+" "+userRecord.get(i).get(1)+" "+userRecord.get(i).get(2)+"\r\n";
				fw.write(line);
			}
			
			fw.flush();
			fw.close();
		}
	}
	
	/**
	 * Title      : getMonthBill
	 * Description: provide other class with method to generate mothlyBill
	 * @return null
	 * @throws IOException 
	 */
	
	public void getMonthlyBill() throws IOException, ParseException{
		new monthlyBill(userID);
	}
}
