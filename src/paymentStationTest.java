import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class paymentStationTest {
	
	paymentStation paymentStation1;
	entrance entrance1;
	parking parking1;
	register register1;
	FrameFixture pay1;

	@Before
	public void setUp() throws Exception {
		parking1=new parking();
		entrance1=new entrance(parking1);
		register1=new register();
		paymentStation1=new paymentStation(entrance1,parking1,register1);
		pay1=new FrameFixture(paymentStation1.frame);
		pay1.show();
	}

	@After
	public void tearDown() throws Exception {
		pay1.cleanUp();
	}

	@Test
	public void testPaymentStation() {
		pay1.textBox("textField").enterText("B0002");
		pay1.textBox("textField").pressKey(KeyEvent.VK_ENTER);
	}

	@Test
	public void testGetDate() {
	}

}
