import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.fest.swing.fixture.FrameFixture;   
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;


public class entranceTest extends TestCase{
	parking parking1;
	FrameFixture entrance1,exi1,public1;
	entrance entr1;
	register register1;
	exit exit1;
	Public_use public_use1;
	Pattern p; 
	SimpleDateFormat df;
	Date testDate;

	@Before
	public void setUp() throws Exception {
		parking1=new parking();
		entr1=new entrance(parking1);
		entrance1=new FrameFixture(entr1);
		exit1=new exit(parking1,entr1);
		testDate=new Date();
		df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		entrance1.show();
		p=Pattern.compile("You are allocated at .....");
	}

	@After
	public void tearDown() throws Exception {
		entrance1.cleanUp();
		exi1=new FrameFixture(exit1);
		exi1.show();
		exi1.textBox("textField1").enterText("A100");
		exi1.button("button1").click();
		exi1.cleanUp();
	}
	
	@Test
	public void testGetCalendar() {
	}
	
	@Test
	public void testEntrance() {
		entrance1.toggleButton("button2").click();
		entrance1.textBox("textField1").enterText("A100");
		entrance1.button("button1").click();
		entrance1.optionPane().requireMessage("Identity recognized");
		entrance1.optionPane().okButton().click();
		entrance1.optionPane().requireMessage(p);
		entrance1.optionPane().okButton().click();
	}
	
	@Test
	public void testEntrance3() {
		entrance1.toggleButton("button2").click();
		entrance1.textBox("textField1").enterText("A000");
		entrance1.button("button1").click();
		entrance1.optionPane().requireErrorMessage().requireMessage("No staff matched");
	}
	
	@Test
	public void testEntrance2() throws IOException, ParseException, InterruptedException {
		entrance1.toggleButton("button3").click();
		entrance1.textBox("textField2").enterText("qwe");
		entrance1.button("button1").click();
		entrance1.cleanUp();
		register1=new register();
		FrameFixture tari1;
		tari1=new FrameFixture(entr1.tariff1);
		tari1.show();
		tari1.button("OKbutton").click();
		tari1.cleanUp();
		public_use1=new Public_use(entr1, "B0001", df.format(testDate), register1);
		public1=new FrameFixture(public_use1.frame);
		public1.show();
		public1.button("btnAdd").click();
		public1.button("btnCheck").click();
		public1.cleanUp();
		exi1=new FrameFixture(exit1);
		exi1.show();
		exi1.textBox("textField1").enterText("B0001");
		exi1.button("button1").click();
		exi1.cleanUp();
	}
	
	@Test
	public void testActionPerformed() {
	}

	@Test
	public void testItemStateChanged() {
	}

	@Test
	public void testGetEnterTime() throws IOException {
		assertEquals(df.format(new Date()).substring(0,15),entr1.getEnterTime("A100").substring(0,15));
	}

	@Test
	public void testAddDepartureTime() throws IOException {
		entrance1.toggleButton("button2").click();
		entrance1.textBox("textField1").enterText("A100");
		entrance1.button("button1").click();
		entrance1.optionPane().requireMessage("Identity recognized");
		entrance1.optionPane().okButton().click();
		entrance1.optionPane().requireMessage(p);
		entrance1.optionPane().okButton().click();
		entr1.addDepartureTime("A100", df.format(testDate));
		String[] buf=new String[4];
		String leaveTime=null;
		String userID="A100";
		String buff;
		if((userID.subSequence(0,1).equals("B"))){
			BufferedReader br = new BufferedReader(new FileReader("visitorRecord.txt"));
			while((buff=br.readLine()) != null){
				buf=buff.split(" ");
				if(buf[0].equals(userID)){
					leaveTime=buf[4];
				}
			}
		}
		else if((userID.subSequence(0,1).equals("A"))){
			BufferedReader br = new BufferedReader(new FileReader("staffRecord.txt"));
			while((buff=br.readLine()) != null&&buff.trim().length() > 0){
				buf=buff.split(" ");
				if(buf[0].equals(userID)){
					leaveTime=buf[2];
				}
			}
		}
		assertEquals(df.format(testDate),leaveTime);
	}

	@Test
	public void testWriteStaffRecord() {}
	
	public static void main(String[] args){
		//junit.swingui.TestRunner.run(entranceTest.class);     
	}

}
