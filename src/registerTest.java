import static org.junit.Assert.*;

import java.io.IOException;
import java.util.regex.Pattern;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class registerTest {
	register testedRegister;
	FrameFixture testedFrame;
	double tempNumber;
	Pattern p1;

	@Before
	public void setUp() throws Exception {
		testedRegister=new register();
		testedFrame=new FrameFixture(testedRegister);
		p1=Pattern.compile("You have allocated to be A...");
		
	}

	@After
	public void tearDown() throws Exception {
		testedFrame.cleanUp();
	}

	@Test
	public void testRegister() {
	}

	@Test
	public void testStartRegister() throws IOException {
		testedFrame.show();
		testedFrame.button("registration").click();
		testedFrame.optionPane().textBox().enterText("123");
		testedFrame.optionPane().okButton().click();
		testedFrame.optionPane().requireInformationMessage().requireMessage(p1);
		testedFrame.optionPane().okButton().click();
	}

	@Test
	public void testActionPerformed() {
	}

	@Test
	public void testGetReceiptNumber() throws IOException {
		testedRegister.getReceiptNumber();
		testedRegister.setReceiptNumber(4);
		assertEquals(4,testedRegister.getReceiptNumber());
	}

	@Test
	public void testSetReceiptNumber() {
	}

	@Test
	public void testGetCashNumber() throws NumberFormatException, IOException {
		tempNumber=testedRegister.getCashNumber();
		testedRegister.addCash(10);
		testedRegister.clearCash();
		assertEquals(0,(int)(tempNumber+10-testedRegister.getCashNumber()));
		testedRegister.addCash(-10);
		testedRegister.clearCash();
	}

	@Test
	public void testClearCash() {
	}

	@Test
	public void testAddCash() {
	}
	
	@Test
	public void testGeneratemonthlyBill() throws IOException {
	testedFrame.show();
	testedFrame.button("monthlyBill").click();
	testedFrame.optionPane().textBox().enterText("A100");
	testedFrame.optionPane().okButton().click();
	testedFrame.optionPane().requireInformationMessage().requireMessage("Your monthly bill has been produced.");
	testedFrame.optionPane().okButton().click();
	}

}
